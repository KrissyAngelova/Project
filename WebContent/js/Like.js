$(function() {
    $('.like-button').click(function(){
        var obj = $(this);
        if( obj.data('liked') ){
            obj.data('liked', false);
            obj.html('Like');
        }
        else{
            obj.data('liked', true);
            obj.html('Unlike');
        }
    });
});