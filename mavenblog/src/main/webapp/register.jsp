<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>  
  <title>用户注册</title> 
  <%@ include file="path.jsp" %>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<link rel="shortcut icon" href="file/pic/blog.ico"/>
	<script type="text/javascript" src="file/js/jquery.min.js"></script>
	<script type="text/javascript" src="file/js/jquery.js"></script>
	<script type="text/javascript" src="file/js/jquery_cmhello.js"></script>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link href="file/css/style.css" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="file/js/register.js"></script>  
</head> 
<body>
<%@ include file="header.jsp" %>
		<div id="wrapper" class="clearfix">
		<div class="gap"></div>
		<div id="breadcrumbs" class="con_box clearfix">
		<div class="bcrumbs"><strong><a href="index" title="返回首页">home</a></strong>
			<a rel="category tag">
				注册
			</a>
		</div>
		</div>
 		<div id="art_container clearfix">
 		<div id="art_main" class="fl">
 			<div class="register_main"> 
					<div class="register_form"> 
	<form class="form-horizontal" id="form" role="form" method="post" action="user/subregister">
   <div class="form-group">
      <label for="" class="col-sm-2 control-label">用户名</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" name="username" id="username" 
            placeholder="请输入用户名(至少2个字符，最多10个字符)" maxlength="10">
            <div class="height-fill">
            <lable class="hint" id="username-hint"><p></p></lable>
            </div>
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">邮箱</label>
      <div class="col-sm-10" >
         <input type="text" class="form-control" name="email" id="email" 
            placeholder="请输入邮箱地址" maxlength="30">
            <div class="height-fill">
            <lable class="hint" id="email-hint"><p></p></lable>
            </div>
      </div>
   </div>
   <div class="form-inline">
		      <div class="form-group">
		      <input type="text" class="form-control" id="vlidate-code" 
		         placeholder="请输入邮箱验证码" maxlength="10">
		       </div>
		   	<button type="button" id="get-validate-code" class="btn btn-default">获取邮箱验证码</button>
		   	<div class="height-fill">
            <lable class="hint" id="vlidate-code-hint"><p></p></lable>
            </div>
	</div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">密码</label>
      <div class="col-sm-10">
         <input type="password" class="form-control" name="password" id="password" 
            placeholder="请输入密码（不少于6位，不能超过20位）" maxlength="20">
            <div class="height-fill">
            <lable class="hint" id="password-hint"><p></p></lable>
            </div>
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">再输一次</label>
      <div class="col-sm-10">
         <input type="password" class="form-control" name="repassword" id="repassword" 
            placeholder="请再输入一次密码" maxlength="20">
            <div class="height-fill">
            <lable class="hint" id="repassword-hint"><p></p></lable>
            </div>
      </div>
   </div>
   <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
         <input type="button" id="register" class="btn btn-primary btn-lg btn-block" value="注册">
      </div>
   </div>
   <div class="height-fill"></div>
   <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
         <a href="./login">我已有账号，立即登录
         </a>
      </div>
   </div>
</form>
</div> 
</div>	
</div>
<%@ include file="sider.jsp"%>
</div>
</div> 
<!-- //wrapper --><!-- //底部模板 -->
<%@ include file="./bottom.jsp" %>
</body>
<script tppe="text/javascript">
</script>
</html>