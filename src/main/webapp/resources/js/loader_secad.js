$(document).ready(function() {
    let x = document.getElementById('loader-wrapper-2');
    x.style.display = 'none';
});

// Função para iniciar o loader
function startLoading() {
    let x = document.getElementById('loader-wrapper-2');
    x.style.display = 'block';
}

// Função para finalizar o loader
function finishLoading() {
    let x = document.getElementById('loader-wrapper-2');
    x.style.display = 'none';
}

$(document).bind('ajaxComplete pfAjaxComplete', function(){
    checkDialogPresent();
});

// Caso um dialog esteja sendo exibido, mover para cima dele o loader
function checkDialogPresent() {
    let loaderWrapper = $( "#loader-wrapper" );
    let loaderWrapper2 = $( "#loader-wrapper2" );

    let dialogMasks = $( ".ui-dialog-mask");

    if (dialogMasks.length > 0) {
        loaderWrapper.css({
            "z-index": "2000",
            "padding-left": "inherit",
            "padding-top": "inherit"
        });
        loaderWrapper2.css({
            "z-index": "2000",
            "padding-left": "inherit",
            "padding-top": "inherit"
        });
    }

}