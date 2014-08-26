<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="sign"/>
    <title>Essayer Educally gratuitement</title>
    <meta name="sign-type" content="sign-up">
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

            <g:form role="form" controller="register" action='register' name='registerForm' id="smart-form-register"
                    class="smart-form client-form">
                <header>
                    Inscription pour découvrir Educally*
                </header>

                <fieldset>
                    <section>
                        <label class="input" data-toggle="tooltip"
                               title="Vous en aurez besoin pour vous connecter à votre compte">
                            <div class="form-group">
                                <i class="icon-append fa fa-user"></i>
                                <input type="text" id="username" name="username" placeholder="Nom d'utilisateur"
                                       value="${command?.username}"/>
                            </div>
                        </label>
                    </section>

                    <section>
                        <label class="input" data-toggle="tooltip"
                               title="Vous en aurez besoin pour vérifier votre compte">
                            <div class="form-group">
                                <i class="icon-append fa fa-envelope"></i>
                                <input type="email" name="email" placeholder="Adresse email" value="${command?.email}">
                            </div>
                        </label>
                    </section>

                    <section>
                        <label class="input" data-toggle="tooltip"
                               title="Le mot de passe est obligatoire">
                            <div class="form-group">
                                <i class="icon-append fa fa-lock"></i>
                                <input type="password" name="password" placeholder="Mot de passe" id="password"
                                       value="${command?.password}">
                            </div>
                        </label>
                    </section>

                    <section>
                        <label class="input" data-toggle="tooltip"
                               title="Le mot de passe est obligatoire">
                            <div class="form-group">
                                <i class="icon-append fa fa-lock"></i>
                                <input type="password" name="password2" placeholder="Confirmation du mot de passe"
                                       value="${command?.password2}">
                            </div>
                        </label>
                    </section>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-6">
                            <label class="input">
                                <input type="text" name="firstname" placeholder="Prénom" value="${command?.firstname}">
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="input">
                                <input type="text" name="lastname" placeholder="Nom" value="${command?.lastname}">
                            </label>
                        </section>
                    </div>

                    <div class="row">
                        <section class="col col-6">
                            %{--<label class="select">
                                <select name="gender">
                                    <option value="0" selected="" disabled="">Gender</option>
                                    <option value="1">Male</option>
                                    <option value="2">Female</option>
                                    <option value="3">Prefer not to answer</option>
                                </select> <i></i></label>--}%
                        </section>
                        %{-- <section class="col col-6">
                             <label class="input">
                                 <div class="form-group">
                                     <i class="icon-append fa fa-calendar"></i>
                                     <input type="text" name="request" placeholder="Request activation on"
                                            class="datepicker"
                                            data-dateformat='dd/mm/yy'>
                                 </div>
                             </label>
                         </section>--}%
                    </div>

                    <section>
                        %{--<label class="checkbox">
                            <input type="checkbox" name="subscription" id="subscription">
                            <i></i>I want to receive news and special offers</label>--}%
                        <label class="checkbox">
                            <div class="form-group">
                                <input type="checkbox" name="terms" id="terms">
                                <i></i>J'accepte les <a href="#" data-toggle="modal"
                                                        data-target="#myModal">conditions d'utilisation</a>
                            </div>
                        </label>
                    </section>
                </fieldset>

                <g:if test='${emailSent}'>
                    <fieldset>
                        <section>
                            <div class="alert adjusted alert-info fade in">
                                <a href="${createLink(uri: '/')}" class="close" >x</a>
                                <i class="fa-fw fa-lg fa fa-exclamation"></i>
                                <strong>Votre inscription a bien été prise en compte.</strong> <p>Vous allez recevoir un email de confirmation.</p>
                            </div>
                        </section>
                    </fieldset>
                </g:if>

                <footer>
                    <g:submitButton name="Commencez votre mois GRATUIT" class="btn btn-primary"/>
                </footer>

            </g:form>

        </div>

        <p class="note text-center">*Inscrivez-vous pour essayer Educally gratuitement pendant 1 mois. Sans engagement, annulez en ligne à tout moment.</p>
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

<asset:javascript src="register.js"/>

</body>
</html>




