//= require plugin/jquery-nestable/jquery.nestable
//= require plugin/pace/pace
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */

var buildSkillTree = function (data, root) {

    var skillTemplate = $("#skill-template");
    var rootElement = $(root);
    var skills = JSON.parse(data);

    console.log(skills);

    for (var i = 0, len = skills.length; i < len; i++) {

        var template = skillTemplate.clone();

        var skill = skills[i];
        console.log("name=" + skill.name + " path=" + skill.path);

        var dataElement = template.find("ol");

        dataElement.attr("data-id", skill.id);
        template.find("#skill-content").html(skill.title);

        if (skill.name) {
            dataElement.attr("data-name", skill.name)
        }

        if (skill.path) {
            var path = skill.path.split(",");
            var parentName = path[path.length - 2];
            var parent = rootElement.find("[data-name='" + parentName + "']").children().first();

            parent.append(template.html());

            //console.log("name=" + skill.name + " parent=" + parentName + "\n parent=" + parent+ " html=" + parent.html());


        } else {
            rootElement.append(template.html());
            //console.log("name=" + skill.name + " parent=root");
        }
    }

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



