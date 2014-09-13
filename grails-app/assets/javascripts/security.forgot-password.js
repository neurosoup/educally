//= require application.forms
//= require_self

$(document).ready(function () {

    $('#username').focus();

    $('#loginForm').bootstrapValidator({
        message: '',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            username: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'La saisie du nom d\'utilisateur est obligatoire'
                    }
                }
            }
        }
    });
});

