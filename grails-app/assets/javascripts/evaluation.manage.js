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
            console.log("Erreur lors de la construction du livret de compétence : " + errorThrown);
        }
    });

    var build = function (data, root) {

        var skillTemplate = $("#skill-template");
        var rootElement = $(root);

        var knownParents = [];

        for (var i = 0, len = data.length; i < len; i++) {

            var template = skillTemplate.clone();

            var skill = data[i];
            template.find("li").attr("data-id", skill.id)
            template.find("#skill-content").html(skill.title);

            if (skill.path) {
                var path = skill.path.split(",");
                var parentName = path[path.length - 2];
                var parent = rootElement.find("[data-name='" + parentName + "']");

                if (knownParents.indexOf(parentName) == -1) {
                    knownParents.push(parentName);
                    parent.append("<ol data-name='" + skill.name + "' class='dd-list'></o>").append(template.html());
                } else {
                    parent.append(template.html());
                }

                console.log(knownParents);

            } else {
                rootElement.append("<ol data-name='" + skill.name + "' class='dd-list'></o>").append(template.html());
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



