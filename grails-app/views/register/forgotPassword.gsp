<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="sign"/>
    <meta name="sign-type" content="sign-forgot">
    <title>Connexion</title>
</head>

<body>

<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
        <h1 class="txt-color-red login-header-big">Educally</h1>

        <div class="hero">

            <div class="pull-left login-desc-box-l">
                <h4 class="paragraph-header">It's Okay to be Smart. Experience the simplicity of SmartAdmin, everywhere you go!</h4>

                <div class="login-app-icons">
                    <a href="javascript:void(0);" class="btn btn-danger btn-sm">Frontend Template</a>
                    <a href="javascript:void(0);" class="btn btn-danger btn-sm">Find out more</a>
                </div>
            </div>

            <asset:image src="demo/iphoneview.png" class="pull-right display-image" alt="" style="width:210px"/>

        </div>

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <h5 class="about-heading">About SmartAdmin - Are you up to date?</h5>

                <p>
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa.
                </p>
            </div>

            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <h5 class="about-heading">Not just your average template!</h5>

                <p>
                    Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi voluptatem accusantium!
                </p>
            </div>
        </div>

    </div>

    <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
        <div class="well no-padding">
            <g:form role="form" action='forgotPassword' name='forgotPasswordForm' class="smart-form client-form">
                <header>
                    Réinitialisation du mot de passe
                </header>

                <fieldset>

                    <section>
                        <label class="input" data-toggle="tooltip"
                               title="Veuillez saisir votre nom d'utilisateur">
                            <div class="form-group">
                                <i class="icon-append fa fa-user"></i>
                                <input type="text" name="username" id="username" placeholder="Nom d'utilisateur">
                            </div>
                        </label>
                    </section>

                    <g:if test='${emailSent}'>
                        <section>
                            <div class="alert adjusted alert-info fade in">
                                <a href="${createLink(uri: '/')}" class="close">x</a>
                                <i class="fa-fw fa-lg fa fa-exclamation"></i>
                                <strong>Votre demande a bien été prise en compte.</strong>

                                <p>Vous allez recevoir un email de réinitialisation de votre mot de passe.</p>
                            </div>
                        </section>
                    </g:if>

                </fieldset>
                <footer>
                    <g:submitButton name="Envoyer" class="btn btn-primary"/>
                </footer>
            </g:form>

        </div>

    </div>
</div>

<asset:javascript src="forgot-password.js"/>

</body>
</html>




