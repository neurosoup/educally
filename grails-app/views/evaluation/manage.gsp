<!DOCTYPE html>
<html>
<head>

</head>

<body>

<div id="page-title" class="row">
    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
        <h1 class="page-title txt-color-blueDark"><i class="fa fa-tachometer fa-fw "></i>
            Evaluations
            <span>>
            ${skillBookTitle}
            </span>
        </h1>
    </div>

    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
        <ul id="sparks" class="">
            <li class="sparks-info">
                <h5>Couverture <span class="txt-color-blue"><i class="fa fa-check-circle"></i>&nbsp;${skillCoverage}%
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

                                <ul id="myTab1" class="nav nav-tabs bordered tabs-pull-right">
                                    <g:each in="${evaluatedSkills}" var="evaluatedSkill">
                                            <li>
                                                <a href="#skill-${evaluatedSkill.key}"
                                                   data-toggle="tab">${evaluatedSkill.value.skill.title}</a>
                                            </li>
                                    </g:each>
                                </ul>

                                <div id="myTabContent1" class="tab-content padding-10">
                                    <div class="tab-pane fade in active" id="s1">
                                        <g:render template="evaluationProperties"/>
                                    </div>

                                    <div class="tab-pane fade in active" id="s2"/>
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

<asset:javascript src="evaluation.manage.js"/>

<g:javascript>

    initializeSkillExplorer("${raw(skills)}", "#nestable", "${g.render(template: 'skillNode')}");

</g:javascript>

</body>
</html>