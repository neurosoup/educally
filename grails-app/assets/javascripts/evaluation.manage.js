//= require plugin/jquery-nestable/jquery.nestable
//= require plugin/pace/pace
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */

var buildSkillTree = function (data, root, nodeTemplate) {

    var skills = JSON.parse(data);

    console.log(skills)

    var nodeElement = $("<div/>").html(nodeTemplate);
    var rootElement = $(root);

    for (var i = 0, len = skills.length; i < len; i++) {

        var node = nodeElement.clone();
        var nameHolder = node.find("ol");
        var idHolder = node.find("li");
        var contentHolder = node.find(".inner-content");

        var skill = skills[i];

        idHolder.attr("data-id", skill.id);
        contentHolder.html(skill.title);

        contentHolder.popover({
            container: 'body'
        });

        if (skill.name) {
            nameHolder.attr("data-name", skill.name);
        } else {
            nameHolder.remove();
        }

        //Determine parent name
        var parentName = "root";
        if (skill.path) {
            var path = skill.path.split(",");
            parentName = path[path.length - 2];
        }

        //Find parent and insert item element
        var parent = rootElement.find("ol[data-name='" + parentName + "']");
        parent.append(node.html());
    }

    //Initialize nestable
    var nestable = $('#nestable');

    nestable.nestable();

    $('.inner-content').each(function () {
        var $element = $(this);

        $element.truncate({
            lines: 1,
            lineHeight: 20
        });
    });

    nestable.nestable('collapseAll');

    nestable.mCustomScrollbar({
        axis: "y",
        theme: "dark-3"
    });

    resizeContent();

};

var resizeContent = function () {

    var height = $(window).height();
    var nestable = $('#nestable');

    nestable.css('height', height / 3);
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

$(window).resize(resizeContent);



