//= require application.forms
//= require_self

$(document).ready(function () {

    // Model i agree button
    $("#i-agree").click(function () {
        $this = $("#terms");
        if ($this.checked) {
            $('#myModal').modal('toggle');
        } else {
            $this.prop('checked', true);
            $('#myModal').modal('toggle');
        }
    });

    $('#username').focus();

    $('#registerForm').bootstrapValidator({
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
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'La longueur du nom d\'utilisateur doit être comprise entre 3 et 30 caractères'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'Le nom d\'utilisateur ne peut pas contenir de symboles'
                    }
                }
            },
            email: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'La saisie de l\'email est obligatoire'
                    },
                    regexp: {
                        regexp: /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/,
                        message: 'Cette adresse ne semble pas être valide'
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
            },
            terms: {
                message: '',
                validators: {
                    notEmpty: {
                        message: 'Vous devez accepter les conditions générales pour vous inscrire'
                    }
                }
            }
        }
    });

    /*$(function () {
// Validation
        $("#smart-form-register").validate({

            // Rules for form validation
            rules: {
                username: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 3,
                    maxlength: 20
                },
                passwordConfirm: {
                    required: true,
                    minlength: 3,
                    maxlength: 20,
                    equalTo: '#password'
                },
                firstname: {
                    required: true
                },
                lastname: {
                    required: true
                },
                gender: {
                    required: true
                },
                terms: {
                    required: true
                }
            },

            // Messages for form validation
            messages: {
                login: {
                    required: 'Please enter your login'
                },
                email: {
                    required: 'Please enter your email address',
                    email: 'Please enter a VALID email address'
                },
                password: {
                    required: 'Please enter your password'
                },
                passwordConfirm: {
                    required: 'Please enter your password one more time',
                    equalTo: 'Please enter the same password as above'
                },
                firstname: {
                    required: 'Please select your first name'
                },
                lastname: {
                    required: 'Please select your last name'
                },
                gender: {
                    required: 'Please select your gender'
                },
                terms: {
                    required: 'You must agree with Terms and Conditions'
                }
            },

            // Ajax form submition
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    success: function () {
                        $("#smart-form-register").addClass('submited');
                    }
                });
            },

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            }

        });
    });*/
});