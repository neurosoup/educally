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

    var html = "<ol class='dd-list'><li class='dd-item'><div class='dd-handle'></div></li></ol>";
    var itemTemplate = $("<div/>").html(html);

    //console.log(skills);

    for (var i = 0, len = skills.length; i < len; i++) {

        var itemElement = itemTemplate.clone();

        var skill = skills[i];

        var dataElement = itemElement.find("ol");

        dataElement.attr("data-id", skill.id);
        itemElement.find(".dd-handle").html(skill.title);

        if (skill.name) {
            dataElement.attr("data-name", skill.name)
        }

        if (skill.path) {
            var path = skill.path.split(",");
            var parentName = path[path.length - 2];
            var parentCandidate = rootElement.find("[data-name='" + parentName + "']");
            var parent = parentCandidate;

            if (parentCandidate.is("ol")) {
                parent = parentCandidate.children().first();
                parentCandidate.attr("data-name", "");
                parent.attr("data-name", parentName);
            }
            parent.append(itemElement.html());

            //console.log("name=" + skill.name + " parent=" + parentName + "\n parent=" + parent+ " html=" + parent.html());


        } else {
            var newRoot = itemElement.children().first();
            newRoot.find("li").attr("data-name", skill.name)
            rootElement.append(newRoot.html());
            console.log(newRoot.html());
        }
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



