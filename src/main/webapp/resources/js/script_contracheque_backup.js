$(document).ready(function() {              
    //var cache_width = $('#printDiv').width(); //Criado um cache do CSS
    //var a4  =[ 595.28,  841.89]; // Widht e Height de uma folha a4
    // Setar o width da div no formato a4
    //$("#printDiv").width((a4[0]*1.33333) -80).css('max-width','none');
    // Aqui ele cria a imagem e cria o pdf


    var canvasToImage = function(canvas){
        var img = new Image();
        var dataURL = canvas.toDataURL('image/png');
        img.src = dataURL;
        return img;
    };
    var canvasShiftImage = function(oldCanvas,shiftAmt){
        shiftAmt = parseInt(shiftAmt) || 0;
        if(!shiftAmt){ return oldCanvas; }

        var newCanvas = document.createElement('canvas');
        newCanvas.height = oldCanvas.height - shiftAmt;
        newCanvas.width = oldCanvas.width;
        var ctx = newCanvas.getContext('2d');

        var img = canvasToImage(oldCanvas);
        ctx.drawImage(img,0, shiftAmt, img.width, img.height, 0, 0, img.width, img.height);

        return newCanvas;
    };


    var canvasToImageSuccess = function(canvas){
        var pdf = new jsPDF('l','px'),
            pdfInternals = pdf.internal,
            pdfPageSize = pdfInternals.pageSize,
            pdfScaleFactor = pdfInternals.scaleFactor,
            pdfPageWidth = pdfPageSize.width,
            pdfPageHeight = pdfPageSize.height,
            totalPdfHeight = 0,
            htmlPageHeight = canvas.height,
            htmlScaleFactor = canvas.width / (pdfPageWidth * pdfScaleFactor),
            safetyNet = 0;

        while(totalPdfHeight < htmlPageHeight && safetyNet < 15){
            var newCanvas = canvasShiftImage(canvas, totalPdfHeight);
            pdf.addImage(newCanvas, 'png', 0, 0, pdfPageWidth, 0, null, 'NONE');

            totalPdfHeight += (pdfPageHeight * pdfScaleFactor * htmlScaleFactor);

            if(totalPdfHeight < htmlPageHeight){
                pdf.addPage();
            }
            safetyNet++;
        }

        pdf.save('test.pdf');
    };

    html2canvas($('#printdiv')[0], {
        onrendered: function(canvas){
            canvasToImageSuccess(canvas);
        }
    });
});