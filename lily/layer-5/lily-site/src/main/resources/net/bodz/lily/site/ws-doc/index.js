$(document).ready(function() {

    $(document.body).keydown(function (e) {
        var el = $(e.target);
        if (el.hasClass("editable")) return;
        if (el.is("input, select")) return;
        
        switch (e.keyCode) {
        case 27: // ESC
        case 32: // SPC
        case 46: // DEL
            return;
        }
    });

    $(".list-cmds .cmds a").click(function (e) {
        var $a = $(this);
        var cmd = $a.attr("href");
        var $table = $a.closest(".list-cmds").find(".dataTable");
        var model = $table.DataTable();
        
        switch (cmd) {
            default:
                return;
        }
        return false;
    });
    
    //fullScreen();
    
});
