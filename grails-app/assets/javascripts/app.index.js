//= require_self


$(document).ready(function () {

    if ($.localStorage.isSet('skills')) {
        var link = $('#evaluations-manage');
        var href = link.attr('href');
        href = href + '&initialized=1';
        link.attr('href', href);
    }

});

