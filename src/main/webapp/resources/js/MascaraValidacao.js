// JavaScript Document
$(document).bind('ajaxComplete pfAjaxComplete', function () {
    handle();
});

$(document).ready(function () {
    handle();
});

function handle() {
    mascaraTel($('input[type=telephone]'));
    mascaraCPF($('input[type=cpf]'));
    mascaraCEP($('input[type=cep]'));
}

function mascaraCPF(inputCPF) {
    if (inputCPF.length > 0) {
        inputCPF.mask('000.000.000-00');
    }
}

function mascaraCEP(inputCEP) {
    if (inputCEP.length > 0) {
        inputCEP.mask('00000-000');
    }
}

function mascaraTel(inputPhone) {
    if (inputPhone.length > 0) {
        let maskBehavior = function (val) {
                return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
            },
            options = {
                onKeyPress: function (val, e, field, options) {
                    field.mask(maskBehavior.apply({}, arguments), options);
                }
            };
        inputPhone.mask(maskBehavior, options);
    }
}
