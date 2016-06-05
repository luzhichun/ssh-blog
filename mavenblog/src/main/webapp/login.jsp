<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>  
  <title>用户登录</title> 
  	<%@ include file="path.jsp" %>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<link rel="shortcut icon" href="file/pic/blog.ico"/>
	<script type="text/javascript" src="file/js/jquery.min.js"></script>
	<script type="text/javascript" src="file/js/jquery_cmhello.js"></script>
	<!-- bootstrap插件 -->
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link href="file/css/style.css" rel="stylesheet" type="text/css"> 
</head> 
<body>
<%@ include file="header.jsp" %>
		<div id="wrapper" class="clearfix">
		<div class="gap"></div>
		<div id="breadcrumbs" class="con_box clearfix">
		<div class="bcrumbs"><strong><a href="index" title="返回首页">home</a></strong>
			<a rel="category tag">
				登录
			</a>
		</div>
		</div>
 		<div id="art_container clearfix">
 			<div id="art_main" class="fl"> 
				<div class="login_main">
					<div class="con_box userinfo_box clearfix">
     		<div class="login_form">
				    <form class="form-horizontal" id="form" role="form" method="post" action="user/sublogin">
				   <div class="form-group">
				      <label for="" class="col-sm-1 control-label"></label>
				      <div class="col-sm-10">
				         <input type="text" class="form-control" name="username" id="username" 
				            placeholder="请输入用户名" maxlength="10" value="">
				      </div>
				   </div>
				   <div class="form-gap"></div>
				   <div class="form-group">
				      <label for="" class="col-sm-1 control-label"></label>
				      <div class="col-sm-10">
				         <input type="password" class="form-control" name="password" id="password" 
				            placeholder="请输入密码" maxlength="20" value="">
				            <div class="height-fill">
				           		 <lable class="hint" id="hint"><p>
				           		 	&nbsp<s:property value="#request.loginState"/>
				           		 </p></lable>
				            </div>
				      </div>
				   </div>
				   <div class="form-gap"></div>
				   <div class="form-group">
				   	  <label for="" class="col-sm-1 control-label"></label>
				      <div class="col-sm-offset-0 col-sm-10">
				         <input type="button" id="register" class="btn btn-primary btn-lg btn-block" value="登录" onclick="this.form.submit();"'>
				      </div>
				   </div>
				   <div class="form-gap"></div>
				   <div class="form-group">
				   	  <label for="" class="col-sm-1 control-label"></label>
				      <div class="col-sm-offset-0 col-sm-10">
				         <a href="register">还没有账号，立即注册
				         </a>
				      </div>
				   </div>
				</form>
   			</div>
   		</div>
				</div>
		    </div>
		    <%@ include file="sider.jsp"%>
</div>
</div> 
<!-- //wrapper --><!-- //底部模板 -->
<%@ include file="./bottom.jsp" %>
</body>
</html>