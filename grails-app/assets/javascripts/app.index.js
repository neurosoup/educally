//= require plugin/storageapi/jquery.storageapi
//= require_self


function initializeCachedLink(skillBookId) {

    var modelKey = 'evaluation.model.skillBookId' + skillBookId;
    var statusKey = modelKey + '.status';

    if (!$.localStorage.isSet(statusKey)) {
        $.localStorage.set(statusKey, 'unset')
    }

    var link = $('#evaluation-link');
    var href = link.attr('href');

    if ($.localStorage.get(statusKey) == 'set') {
        href = href + '&localStorage=1';
    } else {
        href = href + '&localStorage=0';
    }

    link.attr('href', href);
}


