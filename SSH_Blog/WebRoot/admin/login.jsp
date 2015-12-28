<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<html>
<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<head>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/file/pic/blog.ico"/>
	<link rel="stylesheet" type="text/css" href="css/Login_style.css" />
	<script type="text/javascript" src="js/Login_javascript.js"></script>
</head>
<body>
<% 
	String number=request.getParameter("id");
	String password=request.getParameter("password");
	if(number!=null&&password!=null){
		if(number.equals("admin")&&password.equals("admin")){
			session.setAttribute("admin", "");
			response.sendRedirect("index.action");
			return;
		}
		else{
			response.sendRedirect("login.jsp");
			return; 
		} 
	}
%>
<a href="../index.action"><h3>返回网站首页</h3></a>
<br><br><br>
<h2 align="center">后台管理登录</h2>
<div class="LoginWindow">
  <div>
    <form method="post" action="login.jsp" class="login" name="form1">
    <p>
      <label for="login">帐号:</label>
      <input type="text" name="id" id="id">
    </p>

    <p>
      <label for="password">密码:</label>
      <input type="password" name="password" id="password">
    </p>
    <p class="login-submit">
   	  <button type="submit" class="login-button">登录</button>
    </p>

    </form>
  </div>
</div>

<div id="timeArea"><script> LoadBlogParts();</script></div>
</body>
<script>
	alert("账号：admin,密码：admin");
</script>
</html>