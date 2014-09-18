//= require plugin/jquery-nestable/jquery.nestable
//= require plugin/pace/pace
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */

var buildSkillTree = function (data, root) {

    var rootElement = $(root);
    var skills = JSON.parse(data);

    var html = "<li class='dd-item dd3-item'><div class='dd-handle dd3-handle'></div><div class='dd3-content'></div>' <ol class='dd-list'></ol></li>";
    var template = $("<div/>").html(html);

    for (var i = 0, len = skills.length; i < len; i++) {

        var item = template.clone();
        var name = item.find("ol");
        var id = item.find("id");

        var skill = skills[i];

        id.attr("data-id", skill.id);
        item.find(".dd3-content").html(skill.title);
        if (skill.name) {
            name.attr("data-name", skill.name);
        } else
        {
            name.remove();
        }

        //Determine parent name
        var parentName = "root";
        if (skill.path) {
            var path = skill.path.split(",");
            parentName = path[path.length - 2];
        }

        //Find parent and insert item element
        var parent = rootElement.find("ol[data-name='" + parentName + "']");
        parent.append(item.html());
    }

    $('#nestable').nestable();
    $('.dd').nestable('collapseAll');
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
    /*  $('#nestable').nestable().on('change', updateOutput);

     // output initial serialised data
     updateOutput($('#nestable').data('output', $('#nestable-output')));*/

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



