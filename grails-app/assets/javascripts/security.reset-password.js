//= require application.forms
//= require_self

$(document).ready(function () {

    $('#password').focus();

    $('#resetPasswordForm').bootstrapValidator({
        message: '',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
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
                    },
                    regexp: {
                        regexp: /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[+!@#$%^&]).*$/,
                        message: 'Le mot de passe doit avoir au moins une lettre, un chiffre et un caractère spécial : !@#$%^&'
                    }
                }
            },
            password2: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'La saisie du mot de passe est obligatoire'
                    },
                    stringLength: {
                        min: 8,
                        max: 64,
                        message: 'La longueur du mot de passe doit être comprise entre 8 et 64 caractères'
                    },
                    regexp: {
                        regexp: /^.*(?=.*\d)(?=.*[a-zA-Z])(?=.*[+!@#$%^&]).*$/,
                        message: 'Le mot de passe doit avoir au moins une lettre, un chiffre et un caractère spécial : !@#$%^&'
                    },
                    identical: {
                        field: 'password',
                        message: 'Les mots de passe ne sont pas identiques'
                    }
                }
            }
        }
    });

});