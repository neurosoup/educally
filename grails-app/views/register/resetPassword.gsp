<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="sign"/>
    <title>Réinitialisation du mot de passe</title>
    <meta name="sign-type" content="sign-forgot">
</head>

<body>

<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-7 hidden-xs hidden-sm">
        <h1 class="txt-color-red login-header-big">Educally</h1>

        <div class="hero">

            <div class="pull-left login-desc-box-l">
                <h4 class="paragraph-header">It's Okay to be Smart. Experience the simplicity of SmartAdmin, everywhere you go!</h4>

                <div class="login-app-icons">
                    <a href="javascript:void(0);" class="btn btn-danger btn-sm">Frontend Template</a>
                    <a href="javascript:void(0);" class="btn btn-danger btn-sm">Find out more</a>
                </div>
            </div>

            <asset:image src="demo/iphoneview.png" alt="" class="pull-right display-image" style="width:210px"/>

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

    <div class="col-xs-12 col-sm-12 col-md-5 col-lg-5">
        <div class="well no-padding">

            <g:form role="form" action='resetPassword' name='resetPasswordForm'
                    class="smart-form client-form">
                <header>
                    Réinitialisation du mot de passe
                </header>

                <fieldset>
                    <section>
                        <div class="form-group">
                            <label class="input" data-toggle="tooltip"
                                   title="Votre nouveau mot de passe">

                                <i class="icon-append fa fa-lock"></i>
                                <input type="password" name="password" placeholder="Mot de passe" id="password"
                                       value="${command?.password}">
                            </label>
                        </div>
                    </section>

                    <section>
                        <div class="form-group">
                            <label class="input" data-toggle="tooltip"
                                   title="Confirmation de votre nouveau mot de passe">
                                <i class="icon-append fa fa-lock"></i>
                                <input type="password" name="password2" placeholder="Confirmation du mot de passe"
                                       value="${command?.password2}">
                            </label>
                        </div>
                    </section>
                </fieldset>

                <footer>
                    <g:submitButton name="Réinitialiser" class="btn btn-primary"/>
                </footer>

            </g:form>

        </div>

    </div>
</div>

<asset:javascript src="page.reset-password.js"/>

</body>
</html>




