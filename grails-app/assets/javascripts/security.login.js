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
            j_username: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'La saisie du nom d\'utilisateur est obligatoire'
                    }
                }
            },
            j_password: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'La saisie du mot de passe est obligatoire'
                    }
                }
            }
        }
    });
});
