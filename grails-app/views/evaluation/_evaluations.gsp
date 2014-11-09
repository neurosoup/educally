<g:each in="${evaluations}" var="evaluation">

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