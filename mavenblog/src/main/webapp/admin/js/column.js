$(document).ready(function(){
	$("#add-column").click(function(){
		$("#ds-wrapper").fadeToggle();
	});
	$("#cancel").click(function(){
		$("#ds-wrapper").hide(500);
	});
	$("#submit").click(function(){
		var pid = $("#parentColumn").val();
		var columnName = $("#columnNname").val();
		var linkName = $("#linkName").val();
		$.ajax({ 
			type: "post", 
			url: "ajax/ColumnManage.action", 
			data:"pid=" + pid+ "&columnName=" + columnName+ "&linkName=" + linkName +"&what=addColumn",
			success: function(msg) { 
				alert("栏目添加成功...");
				$("#ds-wrapper").hide();
			} 
		});
	});
});