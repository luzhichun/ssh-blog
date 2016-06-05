<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
 <head>  
 	<title>个人资料修改</title>
 	<%@ include file="../path.jsp" %>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="file/pic/blog.ico"/>
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="file/js/jquery_cmhello.js"></script>
	<script src="admin/js/myjquery.js"></script>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link href="file/css/style.css" rel="stylesheet" type="text/css">
   <link rel="stylesheet" type="text/css" href="file/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="file/css/main.css"/>
	<style >
   		#user-all{
			width:100%;
			height:auto;
			background: url(../file/pic/skin/<s:property value="background"/>) repeat;
		}
   </style>
</head>
<body style="background:#464646;">
<%@ include file="./pagetop.jsp" %>
	<div id="user-all">
	<div id="user-header">
		<div id="blog_title">
            <h2>
                <a href="u/<s:property value="#session.user.url"/>"><s:property value="#session.user.username"/> 的博客</a></h2>
            <h3><s:property value="#session.user.bloginfo.intro"/></h3>
            <div class="clear">
            </div>
        </div>
	</div>
	<div id="wrapper" class="clearfix">
	<div class="gap"></div>
	<div id="breadcrumbs" class="con_box clearfix">
				<div class="bcrumbs"><strong><a href="index" title="返回首页">home</a></strong>
				<a href="<s:property value="#session.user.url"/>"><s:property value="#session.user.username"/></a>
				<a>个人中心</a>
				<a>个人资料修改</a>
				</div>
	</div> 
   <div id="art_container clearfix"> 
    <div id="user_art_main" class="fl"> 
    	<div class="main-wrap">
    	<div class="gap"></div>
		<%@include file="navigation.jsp"%>
        <div class="result-wrap">
            <div class="result-content">

                <form action="user/modify" method="post" id="valueform" name="valueform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120"><i class="require-red">*</i>用户名：</th>
                            <td>
                                <input class="common-text required" name="username" size="20" maxlength="10" value="<s:property value="#session.user.username"/>" type="text" readonly="readonly">
                            </td>
                        </tr>
                            <tr>
                                <th><i class="require-red">*</i>个人介绍：</th>
                                <td>
                                    <input class="common-text required" id="personinfo" name="personinfo" size="50" maxlength="30" value="<s:property value="#session.user.bloginfo.intro"/>" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red"></i>头像：</th>
                                <td><input name="uploadFile" type="file" value="选择要上传的头像"></td>
                            </tr>
                            <tr>
                                <th>博客皮肤：</th>
                                <td> 
                                	<div class="modify-skin">
                                		<div class="skin">
                                			<img src="../file/pic/skin/black_small.png" height="100px" width="120px">
                                			<span>
                                				<input type="radio" name="skin" value="black.jpg" 
                                				<s:if test="%{#session.user.bloginfo.background == \"black.jpg\"}">
                                					checked
                                				</s:if>
                                				>
                                				<label for="radSkin2">炫酷黑</label>
                                			</span>
                                		</div>
                                		<div class="skin">
                                			<img src="../file/pic/skin/grey_small.png" height="100px" width="120px"">
                                			<span>
                                				<input type="radio" name="skin" value="grey.jpg" 
                                				<s:if test="%{#session.user.bloginfo.background == \"grey.jpg\"}">
                                					checked
                                				</s:if>
                                				>
                                				<label for="radSkin2">水墨</label>
                                			</span>
                                		</div>
                                		<div class="skin">
                                			<img src="../file/pic/skin/bluesky_small.png" height="100px" width="120px"">
                                			<span>
                                				<input type="radio" name="skin" value="bluesky.jpg" 
                                				<s:if test="%{#session.user.bloginfo.background == \"bluesky.jpg\"}">
                                					checked
                                				</s:if>
                                				>
                                				<label for="radSkin2">蓝天</label>
                                			</span>
                                		</div>
                                		<div class="skin">
                                			<img src="../file/pic/skin/sport_small.png" height="100px" width="120px"">
                                			<span>
                                				<input type="radio" name="skin" value="sport.jpg" 
                                				<s:if test="%{#session.user.bloginfo.background == \"sport.jpg\"}">
                                					checked
                                				</s:if>
                                				>
                                				<label for="radSkin2">运动</label>
                                			</span>
                                		</div>
                                	</div>
                                </td>
                            </tr>
                       		<tr>
                       			<th>有评论是否邮箱通知：</th>
                       			<td>
                       				<span style="margin-left:40px;"><input type="radio" name="email_notice" value="1"
                       					<s:if test="%{#session.user.bloginfo.emailNoticeflag == 1}">
                                					checked
                                		</s:if>
                       				/><label>是</label></span> 
                       				<span style="margin-left:40px;"><input type="radio" name="email_notice" value="0"
                       					<s:if test="%{#session.user.bloginfo.emailNoticeflag == 0}">
                                					checked
                                		</s:if>
                       				/><label>否</label></span>
                       			</td> 
                       		</tr>
                            <tr>
                                <th></th> 
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" onclick="submitForm()" type="button">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>
    </div>
    </div> 
   </div> 
</div>
<%@ include file="../bottom.jsp" %>
</div>
</body>
<script type="text/javascript">
	function submitForm(){
		$("#valueform").submit();
	}
</script> 
</html>