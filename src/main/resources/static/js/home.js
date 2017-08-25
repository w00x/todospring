$(document).ready(function() {
	$(".save").on("click", function(){
		var id = $(this).data("id");
		var description = $("#edit_in_"+id).val();
        var checked = $("#checkid_"+id).prop( "checked" );

        $.ajax({
            url: "/api/v1/todo",
            method: "PUT",
            contentType: "application/json",
			data: JSON.stringify({ id: id, done: checked, description: description }),
            success: function() {
                $("#show_"+id).html(description);
                disable_edit(id);
            }
        });
	});

	$(".edit").on("click", function(){
		var id = $(this).data("id");
        enable_edit(id);
	});
	
	$(".delete").on("click", function(){
		var id = $(this).data("id");

        $.ajax({
            url: "/api/v1/todo/"+id,
            method: "DELETE",
            contentType: "application/json",
            success: function() {
                $("#tr_"+id).remove();
            }
        });
	});

	$(".check").on("change", function() {
        var id = $(this).data("id");
        var checked = $(this).prop( "checked" );
        var description = $("#edit_in_"+id).val();

        $.ajax({
            url: "/api/v1/todo",
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify({ id: id, done: checked, description: description }),
            success: function() {}
        });
	});
});

var enable_edit = function(id) {
    $("#edit_in_"+id).show();
    $("#show_"+id).hide();

    $("#edit_"+id).hide();
    $("#save_"+id).show();
};

var disable_edit = function(id) {
    $("#edit_in_"+id).hide();
    $("#show_"+id).show();

    $("#edit_"+id).show();
    $("#save_"+id).hide();
}