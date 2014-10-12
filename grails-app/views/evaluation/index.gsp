<%@ page import="grails.converters.JSON" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>

<div id="page-title" class="row">
    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
        <h1 class="page-title txt-color-blueDark"><i class="fa fa-tachometer fa-fw "></i>
            Evaluations
            <span>
                ${expandoInstance.skillBook.title}
            </span>
        </h1>
    </div>

    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
        <ul id="sparks" class="">
            <li class="sparks-info">
                <h5>Couverture <span class="txt-color-blue"><i
                        class="fa fa-check-circle"></i>&nbsp;${expandoInstance.stats.coverage}%
                </span></h5>
            </li>
        </ul>
    </div>
</div>

<!-- widget grid -->
<section id="widget-grid" class="">

    <!-- row -->
    <div class="row">

        <!-- NEW WIDGET START -->
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

            <div class="jarviswidget well" id="wid-id-0">
                <header>
                    <span class="widget-icon"><i class="fa fa-comments"></i></span>

                    <h2></h2>
                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <!-- widget content -->
                    <div id="wid-body-0" class="widget-body no-padding">
                        <div class="widget-body-toolbar">

                            <div class="row">

                                <div class="col-xs-9 col-sm-5 col-md-5 col-lg-5">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-search"></i></span>
                                        <input class="form-control" id="prepend" placeholder="Filter" type="text">
                                    </div>
                                </div>

                            </div>

                        </div>

                        <div id="wid-content-0">

                            %{--Skills navigator--}%
                            <div class="skills-nav activate">
                                <div class="dd" id="nestable">
                                    <ol class="dd-list" data-name="root">
                                    </ol>
                                </div>
                                <span class="minifyme" data-action="minifySkills"><i class="fa fa-book hit"></i></span>
                            </div>

                            %{--Evaluations--}%
                            <div class="evaluations-nav activate">

                                <ul id="evaluationTabs" class="nav nav-tabs bordered tabs-pull-right hidden">
                                    <g:each in="${expandoInstance.skills}" var="skill">
                                        <li>
                                            <a href="#skill-${skill.domainInstance.id}"
                                               data-toggle="tab">${skill.domainInstance.id}</a>
                                        </li>
                                    </g:each>
                                </ul>

                                <div id="myTabContent1" class="tab-content padding-10">
                                    <g:each in="${expandoInstance.skills}" var="skill">
                                        <div class="tab-pane fade in" id="skill-${skill.domainInstance.id}">

                                            <h1>${skill.domainInstance.title}</h1>

                                            <g:each in="${skill.evaluations}" var="evaluation">

                                                <div class="panel-group smart-accordion-default"
                                                     id="accordion-${evaluation.id}">

                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <h4 class="panel-title">
                                                                <a data-toggle="collapse"
                                                                   data-parent="#accordion-${evaluation.id}"
                                                                   href="#collapse-${evaluation.id}">
                                                                    <i class="fa fa-lg fa-angle-down pull-right"></i>
                                                                    <i class="fa fa-lg fa-angle-up pull-right"></i>
                                                                    ${evaluation.title}
                                                                </a>
                                                            </h4>
                                                        </div>

                                                        <div id="collapse-${evaluation.id}"
                                                             class="panel-collapse collapse in">
                                                            <div class="panel-body">
                                                                Liste des valeurs
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </g:each>

                                        </div>
                                    </g:each>
                                </div>

                            </div>

                        </div>

                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->

        </article>
        <!-- WIDGET END -->

    </div>

    <!-- end row -->

</section>
<!-- end widget grid -->

<asset:javascript src="evaluation.index.js"/>

<g:javascript>
    var model = "${raw(expandoInstance.skills.domainInstance as JSON)}";
    initializeModel(model, "#nestable", "${g.render(template: 'skillNode')}");
</g:javascript>

</body>
</html>