<!DOCTYPE html>
<html>
<head>

</head>

<body>

<div class="row">
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
            %{--<li class="sparks-info">
                <h5>Site Traffic <span class="txt-color-purple"><i class="fa fa-arrow-circle-up"
                                                                   data-rel="bootstrap-tooltip"
                                                                   title="Increased"></i>&nbsp;45%</span></h5>

                <div class="sparkline txt-color-purple hidden-mobile hidden-md hidden-sm">
                    110,150,300,130,400,240,220,310,220,300, 270, 210
                </div>
            </li>
            <li class="sparks-info">
                <h5>Site Orders <span class="txt-color-greenDark"><i class="fa fa-shopping-cart"></i>&nbsp;2447</span>
                </h5>

                <div class="sparkline txt-color-greenDark hidden-mobile hidden-md hidden-sm">
                    110,150,300,130,400,240,220,310,220,300, 270, 210
                </div>
            </li>--}%
        </ul>
    </div>
</div>

<!-- widget grid -->
<section id="widget-grid" class="">

<!-- row -->
<div class="row">

<!-- NEW WIDGET START -->
<article class="col-sm-12">

<!-- Widget ID (each widget will need unique ID)-->
<div class="jarviswidget well" id="wid-id-0">
<!-- widget options:
				usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

				data-widget-colorbutton="false"
				data-widget-editbutton="false"
				data-widget-togglebutton="false"
				data-widget-deletebutton="false"
				data-widget-fullscreenbutton="false"
				data-widget-custombutton="false"
				data-widget-collapsed="true"
				data-widget-sortable="false"

				-->
<header>
    <span class="widget-icon"><i class="fa fa-comments"></i></span>

    <h2>My Data</h2>

</header>

<!-- widget div-->
<div>

<!-- widget edit box -->
<div class="jarviswidget-editbox">
    <!-- This area used as dropdown edit box -->

</div>
<!-- end widget edit box -->

<!-- widget content -->
<div class="widget-body">

    <hr class="simple">

    <ul id="myTab1" class="nav nav-tabs bordered">
        <li class="active">
            <a href="#s1" data-toggle="tab">Toutes les évaluations <span
                    class="badge bg-color-blue txt-color-white">${evaluationCount}</span></a>
        </li>
        %{--<li>
            <a href="#s2" data-toggle="tab"><i class="fa fa-fw fa-lg fa-gear"></i> Tab Item 2</a>
        </li>--}%
        %{-- <li class="dropdown">
             <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">Toutes les évaluations <span
                     class="badge bg-color-blue txt-color-white">${evaluationCount}</span> <b
                     class="caret"></b></a>
             <ul class="dropdown-menu">
                 <li>
                     <a href="#s3" data-toggle="tab">@fat</a>
                 </li>
                 <li>
                     <a href="#s4" data-toggle="tab">@mdo</a>
                 </li>
             </ul>
         </li>--}%
        %{--<li class="pull-right">
            <a href="javascript:void(0);">
                <div class="sparkline txt-color-pinkDark text-align-right" data-sparkline-height="18px"
                     data-sparkline-width="90px" data-sparkline-barwidth="7">
                    5,10,6,7,4,3
                </div></a>
        </li>--}%
    </ul>

    <div id="myTabContent1" class="tab-content padding-10">
        <div class="tab-pane fade in active" id="s1">
            <div class="row">

                <div class="col-sm-12 col-lg-8">

                    <div class="dd" id="nestable">
                        <ol class="dd-list" data-name="root">
                        </ol>
                        %{--<ol class="dd-list">
                            <li class="dd-item" data-id="1">
                                <div class="dd-handle">
                                    Item 1 <span>- Description Field</span>
                                </div>
                            </li>
                            <li class="dd-item" data-id="2">
                                <div class="dd-handle">
                                    <h4><span class="semi-bold">Item 2</span> - Big Header</h4>
                                    <span>Description with piechart</span>
                                    <span class="air air-top-right padding-7">
                                        <div class="easy-pie-chart text-danger easyPieChart" data-percent="33"
                                             data-pie-size="40"
                                             data-pie-track-color="rgba(169, 3, 41,0.07)">
                                            <span class="percent percent-sign txt-color-red font-xs"></span>
                                        </div>
                                    </span>

                                </div>
                                <ol class="dd-list">
                                    <li class="dd-item" data-id="3">
                                        <div class="dd-handle">
                                            Item 3
                                        </div>
                                    </li>
                                    <li class="dd-item" data-id="4">
                                        <div class="dd-handle">
                                            Item 4
                                            <em class="label pull-right label-primary">
                                                Label ID
                                            </em>
                                        </div>
                                    </li>
                                    <li class="dd-item" data-id="5">
                                        <div class="dd-handle">
                                            Item 5

                                        </div>
                                        <ol class="dd-list">
                                            <li class="dd-item" data-id="6">
                                                <div class="dd-handle bg-color-blue txt-color-white">
                                                    <i>Item 6 (italic)</i>
                                                </div>
                                            </li>
                                            <li class="dd-item" data-id="7">
                                                <div class="dd-handle bg-color-pink txt-color-white">
                                                    <strong>Item 7 (bold)</strong>
                                                </div>
                                            </li>
                                            <li class="dd-item" data-id="8">
                                                <div class="dd-handle bg-color-greenLight txt-color-white">
                                                    <strong><i>Item 8 (Bold + Italic)</i></strong>
                                                </div>
                                            </li>
                                        </ol>
                                    </li>
                                    <li class="dd-item" data-id="9">
                                        <div class="dd-handle">
                                            Item 9

                                            <em class="badge pull-right bg-color-purple">
                                                99
                                            </em>
                                        </div>
                                    </li>
                                    <li class="dd-item" data-id="10">
                                        <div class="dd-handle">
                                            Item 10
                                        </div>
                                    </li>
                                </ol>
                            </li>
                            <li class="dd-item" data-id="11">
                                <div class="dd-handle">

                                    <div class="row">
                                        <div class="col-xs-8">
                                            Item 11
                                            <span class="font-xs text-muted">
                                                - with progress bar
                                            </span>
                                        </div>

                                        <div class="col-xs-4">
                                            <div class="progress progress-striped active no-margin">
                                                <div class="progress-bar progress-bar-primary" role="progressbar"
                                                     style="width: 37%">37%</div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </li>
                            <li class="dd-item" data-id="12">
                                <div class="dd-handle">

                                    <div class="row">
                                        <div class="col-xs-8 text-success">
                                            <strong>Item 12</strong>
                                            <span class="font-xs text-muted">
                                                - success text
                                            </span>
                                        </div>

                                        <div class="col-xs-4">
                                            <div class="progress progress-striped active no-margin">
                                                <div class="progress-bar progress-bar-success" role="progressbar"
                                                     style="width: 85%">85%</div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </li>
                        </ol>--}%
                    </div>

                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="s2">
            <p>
                Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee.
            </p>
        </div>

    </div>

    <div id="nestable-menu">
        <button type="button" class="btn btn-default" data-action="expand-all">
            Expand All
        </button>
        <button type="button" class="btn btn-default" data-action="collapse-all">
            Collapse All
        </button>
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

<!-- row -->

<div class="row">

    <div class="col-sm-12">

        <div class="well well-sm well-light">
            <p>
                <strong>Debug</strong>
            </p>

            <p class="alert alert-info">
                Preview of the lists update DB input.
            </p>
            <textarea id="nestable-output" rows="3" class="form-control font-md"></textarea>
            <br>

        </div>

    </div>

</div>

<!-- end row -->

</section>
<!-- end widget grid -->

<asset:javascript src="evaluation.manage.js"/>

<g:javascript>buildSkillTree("${raw(skills)}", "#nestable");</g:javascript>

</body>
</html>