function deleteMessage(id){    
    confirm_ = confirm('This action will delete current order! Are you sure?');
    if(confirm_){
    	var rows = $(".list-page").attr("rows");
    	$("#tr_"+id).fadeOut("slow");
        $.ajax({
            type:"GET",
            url:"ajax/CommentManage.action?id="+id,
            success:function(msg){
//            	$("#tr_"+id).fadeOut("slow");
            }
        });
    }
}
function auditing(id,var2){
	var flag = var2;
	if(flag == 1){
		confirm_ = confirm('确定审核通过？');
	    if(confirm_){
	    	$("#tr_"+ id +"-through").text("1");
        	$("#tr_"+ id +"-auditing").text("1");
	        $.ajax({
	            type:"GET",
	            url:"ajax/CommentManage.action",
	            data:"id=" + id+ "&flag=" + 1,
	            success:function(msg){
//	            	$("#tr_"+ id +"-through").text("1");
//	            	$("#tr_"+ id +"-auditing").text("1");
	            }
	        });
	    }
	}
	else{
		confirm_ = confirm('确定审核失败？');
	    if(confirm_){
	    	$("#tr_"+ id +"-through").text("0");
        	$("#tr_"+ id +"-auditing").text("1");
	        $.ajax({
	            type:"GET",
	            url:"ajax/CommentManage.action",
	            data:"id=" + id+ "&flag=" + 0,
	            success:function(msg){
//	            	$("#tr_"+ id +"-through").text("0");
//	            	$("#tr_"+ id +"-auditing").text("1");
	            }
	        });
	    }
	}
}
$(document).ready(function() { 
	// 全选 
	$("#allMessageChoose").click(function() { 
		$("input[name='messageIds']").prop("checked",this.checked);
	}); 
	// 单选 
	var subChk = $("input[name='messageIds']"); 
	subChk.click(function() { 
		$("#allChk").prop("checked", subChk.length == subChk.filter(":checked").length ? true:false); 
	}); 
	/* 批量删除 */ 
	$("#messageBatchDel").click(function() { 
		// 判断是否至少选择一项 
		var checkedNum = $("input[name='messageIds']:checked").length; 
		if(checkedNum == 0) { 
			alert("请选择至少一项！"); 
			return; 
		} 
		// 批量选择 
		if(confirm("确定要删除所选项目？")) { 
			var rows = $(".list-page").attr("rows");
			var checkedList = new Array(); 
			var id;
			var i = 0;
			$("input[name='messageIds']:checked").each(function() { 
				id = $(this).val();
				checkedList.push(id);
				//$("#tr_"+id).remove();
				$("#tr_"+id).fadeOut("slow");
				rows--;
			}); 
			$("[name ='articleIds']:checkbox").attr("checked", false);
			$.ajax({ 
				type: "GET", 
				url: "ajax/CommentManage.action", 
				data:"ids=" + checkedList.toString(),
				success: function(msg) { 
//					$("[name ='articleIds']:checkbox").attr("checked", false); 
				} 
			}); 
		} 
	}); 
	$("#messageBatchThrough").click(function() { 
		// 判断是否至少选择一项 
		var checkedNum = $("input[name='messageIds']:checked").length; 
		if(checkedNum == 0) { 
			alert("请选择至少一项！"); 
			return; 
		} 
		// 批量审核
		if(confirm("确定要审核所选项目？")) { 
			var checkedList = new Array(); 
			var id;
			var i = 0;
			$("input[name='messageIds']:checked").each(function() { 
				id = $(this).val();
				checkedList.push(id);
				$("#tr_"+ id +"-through").text("1");
            	$("#tr_"+ id +"-auditing").text("1");
			}); 
			$("[name ='articleIds']:checkbox").attr("checked", false); 
			$.ajax({ 
				type: "GET", 
				url: "ajax/CommentManage.action", 
				data:"ids=" + checkedList.toString() + "&flag=1",
				success: function(msg) { 
//					$("[name ='articleIds']:checkbox").attr("checked", false); 
				} 
			}); 
		} 
	});
});