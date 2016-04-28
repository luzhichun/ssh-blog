$(document).ready(function(){
	$("#columnSerach").click(function(){
		var selectTag = $("#select-serach");
		var slectValue = selectTag.val();
		//alert(slectValue);
		//var trId = trTag.attr("id");
		//deleteArticle(trId);
	});
});
function deleteArticle(id){    
    confirm_ = confirm('确定要删除该文章?');
    if(confirm_){
    	var rows = $(".list-page").attr("rows");
    	$("#tr_"+id).fadeOut("slow");
        $.ajax({
            type:"GET",
            url:"ajax/ArticleManage.action?id="+id,
            success:function(msg){
//            	$("#tr_"+id).fadeOut("slow");
            }
        });
    }
}
$(document).ready(function() { 
	//文章管理
	// 全选 
	$("#allArticleChoose").click(function() { 
		$("input[name='articleIds']").prop("checked",this.checked); 
	}); 
	// 单选 
	var subChk = $("input[name='articleIds']"); 
	subChk.click(function() { 
		$("#allChk").prop("checked", subChk.length == subChk.filter(":checked").length ? true:false); 
	}); 
	/* 批量删除 */ 
	$("#articleBatchDel").click(function() { 
		// 判断是否至少选择一项 
		var checkedNum = $("input[name='articleIds']:checked").length; 
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
			$("input[name='articleIds']:checked").each(function() { 
				id = $(this).val();
				checkedList.push(id);
				//$("#tr_"+id).remove();
				$("#tr_"+id).fadeOut("slow");
				rows--;
			}); 
			$("[name ='articleIds']:checkbox").attr("checked", false); 
			$.ajax({ 
				type: "GET", 
				url: "ajax/ArticleManage.action?ids=" + checkedList.toString(), 
				success: function(msg) { 
//					$("[name ='articleIds']:checkbox").attr("checked", false); 
				} 
			}); 
		} 
	}); 
}); 
$(document).ready(function(){
	  $("input[name='uploadFile']").change( function(){
		  //alert("form已经改变");
		  var filepath=$("input[name='uploadFile']").val();
		  if(filepath == ""){
			  //表示未上传文件
			  return false;
		  }
		  var extStart=filepath.lastIndexOf(".");
		  var ext=filepath.substring(extStart,filepath.length).toUpperCase();
		  if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			  alert("图片限于bmp,png,gif,jpeg,jpg格式");
			  $("input[name='uploadFile']").val("");//取消该文件
			  return false;
		  }
		  var file_size = 0;
		  if ( $.browser.msie) {
			  //表示ie浏览器
			  var img=new Image();
			  img.src=filepath;
			  while(true){
				  if(img.fileSize > 0){
					  if(img.fileSize>2*1024){
						  alert("图片大小不能超过2MB");
						  $("input[name='uploadFile']").val("");//取消该文件
						  return false;
					  }
					  break;
				  }
			  }
		  } else {
			  //其他浏览器
			  file_size = this.files[0].size;
			  var size = file_size / 1024;
			  if(size > 2048){
				  alert("图片大小不能超过2MB");
				  $("input[name='uploadFile']").val("");//取消该文件
				  return false;
			  }
		  }
		  return true;
		  });
	 });
function deleteMessage(id){    
    confirm_ = confirm('确定要删除所选留言？');
    if(confirm_){
    	var rows = $(".list-page").attr("rows");
    	$("#tr_"+id).fadeOut("slow");
        $.ajax({
            type:"GET",
            url:"ajax/MessageManage.action?id="+id,
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
	            url:"ajax/MessageManage.action",
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
	            url:"ajax/MessageManage.action",
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
				url: "ajax/MessageManage.action", 
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
				url: "ajax/MessageManage.action", 
				data:"ids=" + checkedList.toString() + "&flag=1",
				success: function(msg) { 
//					$("[name ='articleIds']:checkbox").attr("checked", false); 
				} 
			}); 
		} 
	});
});