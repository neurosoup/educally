//= require plugin/jquery-nestable/jquery.nestable
//= require plugin/pace/pace
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */

var initializeSkillExplorer = function (data, root, nodeTemplate) {

    var skills = JSON.parse(data);

    //console.log(skills)

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
            idHolder.addClass("leaf");
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

    var nestable = $('#nestable');
    nestable.nestable();
    nestable.nestable('collapseAll');
    nestable.mCustomScrollbar({
        axis: "y",
        theme: "dark-thin",
        scrollButtons: {
            enable: true
        },
        scrollbarPosition: 'inside',
        alwaysShowScrollbar: 1
    });

    $(".dd-item").on('change', function (e, data) {
        if (data.action == 'expand') {
            truncSkillsTitle($(e.target).children("ol.dd-list"));
        }
    });

    $(".dd-item").on('click', function () {
        var element = $(this);
        var id = element.attr('data-id');
        $(this).addClass('item-selected');

        //console.log('id='+id);
        //console.log($('.dd3-item.leaf.item-selected:not([data-id=id])'));
        //$('.dd3-item.leaf.item-selected:not([data-id=id])').removeClass('item-selected');

    });

    truncSkillsTitle($("[data-name=root]"));

};

var truncSkillsTitle = function (ol) {

    var li = ol.children("li.leaf");

    var maxHeight = 50;
    li.each(function (index, item) {

        var element = $(item);
        var content = element.find(".inner-content:first");

        var originalText = content.attr("data-original");
        if (!originalText) {
            content.attr("data-original", content.text());
        }

        if (element.height() > maxHeight) {

            while (element.height() > maxHeight) {
                var text = content.text() + '...';
                var last = text.lastIndexOf(" ")
                text = text.substring(0, last) + '...';
                content.text(text);
            }
        }

    });
};

var pagefunction = function () {

    var parent = $('#wid-id-0');
    var nestable = $('#nestable');
    var maxHeight = parent.height();

    console.log("parent height=" + parent.height());

    nestable.height(maxHeight);

    parent.resize(function () {
        var nestable = $('#nestable');
        var parent = $(this);
        var maxHeight = parent.height();

        console.log("widget height=" + maxHeight);

        /*nestable.css('height', newHeight);
        nestable.css('max-height', newHeight);*/
        nestable.height(maxHeight);
    })


};

// end pagefunction

pagefunction();




