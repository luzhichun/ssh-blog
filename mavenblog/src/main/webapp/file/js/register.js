function validEmail(emailAddress){
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!reg.test(emailAddress)) {
		return false;
	}
	return true;
}
function validateNull(idValue,hintName){
	//验证是否为空
	var value = $(idValue).val();
	if(value == null || value == ""){
		$(idValue + "-hint p").text(hintName + "不能为空");
		return true;
	} else{
		$(idValue + "-hint p").text("");
		return false;
	}
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
function setFormState(idValue,state){
	//设置表单状态
	var tag = $(idValue).parent().parent();
	var a= tag.attr("class");
	if(a.indexOf("form-group")>=0)
	{
		tag.attr("class","form-group has-" +state);
	    return ;
	}
	if(a.indexOf("form-inline")>=0)
	{
		tag.attr("class","form-inline has-" +state);
		 return ;
	}
}
function validateLength(idValue,minLength,maxLength){
	var length = $(idValue).val().length;
	if(length >= minLength && length <= maxLength){
		return true;
	}
	$(idValue + "-hint p").text("长度不合法");
	return false;
}
function isSuccess(idValue){
	//input状态是否输入成功
	var state = $(idValue).parent().parent().attr("class");
	if(state.indexOf(" has-success")>=0)
	{
	   return true;
	}
	else
	{
		 return false;
	}
//	if(state == "form-group has-success"){
//		//alert(idValue);
//		return true;
//	}
//	else{
//		return false;
//	}
}
$(document).ready(function (){
	$('#get-validate-code').click(function () {
		if(validateNull("#email","邮箱")){
			//邮箱地址为空
			setFormState("#email","error");
			return ;
		}
		if(!validEmail($("#email").val())){
			//邮箱地址无效
			$("#email-hint p").text("请输入有效的邮箱地址");
			setFormState("#email","error");
			return ;
		}
		var email = $("#email").val();
		var params = {
				email : email
		        };
		$.ajax({
		    type: "POST",
		    url: "ajax/sendVlidatecode.action",
		    data: params,
		    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
		    success: function(json){ 
			    var obj = $.parseJSON(json);  //使用这个方法解析json
			    var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
			    //alert(state_value);
			    if(state_value == "验证码发送成功!"){
			    	var count = 60;
			        var countdown = setInterval(CountDown, 1000);
			        function CountDown() {
			            $("#get-validate-code").attr("disabled", true);
			            $("#get-validate-code").text(count + " 秒后可以再次获取!");
			            if (count == 0) {
			            	$("#get-validate-code").text("获取邮箱验证码").removeAttr("disabled");
			                clearInterval(countdown);
			            }
			            count--;
			        }
			    }
		    },
		    error: function(json){
		     alert("json=" + json);
		     return false;
		    }
		    });
    });
	$("#username").blur(function (){
		if(validateNull("#username","用户名") || !validateLength("#username",2,10)){
			setFormState("#username","error");
			return ;
		}
		else{
			setFormState("#username","success");
			var username = $("#username").val();
			var params = {
			           username : username
			        };
			$.ajax({
			    type: "POST",
			    url: "ajax/checkUsername.action",
			    data: params,
			    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
			    success: function(json){ 
				    var obj = $.parseJSON(json);  //使用这个方法解析json
				    var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
				    //alert(state_value);
				    if(state_value == "该用户名已存在！"){
				    	 setFormState("#username","error");
						 $("#username-hint p").text("该用户名已存在，请重新输入");
						 
				    }
			    },
			    error: function(json){
			     alert("json=" + json);
			     return false;
			    }
			    });
			return ;
		}
	});
	$("#vlidate-code").blur(function (){
		if(validateNull("#vlidate-code","验证码")){
			setFormState("#vlidate-code","error");
			return ;
		}
		else{
			setFormState("#vlidate-code","success");
			var vlidatecode = $("#vlidate-code").val();
			var params = {
					vlidatecode : vlidatecode
			        };
			$.ajax({
			    type: "POST",
			    url: "ajax/emailVlidate.action",
			    data: params,
			    dataType:"text", //ajax返回值设置为text（json格式也可用它返回，可打印出结果，也可设置成json）
			    success: function(json){ 
				    var obj = $.parseJSON(json);  //使用这个方法解析json
				    var state_value = obj.result;  //result是和action中定义的result变量的get方法对应的
				    //alert(state_value);
				    if(state_value == "验证码错误！"){
				    	 setFormState("#vlidate-code","error");
						 $("#vlidate-code-hint p").text("验证码错误！");
				    }
				    if(state_value == "验证码正确！"){
				    	 setFormState("#vlidate-code","success");
				    	 $("#email").attr("readonly", "readonly");
				    	 $("#vlidate-code").attr("disabled", true);
				    	 $("#get-vlidate-code").attr("disabled", true);
				    }
			    },
			    error: function(json){
			     alert("json=" + json);
			     return false;
			    }
			    });
			return ;
		
		}
	});
	$("#email").blur(function (){
		if(validateNull("#email","邮箱")){
			setFormState("#email","error");
			return ;
		}
		if(!validEmail($("#email").val())){
			//邮箱地址无效
			$("#email-hint p").text("请输入有效的邮箱地址");
			setFormState("#email","error");
			return ;
		}
		setFormState("#email","success");
		return ;
	});
	$("#password").blur(function (){
		if(validateNull("#password","密码") || !validateLength("#password",6,20)){
			setFormState("#password","error");
			return ;
		}
		else{
			setFormState("#password","success");
			return ;
		}
	});
	$("#repassword").blur(function (){
		if(!isSuccess("#password")){
			return ;
		}
		var psd = $("#password").val();
		var repsd = $("#repassword").val();
		if(!(psd==repsd)){
			//alert("两次输入密码不一致");
			$("#repassword-hint p").text("两次输入的密码不一致");
			setFormState("#repassword","error");
		}else{
			$("#repassword-hint p").text("");
			setFormState("#repassword","success");
		}
	});
	$("#register").click(function (){
		if(isSuccess("#username") && 
				isSuccess("#email") && 
				isSuccess("#password") && 
				isSuccess("#repassword") &&
				isSuccess("#vlidate-code")){
			$("#form").submit();
		}
	});
});

