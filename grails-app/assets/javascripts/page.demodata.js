//= require flot
//= require vectormap
//= require fullcalendar
//= require_self

pageSetUp();

/*
 * PAGE RELATED SCRIPTS
 */


var resetData = function (url) {

    $.ajax({
        type:'POST',
        data:jQuery(this).parents('form:first').serialize(),
        url:url,
        success:function(data,textStatus){},
        error:function(XMLHttpRequest,textStatus,errorThrown){}
    });
    return false;
};



