$(document).ready(function() {              
    //var cache_width = $('#printDiv').width(); //Criado um cache do CSS
    var a4  =[ 595.28,  841.89]; // Widht e Height de uma folha a4
    // Setar o width da div no formato a4
    $("#printDiv").width((a4[0]*1.33333) -80).css('max-width','none');

    //var canvas = document.getElementById("printDiv");
            
    $('#printDiv').doc.internal.scaleFactor = 1.8;
    
    html2canvas($('#printDiv'), {
        onrendered: function(canvas) {
            var img = canvas.toDataURL("image/jpg",1.0);  
            var doc = new jsPDF({unit:'px', format:'a4'});
            
            
            
            doc.addImage(img, 'PNG', 20, 20);
            //$('#printDiv').width(cache_width);

            //var imageApp = canvas.toDataURL("image/jpeg",1,0);

            //doc.save('Contracheque.pdf');
            //doc.output('datauristring');        //returns the data uri string
            //doc.output('datauri');              //opens the data uri in current window
            //doc.output('dataurlnewwindow');     //opens the data uri in new window                  

            //App Android
            if( /PortalServidorAndroidApp\/[0-9\.]+$/.test(navigator.userAgent) ) { 
                console.log('@ Android App @');
                //JSInterface.downloadPDF();

                var canvas = document.getElementById("printDiv");

                var fullQuality = canvas.toDataURL("image/jpeg", 1.0);

                JSInterface.downloadNewPDF(fullQuality);

            }

            else {

                var isSafari = navigator.vendor && navigator.vendor.indexOf('Apple') > -1 &&
                navigator.userAgent && !navigator.userAgent.match('CriOS');

                var ua = navigator.userAgent.toLowerCase();
                var isAndroid = ua.indexOf("android") > -1;

                //Safari
                if (isSafari) {
                    console.log('@ iOS Safari browser @');
                    //alert("@ iOS Safari browser @");
                    doc.output('save', 'filename.pdf'); //Works Safari
                }

                //Android browser
                else if(isAndroid) {
                    console.log('@ Android Chrome browser @');
                    //alert("@ Android Chrome browser @");
                    doc.save('Contracheque.pdf');
                }

                //Mobile Browser
                else if( /Android|webOS|iPhone|iPad|iPod|Opera Mini/i.test(navigator.userAgent) ) {
                    console.log('@ Other Browsers @');
                    //alert("@ Other Browsers @");
                    var reader = new FileReader();
                    var out = new Blob([doc.output("blob")], {type: 'application/pdf'});
                    reader.onload = function(e){
                        window.location.href = reader.result;
                    };
                    reader.readAsDataURL(out);

                    //doc.output('datauri'); //Crash
                    //doc.output('dataurlnewwindow'); //Nothing
                    //doc.output('datauristring'); //Nothing

//                    var string = doc.output('datauristring');
//                    var x = window.open();
//                    x.document.open();
//                    x.document.location=string;

//                    var blob = pdf.output("blob");
//                    window.open(URL.createObjectURL(blob));

//                    doc.save('Contracheque.pdf');

//                    var blob = pdf.output("blob");
//                    window.open(URL.createObjectURL(blob));

//                    canvas.toBlob(function(blob) {
//                        saveAs(blob, "Dashboard.png"); 
//                    });


                //Web Browser    
                } else {
                    console.log('@ Web Browser @');
                    //alert("@ Web Browser @");
                    doc.save('Contracheque.pdf');
                }
//                setTimeout(function() {
//                    window.history.back();
//                }, 500);
            }
        }
    });
});