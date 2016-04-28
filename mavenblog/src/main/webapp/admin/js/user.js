function auditingUser(id,flag){  
	//封禁用户
	if(flag == 1){
		confirm_ = confirm('确定审核通过该用户?');
	}
	else{
		confirm_ = confirm('确定封禁该用户?');
	}
    if(confirm_){
        $.ajax({
            type:"GET",
            url:"ajax/UserManage.action?id="+id+"&flag="+flag,
            success:function(msg){
            	$("#tr_"+ id +"-through").text(flag);
            }
        });
    }
}
$(document).ready(function() { 
	//用户管理
	// 全选 
	$("#allUserChoose").click(function() { 
		$("input[name='userIds']").prop("checked",this.checked); 
	}); 
	// 单选 
	var subChk = $("input[name='userIds']"); 
	subChk.click(function() { 
		$("#allChk").prop("checked", subChk.length == subChk.filter(":checked").length ? true:false); 
	}); 
	/* 批量封禁 */ 
	$("#userBatchClosure").click(function() { 
		// 判断是否至少选择一项 
		var checkedNum = $("input[name='userIds']:checked").length; 
		if(checkedNum == 0) { 
			alert("请选择至少一项！"); 
			return; 
		} 
		// 批量选择 
		if(confirm("确定要封禁所选用户？")) { 
			batchAuditing(0) 
		} 
	}); 
	$("#userBatchThrough").click(function() { 
		// 判断是否至少选择一项 
		var checkedNum = $("input[name='userIds']:checked").length; 
		if(checkedNum == 0) { 
			alert("请选择至少一项！"); 
			return; 
		} 
		// 批量选择 
		if(confirm("确定要审核所选用户？")) { 
			batchAuditing(1) 
		} 
	});
}); 
function batchAuditing(flag){
	var checkedList = new Array(); 
	var id;
	var i = 0;
	$("input[name='userIds']:checked").each(function() { 
		id = $(this).val();
		checkedList.push(id);
		$("#tr_"+ id +"-through").text(flag);
	}); 
	$.ajax({ 
		type: "GET", 
		url: "ajax/UserManage.action?flag=" + flag + "&ids=" + checkedList.toString(), 
		success: function(msg) { 
			$("[name ='userIds']:checkbox").attr("checked", false); 
		} 
	}); 
}