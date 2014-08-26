//= require application.forms
//= require_self

$(document).ready(function () {

    $('#email').focus();

    $('#loginForm').bootstrapValidator({
        message: '',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            email: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'La saisie de l\'email ou du nom d\'utilisateur est obligatoire'
                    }
                }
            },
            password: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'La saisie du mot de passe est obligatoire'
                    },
                    stringLength: {
                        min: 8,
                        max: 64,
                        message: 'La longueur du mot de passe doit être comprise entre 8 et 64 caractères'
                    }
                }
            }
        }
    });
});
