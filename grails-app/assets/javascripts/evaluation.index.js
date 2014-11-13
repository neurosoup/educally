//= require plugin/easy-pie-chart/jquery.easy-pie-chart
//= require plugin/storageapi/jquery.storageapi
//= require plugin/jquery-nestable/jquery.nestable
//= require plugin/pace/pace
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */


var buildSkillExplorer = function (skillsData, skillExplorerRoot, skillTemplate, getEvaluationUrl) {

    var skills = JSON.parse(skillsData);

    var nodeElement = $("<div/>").html(skillTemplate);
    var rootElement = $(skillExplorerRoot);

    for (var i = 0, len = skills.length; i < len; i++) {

        var node = nodeElement.clone();
        var nameHolder = node.find("ol");
        var linkHolder = node.find("li");
        var contentHolder = node.find(".inner-content");

        var skill = skills[i];

        var evaluationCount = 0;
        var averageRating = 0;
        if (skill.stats) {
            evaluationCount = skill.stats.evaluationCount;
            averageRating = skill.stats.averageRating;
        }

        linkHolder.attr("data-id", skill.id);
        linkHolder.attr("data-url", getEvaluationUrl + "?skillId=" + skill.id);
        contentHolder.html(skill.title);
        if (evaluationCount > 0) {
            var color = 'txt-color-green';
            if (0 <= averageRating && averageRating < 0.25) color = 'color-red';
            if (0.25 <= averageRating && averageRating < 0.5) color = 'color-orangeDark';
            if (0.5 <= averageRating && averageRating < 0.75) color = 'color-yellow';
            if (0.75 <= averageRating && averageRating < 1) color = 'color-green';
            //var stat = "<div class='easy-pie-chart txt-" + color + "' data-percent='" + averageRating * 100 + "'><span>" + evaluationCount + "</span></div>";
            var stat = "<em class='badge pull-right stat-count bg-" + color + "'>" + evaluationCount + "</em>";
            contentHolder.parent().append(stat)
        }

        if (skill.name) {
            nameHolder.attr("data-name", skill.name);
        } else {
            linkHolder.addClass("leaf");
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

    //Click on a skill in skill explorer
    $(".inner-content").on('click', function () {
        var $this = $(this);

        //Remove previous selection
        $this.parents('[data-name=root]').find('li.leaf').removeClass('item-selected');

        var parent = $(this).parents('.dd-item:first');
        var id = parent.data('id');

        var folder = $(this).parents('.dd-item').first();
        if (!folder.hasClass('leaf')) {
            if (folder.hasClass('dd-collapsed')) {
                $('.dd').nestable('expandItem', folder);
            } else {
                $('.dd').nestable('collapseItem', folder);
            }
        }

        //Show clicked skill as selected item
        parent.addClass('item-selected');

        //Fill with evaluations summary
        var url = parent.data('url');
        $('#skill-' + id).children('.evaluation-content').first().load(url);

        //Activate corresponding evaluation tab
        $("#evaluationTabs").find("a[href='#skill-" + id + "']").tab('show');

    });

    truncSkillsTitle($("[data-name=root]"));

    if ($.fn.easyPieChart) {

        $('.easy-pie-chart').each(function () {

            console.log(this);

            var $this = $(this),
                barColor = $this.css('color') || $this.data('pie-color'),
                trackColor = $this.data('pie-track-color') || '#eeeeee',
                size = parseInt($this.data('pie-size')) || 25;

            $this.easyPieChart({

                barColor: barColor,
                trackColor: trackColor,
                scaleColor: false,
                lineCap: 'butt',
                lineWidth: parseInt(size / 6),
                animate: 1500,
                rotate: -90,
                size: size/*,
                 onStep: function (value) {
                 this.$el.find('span').text(~~value);
                 }*/

            });

            $this = null;
        });

    } // end if

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
                var last = text.lastIndexOf(" ");
                text = text.substring(0, last) + '...';
                content.text(text);
            }
        }

    });
};

function fitHeight(selector) {

    var resizedElement = $(selector).find('div[role=content]');
    var maxHeight = 0;

    if ($('body').hasClass('menu-on-top')) {
        var menuHeight = 68;
        // nav height

        maxHeight = ($(window).height() - 224) - menuHeight;
        if (maxHeight < (320 - menuHeight)) {
            resizedElement.css('height', (320 - menuHeight) + 'px');
        } else {
            resizedElement.css('height', maxHeight + 'px');
        }

    } else {
        maxHeight = $(window).height() - 224;
        if (maxHeight < 320) {
            resizedElement.css('height', 320 + 'px');
        } else {
            resizedElement.css('height', maxHeight + 'px');
        }

    }
}

function fitNestable(selector) {

    var parent = $(selector).find('div[role=content]');

    var toolBar = parent.find('.widget-body-toolbar');
    var nestable = parent.find('#nestable');

    var maxHeight = parent.height() - toolBar.height() - 4;
    nestable.height(maxHeight);
}

var pagefunction = function () {

    var selector = '#wid-id-0';

    fitHeight(selector);
    fitNestable(selector);

    $(window).resize(function () {
        fitHeight(selector);
        fitNestable(selector);
    });

    //Click somewhere on skill in skill explorer -> activate panel
    $('.dd').on('click', function () {
        var $this = $(this);

        var skillsPanel = $this.closest('.skills-nav');
        var evaluationsPanel = $this.parent().siblings('.evaluations-nav');

        skillsPanel.addClass('activate');
        skillsPanel.css('white-space', 'normal');

        evaluationsPanel.addClass('activate');
    });

    //Skill explorer mingifyer
    $(selector).on('click', '[data-action="minifySkills"]', function (e) {
        var $this = $(this);

        var skillsPanel = $this.closest('.skills-nav');
        var evaluationsPanel = $this.parent().siblings('.evaluations-nav');

        skillsPanel.toggleClass('activate');
        evaluationsPanel.toggleClass('activate');

        if (skillsPanel.hasClass('activate')) {
            skillsPanel.css('white-space', 'normal');
        } else {
            skillsPanel.css('white-space', 'nowrap');
        }

        e.preventDefault();

        //clear memory reference
        $this = null;
    });


};

// end pagefunction

pagefunction();




