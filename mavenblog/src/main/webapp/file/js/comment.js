function validEmail(emailAddress){
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!reg.test(emailAddress)) {
		return false;
	}
	return true;
}
function validateNull(idValue,hintName){
	//验证评论是否为空
	var value = $(idValue).val();
	if(value == null || value == ""){
		$(idValue + "-hint").text(hintName + "不能为空");
		return true;
	}
	return false;
}

function validateMTLen(idValue,hintName,len){
	//验证是否超过指定长度
	var value = $(idValue).val();
	if(value.length >= len){
		$(idValue + "-hint").text( hintName+ "长度不能超过" + len + "个字符");
		return true;
	}
	else{
		$(idValue + "-hint").text("");
		return false;
	}	
}
$(document).ready(function(){
	$("#comment-button").click(function(){
		if($("#comment-textarea").attr("readonly")){
			alert("请登录后再评论");
			return ;
		}
		if(validateNull("#comment-textarea","评论内容")){
			//评论内容为空
			return ;
		}
		if(validateMTLen("#comment-textarea","评论内容",250)){
			//评论内容长度不合法
			return ;
		}
		var textarea = $("#comment-textarea").val();
		var article_id = $("#comment-article_id").val();
		alert("评论成功,等待博主审核");
    	$("#comment-username").val("");
		$("#comment-email").val("");
		$("#comment-textarea").val("");
		$.ajax({
            type:"POST",
            url:"ajax/AddComment.action",
            data:"&article_id=" + article_id + "&content=" + textarea,
            success:function(msg){
//            	alert("评论成功,等待博主审核");
//            	$("#comment-username").val("");
//        		$("#comment-email").val("");
//        		$("#comment-textarea").val("");
            }
        });
});
	$("#comment-username").blur(function(){
		if(validateNull("#comment-username","昵称")){
			//用户名为空
			return ;
		}
		if(validateMTLen("#comment-username","昵称",10)){
			//用户名长度不合法
			return ;
		}
	});
	$("#comment-email").blur(function(){
		if(validateNull("#comment-email","邮箱")){
			//邮箱为空
			return ;
		}
		if(validateMTLen("#comment-email","邮箱",20)){
			//邮箱长度不合法
			return ;
		}
		if(!validEmail($("#comment-email").val())){
			//邮箱地址无效
			$("#comment-email-hint").text("请输入有效的邮箱地址");
			return ;
		}
	});
	$("#comment-textarea").blur(function(){
		if($("#comment-textarea").attr("readonly")){
			return ;
		}
		if(validateNull("#comment-textarea","评论内容")){
			//评论内容为空
			return ;
		}
		if(validateMTLen("#comment-textarea","评论内容",250)){
			//评论内容长度不合法
			return ;
		}
	});
});
function light(obj){
	//顶评论
	var jobj = $(obj);
	var comment_id = jobj.attr("comment-id");
	var value = jobj.children().next().text();
	$.ajax({
        type:"GET",
        url:"ajax/CommentLight.action?id="+comment_id,
        success:function(msg){
        	jobj.children().next().text(++value);
        }
    });
}
function reply(id){
	//展开回复留言div
	$(".ds-post-self#" + id).toggle();
}
function reply1(obj){
	//展开回复评论div
	var jobj = $(obj);
	jobj.parent().next().toggle();
}
function submitReply(obj){
	//提交回复的评论,如果台页面发生变化，该函数需要修改
	if($(obj).parent().prev().prev().attr("readonly")){
		alert("请登录后再评论");
		return ;
	}
	var content = $(obj).parent().prev().prev();
	if(!validateTextarea(content)){
		return false;
	}
	var textareaValue = content.val();
	var article_id = $("#comment-article_id").val();
	var reply_id = $(obj).parent().prev().prev().prev().prev().prev().val();
	var parent_id = $(obj).parent().prev().prev().prev().prev().prev().prev().val();
	alert("评论成功,等待博主审核");
	$(obj).parent().parent().parent().parent().parent().hide();
	$(obj).parent().prev().prev().val("");
	$.ajax({
        type:"POST",
        url:"ajax/AddComment.action",
        data:"&article_id=" + article_id + "&content=" + textareaValue + "&reply_id=" + reply_id + "&parent_id=" + parent_id,
        success:function(msg){
//        	alert("回复评论成功,等待博主审核");
//    		$("#comment-textarea").val("");	
        }
    });
}
function validateNull1(obj,nexts,hintName){
	//验证回复评论是否为空
	var value = $(obj).val();
	if(value == null || value == ""){
		var hint = $(obj);
		for(var i = 0;i < nexts;i++){
			hint = hint.next();
		}
		$(hint).text(hintName + "不能为空");
		return true;
	}
	return false;
}
function validateMTLen1(obj,nexts,hintName,len){
	//验证回复评论是否超过指定长度
	var value = $(obj).val();
	var hint = $(obj);
	for(var i = 0;i < nexts;i++){
		hint = hint.next();
	}
	if(value.length >= len){
		hint.text( hintName+ "长度不能超过" + len + "个字符");
		return true;
	}
	else{
		hint.text("");
		return false;
	}	
}
function validateUsername(obj){
	if(validateNull1(obj,2,"昵称")){
		//昵称为空
		return false;
	}
	if(validateMTLen1(obj,2,"昵称",10)){
		//昵称长度
		return false;
	}
	return true;
}
function validateEmail(obj){
	//验证回复评论的邮箱
	if(validateNull1(obj,2,"邮箱")){
		//邮箱为空
		return false;
	}
	if(validateMTLen1(obj,2,"邮箱",20)){
		//邮箱长度
		return false;
	}
	if(!validEmail($(obj).val())){
		//邮箱地址无效
		$(obj).next().next().text("请输入有效的邮箱地址");
		return false;
	}else{
		$(obj).next().next().text("");
		return true;
	}
}
function validateTextarea(obj){
	//验证回复评论的内容
	if($(obj).attr("readonly")){
		return ;
	}
	var value = $(obj).val();
	var jobj = $(obj);
	if(value == null || value == ""){
		//回复内容为空
		jobj.next().next().children("label#textarea-hint").text("回复内容不能为空");
		return false;
	}
	if(value.length > 250){
		//回复内容长度
		jobj.next().next().children("label#textarea-hint").text("回复内容不能超过250个字符");
		return false;
	}
	jobj.next().next().children("label#textarea-hint").text("");
	return true;
}