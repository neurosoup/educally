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

    var itemHtml = "<li class='dd-item'><div class='dd-handle'></div></li>";
    var listHtml = "<ol class='dd-list'></ol>";
    var itemTemplate = $("<div/>").html(itemHtml);
    var listTemplate = $("<div/>").html(rootHtml);

    //console.log(skills);

    for (var i = 0, len = skills.length; i < len; i++) {

        var itemElement = itemTemplate.clone();
        var listElement = listTemplate.clone();
        var itemDataElement = itemElement.find("li");
        var listDataElement = itemElement.find("ol");

        var skill = skills[i];

        itemDataElement.attr("data-id", skill.id);
        itemElement.find(".dd-handle").html(skill.title);
        if (skill.name) {
            itemDataElement.attr("data-name", skill.name);
            listDataElement.attr("data-name", skill.name);
        }

        //Determine parent name
        var parentName = "";
        var parent = null;
        if (skill.path) {
            var path = skill.path.split(",");
            parentName = path[path.length - 2];
        } else {
            parentName = "root-list";
        }

        //Find parent and insert item element
        parent = rootElement.find("li[data-name='" + parentName + "']");
        if (!parent) {
            parent = rootElement.append(listElement.html());
        }
        parent.append(itemElement.html());


        //console.log("name=" + skill.name + " parent=" + parentName + "\n parent=" + parent+ " html=" + parent.html());
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



