$(document).ready(function() {
	$(".save").on("click", function(){
		var id = $(this).data("id");
	});

	$(".edit").on("click", function(){
		var id = $(this).data("id");
		$("#edit_in_"+id).show();
		$("#show_"+id).hide();
		
		$("#edit_"+id).hide();
		$("#save_"+id).show();
	});
	
	$(".delete").on("click", function(){
		var id = $(this).data("id");
	});
});