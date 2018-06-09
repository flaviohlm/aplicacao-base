$(document).bind('ajaxComplete pfAjaxComplete', function(){
    showDialog();
});


function showDialog() {
    $('.dialog-dinamico').each(function(i, obj) {
        let dialogAtual = obj;

        if (dialogAtual.offsetWidth !== 0) {
            // width
            let divWidth = dialogAtual.offsetWidth;
            let widthHalf = "-" + divWidth / 2 + "px";

            // height
            let divHeight = dialogAtual.offsetHeight;
            let heightHalf = "-" + divHeight / 2 + "px";


            if (isMobile()) {
                try {
                    let dialogContent = dialogAtual.childNodes[1]; // ui-dialog-content
                    let dialogContentID = Math.floor(Math.random() * 101); // id no ui-dialog-content

                    let percentageWindowHeight = percentage(window.innerHeight, 70);

                    if (dialogContent.clientHeight > percentageWindowHeight) {
                        // Fazer o dialog preencher a tela e centralizar

                        dialogContent.setAttribute("id", dialogContentID);
                        let jDialogContent = jQuery(dialogContent);

                        jDialogContent.css({
                            "height" : "calc(100vh - 30vh)"
                        });

                        let jDialogAtual = jQuery(dialogAtual);

                        // width
                        let divWidth2 = dialogAtual.offsetWidth;
                        let widthHalf2 = "-" + divWidth2 / 2 + "px";

                        // height
                        let divHeight2 = dialogAtual.offsetHeight;
                        let heightHalf2 = "-" + divHeight2 / 2 + "px";

                        jDialogAtual.css({
                            "left": "50%",
                            "top": "50%",
                            "margin-left": widthHalf2.valueOf(),
                            "margin-top": heightHalf2.valueOf()
                        });
                    } else {

                        let jDialogAtual = jQuery(dialogAtual);

                        // Apenas centralizar
                        jDialogAtual.css({
                            "left": "50%",
                            "top": "50%",
                            "margin-left": widthHalf.valueOf(),
                            "margin-top": heightHalf.valueOf()
                        });
                    }
                } catch (err) {
                    console.log(err.message);
                }
            } else {
                let jDialogAtual = jQuery(dialogAtual);

                // Apenas centralizar
                jDialogAtual.css({
                    "left": "50%",
                    "top": "50%",
                    "margin-left": widthHalf.valueOf(),
                    "margin-top": heightHalf.valueOf()
                });
            }
        }
    });
}

function isMobile() {
    return window.innerWidth <= 640;
}

function percentage(num, per) {
    return (num / 100) * per;
}

// Recalcular a posição do dialog durante eventos ajax dentro do mesmo
function setSize() {
    var dialogArray = document.getElementsByClassName("dialog-dinamico");
    for (var i in dialogArray) {
        var divWidth = dialogArray[i].offsetWidth;
        var divHeight = 400;
        var widthHalf = "-" + divWidth / 2 + "px";
        var heightHalf = "-" + divHeight / 2 + "px";

        $(".dialog-auto-resize").css({
            "left": "50%",
            "top": "50%",
            "margin-left": widthHalf.valueOf(),
            "margin-top": heightHalf.valueOf()
        });
    }
}