//= require plugin/jquery-nestable/jquery.nestable
//= require plugin/pace/pace
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */

function buildSkillTree(url, root) {

    $.ajax({
        type: 'POST',
        //data: null,
        url: url,
        success: function (data, textStatus) {
            build(data, root);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("Erreur lors de la construction de l'arbre du livret de compétence : " + errorThrown);
        }
    });

    var build = function (data, root) {

        var skillTemplate = $("#skill-template");
        var rootElement = $(root);

        console.log(data);

        for (var i = 0, len = data.length; i < len; i++) {

            var skill = data[i];

            var template = skillTemplate.clone();
            var content = template.find("#skill-content");
            var idElement = template.find("li");

            console.log("name="+skill.name+" path="+skill.path);

            if (skill.name) {
                idElement.attr("data-id", skill.name);
            } else {
                idElement.attr("data-id", skill.id)
            }

            content.html(skill.title);

            console.log(template.html());

            if (skill.path) {
                var path = skill.path.split(",");
                var parentName = path[path.length - 2];
                var parent = rootElement.find("[data-id='" + parentName + "']");

                console.log(parent);

                parent.append(template.html());
            } else {

                rootElement.append("<ol class='dd-list'></o>").append(template.html());
            }
        }
    };

}

var pagefunction = function () {

    var updateOutput = function (e) {
        var list = e.length ? e : $(e.target), output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));
            //, null, 2));
        } else {
            output.val('JSON browser support required.');
        }
    };

    // activate Nestable for list 1
    $('#nestable').nestable({
        group: 1
    }).on('change', updateOutput);

    // output initial serialised data
    updateOutput($('#nestable').data('output', $('#nestable-output')));

    $('#nestable-menu').on('click', function (e) {
        var target = $(e.target), action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
    });

};

// end pagefunction

pagefunction();



