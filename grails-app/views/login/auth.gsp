<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="sign"/>
    <meta name="sign-type" content="sign-in">
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
            <form role="form" action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'
                  class="smart-form client-form">
                <header>
                    Connexion
                </header>

                <fieldset>

                    <section>
                        <div class="form-group">
                            <label class="input" data-toggle="tooltip"
                                   title="Veuillez saisir votre nom d'utilisateur">
                                <i class="icon-append fa fa-user"></i>
                                <input type="text" name="j_username" id="username" placeholder="Nom d'utilisateur">
                            </label>
                        </div>
                    </section>

                    <section>
                        <div class="form-group">
                            <label class="input" data-toggle="tooltip" title="Veuillez saisir votre mot de passe">
                                <i class="icon-append fa fa-lock"></i>
                                <input type="password" name="j_password" id="password" placeholder="Mot de passe">
                            </label>
                        </div>

                        <div class="note">
                            <a href="${createLink(controller: 'register', action: 'forgotPassword')}">Mot de passe oublié ?</a>
                        </div>
                    </section>

                    <section>
                        <div class="form-group">
                            <label class="checkbox">
                                <input type="checkbox" name="${rememberMeParameter}" id="remember_me" checked="checked">
                                <i></i>Rester connecté
                            </label>
                        </div>
                    </section>

                    <g:if test="${params.login_error == '1'}">
                        <section>
                            <div class="alert adjusted alert-danger fade in">
                                <button class="close" data-dismiss="alert">
                                    ×
                                </button>
                                <i class="fa-fw fa-lg fa fa-times"></i>
                                ${flash.message}
                            </div>
                        </section>
                    </g:if>

                </fieldset>
                <footer>
                    <g:submitButton name="Connexion" class="btn btn-primary"/>
                </footer>
            </form>

        </div>

        %{--<h5 class="text-center">- Or sign in using -</h5>

        <ul class="list-inline text-center">
            <li>
                <a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
            </li>
            <li>
                <a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
            </li>
            <li>
                <a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
            </li>
        </ul>--}%

    </div>
</div>

<asset:javascript src="security.login.js"/>

</body>
</html>




