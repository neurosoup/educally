//= require plugin/jquery-nestable/jquery.nestable
//= require plugin/pace/pace
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */

function buildTree(skills, rootDiv) {
    console.log(skills);

    /*var skillTemplate = $("#skill-template");

    for (var i = 0, len = skills.length; i < len; i++) {

        var skill = skills[i];
        var template = skillTemplate.clone();
        var content = template.find("#skill-content");
        var idElement = template.find("li");

        if (skill.name) {
            idElement.data("id", skill.name);
        } else {
            idElement.data("id", skill.id)
        }

        content.html(skill.name);

        if (skill.path) {
            var path = skill.path.split(",");
            var parentName = path[path.length - 1];
            var parent = $("li").find("[data-id='" + parentName + "']");
            parent.append(template.html());
        } else {
            rootDiv.append(template.html());
        }
    }*/
};

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



