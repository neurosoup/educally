<!DOCTYPE html>
<html>
<head>
    <asset:stylesheet src="dashboard.cs"/>
</head>

<body>
<div class="row">
    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
        <h1 class="page-title txt-color-blueDark"><i
                class="fa-fw fa fa-database"></i> Données <span>> Données de test</span>
        </h1>
    </div>

    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
        <ul id="sparks" class="">
            <li class="sparks-info">
                <h5 class="text-center">Elèves <span class="txt-color-blue">5</span></h5>
            </li>
        </ul>
    </div>
</div>
<!-- widget grid -->
<section id="widget-grid" class="">

    <!-- row -->
    <div class="row">
        <article class="col-sm-12">
            <!-- new widget -->
            <!-- new widget -->
            <div class="jarviswidget jarviswidget-color-blue" id="wid-id-0" data-widget-editbutton="false"
                 data-widget-colorbutton="false" data-widget-deletebutton="false">

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
                    <span class="widget-icon"><i class="fa fa-gears txt-color-white"></i></span>

                    <h2>Actions sur les données de test</h2>
                </header>

                <!-- widget div-->
                <div>
                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <div>
                            <label>Title:
                                <input type="text"/>
                            </label>
                        </div>
                    </div>
                    <!-- end widget edit box -->

                    <div class="widget-body">
                    <!-- content goes here -->

                            <a class="btn btn-default"
                               onclick='resetData("${createLink(action: 'resetdata')}")'>
                                <i class="fa fa-refresh "></i> Réinitialiser</a>

                    <!-- end content -->
                    </div>

                </div>
                <!-- end widget div -->
            </div>
            <!-- end widget -->
            <!-- end widget -->

        </article>
    </div>

    <!-- end row -->

</section>
<!-- end widget grid -->

<asset:javascript src="page.demodata.js"/>

</body>
</html>