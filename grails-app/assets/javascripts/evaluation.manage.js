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
    nestable.nestable('collapseAll');
    nestable.mCustomScrollbar({
        axis: "y",
        theme: "dark-3"
    });

    /* $("[data-action='expand']").click(function (e) {
     truncNestable($(e.target).siblings("ol:first"));
     });*/

    $(".dd-item").on('change', function (e) {
        truncNestable($(e.target).children("ol.dd-list"));
    });

    truncNestable($("[data-name=root]"));

    resizeNestable();
};

var truncNestable = function (ol) {
    console.log("truncNestable called");

    var li = ol.children("li");
    console.log(li);

    var maxWidth = $('.dd3-item:first').width();
    li.each(function (index, item) {

        var element = $(item);

        if (element.width() > 100) {

            /*while (element.width() > (maxWidth - 100)) {
                var text = element.text() + '...';
                var last = text.lastIndexOf(" ")
                text = text.substring(0, last) + '...';
                element.text(text);
            }*/
        }

    });
}

var resizeNestable = function () {

    var nestable = $('#nestable');
    var height = $(window).height();
    nestable.css('max-height', height / 2);
    //truncNestable($("[data-name=root]"));
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

$(window).resize(resizeNestable);



