//= require_tree plugin/bootstrap-validator
//= require plugin/masked-input/jquery.maskedinput
//= require_self


$(document).ready(function () {

    runAllForms();

    $("[data-toggle='tooltip']").tooltip({
        container: 'body',
        trigger: 'focus'
    });

});
