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
        <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

            <div class="jarviswidget" id="wid-id-0">
                <header>
                    <span class="widget-icon"><i class="fa fa-comments"></i></span>

                    <h2>Compétences</h2>

                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                    <!-- widget content -->
                    <div class="widget-body no-padding">
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

                        <div class="dd" id="nestable">
                            <ol class="dd-list" data-name="root">
                            </ol>
                        </div>

                    </div>
                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->

        </article>
        <!-- WIDGET END -->

        <article class="col-xs-12 col-sm-6 col-md-6 col-lg-6">

            <div class="jarviswidget" id="wid-id-1">
                <header>
                    <span class="widget-icon"><i class="fa fa-comments"></i></span>

                    <h2>Evaluations</h2>

                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->

                    </div>
                    <!-- end widget edit box -->

                <div class="widget-body-toolbar">

                    <div class="row">


                        <div class="pull-right col-xs-3 col-sm-7 col-md-7 col-lg-7 text-right">

                            <button class="btn btn-success">
                                <i class="fa fa-plus"></i> <span class="hidden-mobile">Add New Row</span>
                            </button>

                        </div>

                    </div>

                </div>

                    <!-- widget content -->
                    <div class="widget-body no-padding">

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

                                    <p>
                                        test
                                    </p>

                                </div>
                            </div>

                            <div class="tab-pane fade" id="s2">
                                <p>
                                    Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee.
                                </p>
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

    buildSkillTree("${raw(skills)}", "#nestable", "${g.render(template: 'skillNode')}");

</g:javascript>

</body>
</html>