<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>  
  	<title>梦想博客首页</title> 
  	<%@ include file="path.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="file/pic/blog.ico"/>
	<script type="text/javascript" src="file/js/jquery.min.js"></script>
	<script type="text/javascript" src="file/js/jquery_cmhello.js"></script>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <link href="file/css/style.css" rel="stylesheet" type="text/css">
 	
</head> 
 <body> 
 <%@ include file="header.jsp" %>
  <div id="wrapper" class="clearfix"> 
   <div id="art_container clearfix"> 
    <div id="art_main" class="fl"> 
	<s:iterator value="articles">
	    <%@ include file="articleintro.jsp" %>
	 </s:iterator>
    </div> 
     <%@ include file="sider.jsp" %>
   </div> 
</div> 
  <%@ include file="./bottom.jsp" %> 
 </body>
</html>