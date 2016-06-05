<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html> 
<head>  
		<title>留言板</title>
		<%@ include file="path.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
		<link rel="shortcut icon" href="file/pic/blog.ico"/>
		<script type="text/javascript" src="file/js/jquery.min.js"></script>
		<script type="text/javascript" src="file/js/jquery_cmhello.js"></script>
   		<script src="file/js/message.js"></script>
   		<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   		<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   		<link href="file/css/style.css" rel="stylesheet" type="text/css">
   		<link rel="stylesheet" type="text/css" href="file/css/login.css" /> 
<style type="text/css">
	body {background: #606060;}
	.name-font{font-size:12px;color:red;}
	.href-font{font-size:16px}
	.href-time{color:rgb(153,153,153);}
	a.page{text-decoration:underline;}
	.buttom a:link { 
		color:white; 
		text-decoration:underline; 
		}
	.buttom	a:visited { 
		color:white; 
		text-decoration:none; 
		} 
	.buttom	a:hover { 
		color:red; 
		text-decoration:none; 
		} 
	.buttom	a:active { 
		color:blue; 
		text-decoration:none; 
		}    
</style>
</head>  
<body>
<%@ include file="header.jsp" %>
<div id="wrapper" class="clearfix">
		<div class="gap"></div>
			<div id="breadcrumbs" class="con_box clearfix">
				<div class="bcrumbs"><strong><a href="./index" title="返回首页">home</a></strong>
				<a rel="category tag">留言板</a>
				<a href="./sort?sortByColumn=<s:property value="article.articletype.linkname" />" title="查看 <s:property value="article.articletype.value" />的全部文章" rel="category tag"><s:property value="article.articletype.value" /></a>
				<a><s:property value="article.title" /></a>
				</div>
			</div>
 		<div id="art_container clearfix">
		<div class="showmessage">
	<ul class="left-right-clear">
	<s:iterator value="parMsgs">
	<s:if test="%{throughFlag==1}">
			<li class="ds-post">
			<div class="ds-post-self"  message-id="<s:property value="id"/>">
				<div class="ds-avatar">
					<a href="user/<s:property value="user.url"/>"><img src="upload/headpic/<s:property value="user.headpicname"/>" alt="picture"></a>
				</div>
				<div class="ds-comment-body">
					<div class="ds-comment-header">
						<a class="ds-user-name ds-highlight" data-qqt-account="" href="http://t.qq.com/zouzijian" onclick="this.href='http://yangqq.duoshuo.com/user-url/?user_id=2302622';" rel="nofollow" target="_blank" data-user-id="2302622">
							<s:property value="user.username"/>
						</a>
					</div>
					<div class="ds-comment-content">
						<p></p>
						<pre><s:property value="content"/></pre>
						<p></p>
					</div>
					<div class="ds-comment-footer ds-comment-actions"><span class="ds-time" title="<s:property value="time"/>"><s:property value="time"/></span>
				<a class="ds-post-reply" id="<s:property value="id"/>" href="javascript:void(0);" onclick="reply(this)">
					<span class="ds-icon ds-icon-reply"></span>回复
				</a>
				<a  class="ds-post-likes" href="javascript:void(0);" message-id="<s:property value="id"/>" onclick="light(this)">
					<span class="ds-icon ds-icon-like"></span>顶(<h id="light-count_<s:property value="light"/>"><s:property value="light"/></h>)
				</a>
				</div>
					<div class="ds-post-self" style="display:none" id="<s:property value="id"/>">
				<div class="ds-avatar">
				<s:if test="%{#session.user == null}">
					<img src="upload/headpic/default_head.jpg">
				</s:if>
				<s:else>
					<a href="user/<s:property value="#session.user.url"/>"><img src="upload/headpic/<s:property value="#session.user.headpicname"/>"></a>
				</s:else>
				</div>
				<div class="ds-comment-body">
					<div class="ds-comment-header">
					<form method="post" action="AddMessageServlet">
						<input type="hidden" name="parent_id" value="<s:property value="id"/>">
						<input type="hidden" name="reply_id" value="<s:property value="id"/>">
						<p><input type="hidden" id="username" name="username" value="" size="22" tabindex="1" onblur="validateUsername(this)"><label></label><label id="username-hint" style="color:red;"></label></p>
						<p><input type="hidden" id="email" name="email" value="" size="22" tabindex="1" onblur="validateEmail(this)"><label></label><label id="email-hint" style="color:red;"></label></p>
						<s:if test="%{#session.user == null}">
							<textarea readonly="readonly" id="textarea" name="textarea" class="form-control" placeholder="你还没有登录，请登录后留言" onblur="validateTextarea(this)"></textarea>
						</s:if>
						<s:else>
							<textarea id="textarea" name="textarea" class="form-control" placeholder="说点什么吧…" onblur="validateTextarea(this)"></textarea>
						</s:else>
						<p></p>
						<p><input name="reply-submit" class="comment-reply-button"  type="button" name="submit" value="回复" onclick="submitReply(this)"/>
						<label id="textarea-hint" style="color:red;"></label></p>
					</form>
					</div>
				</div>
			</div>
			
			<div class="ds-post-self"  comment-id="">
		<s:if test="%{chiMessages!=null}">
			<s:iterator value="chiMessages">
			<s:if test="%{throughFlag==1}">
				<div class="ds-avatar">
					<a href="user/<s:property value="user.url"/>"><img src="upload/headpic/<s:property value="user.headpicname"/>" alt="picture"></a>
				</div>
				<div class="ds-comment-body">
					<div class="ds-comment-header">
						<a class="ds-user-name ds-highlight" rel="nofollow" target="_blank">
						<s:property value="user.username"/>
						</a>
						&nbsp&nbsp回复&nbsp&nbsp
						<a class="ds-user-name ds-highlight" rel="nofollow" target="_blank">
						<s:property value="replyMessage.user.username"/>
						</a>
					</div>
					<p><pre><s:property value="content"/></pre></p>
					<div class="ds-comment-footer ds-comment-actions">
					<span class="ds-time" datetime="2014-04-11T15:46:13+08:00" title="">
						<s:property value="time"/>
					</span>
				<a class="ds-post-reply" id="" href="javascript:void(0);" onclick="reply(this)">
					<span class="ds-icon ds-icon-reply"></span>回复
				</a>
				<a  class="ds-post-likes" href="javascript:void(0);" message-id="<s:property value="id"/>" onclick="light(this)">
					<span class="ds-icon ds-icon-like"></span>顶(<h><s:property value="light"/></h>)
				</a>
				</div>
			<div class="ds-post-self" style="display:none">
				<div class="ds-avatar">
					<s:if test="%{#session.user == null}">
					<img src="upload/headpic/default_head.jpg">
				</s:if>
				<s:else>
					<a href="user/<s:property value="#session.user.url"/>"><img src="upload/headpic/<s:property value="#session.user.headpicname"/>"></a>
				</s:else>
				</div>
				<div class="ds-comment-body">
					<div class="ds-comment-header">
					<form method="post" action="AddMessageServlet">
						<input type="hidden" name="parent_id" value="<s:property value="parMessage.id"/>">
						<input type="hidden" name="reply_id" value="<s:property value="id"/>">
						<p><input type="hidden"  name="username" value="" size="22" tabindex="1" onblur="validateUsername(this)"><label></label><label id="reply-username-hint" style="color:red;"></label></p>
						<p><input type="hidden"  name="email" value="" size="22" tabindex="1" onblur="validateEmail(this)"><label></label><label id="reply-email-hint" style="color:red;"></label></p>
						<s:if test="%{#session.user == null}">
							<textarea readonly="readonly" id="textarea" name="textarea" class="form-control" placeholder="你还没有登录，请登录后留言" onblur="validateTextarea(this)"></textarea>
						</s:if>
						<s:else>
							<textarea id="textarea" name="textarea" class="form-control" placeholder="说点什么吧…" onblur="validateTextarea(this)"></textarea>
						</s:else>
						<p></p>
						<p><input class="comment-reply-button" comment-id="<s:property value="id"/>" name="reply-submit"  type="button" value="回复" onclick="submitReply(this)"/>
						<label id="textarea-hint" style="color:red;"></label></p>
					</form>
					</div>
				</div>
			</div>
				</div>
				</s:if>
				</s:iterator>
		</s:if>
			</div>
				</div>
			</div> 
		</li>
		</s:if>
		</s:iterator>
	<li class="ds-post" id="messageboard">
		<div class="ds-post-self">
				<div class="ds-avatar">
				<s:if test="%{#session.user != null}">
					<a href="user/<s:property value="#session.user.url"/>"><img src="upload/headpic/<s:property value="#session.user.headpicname"/>"></a>
				</s:if>
				<s:else>
					<img src="upload/headpic/default_head.jpg">
				</s:else>
				</div>
				<div class="ds-comment-body">
					<div class="ds-comment-header">
					<form method="post" action="AddMessageServlet">
						<p><input type="hidden" id="message-username" name="username" value="" size="22" tabindex="1"><label></label><label id="message-username-hint" style="color:red;"></label></p>
						<p><input type="hidden" id="message-email" name="email" value="" size="22" tabindex="1"><label></label><label id="message-email-hint" style="color:red;"></label></p>
						<s:if test="%{#session.user == null}">
							<textarea readonly="readonly" id="message-textarea" name="textarea" class="form-control" placeholder="你还没有登录，请登录后留言"></textarea>
						</s:if>
						<s:else>
							<textarea id="message-textarea" name="textarea" class="form-control" placeholder="说点什么吧…"></textarea>
						</s:else>
						<p></p>
						<p><input name="submit" class="comment-reply-button" id="message-button" type="button" name="submit" value="提交留言"/></p>
						<label id="message-textarea-hint" style="color:red;"></label></p>
					</form>
					</div>
				</div>
			</div>
	</li>
	</ul>
</div>
 			
<%@ include file="./sider.jsp" %>
</div> 
</div>
<%@ include file="./bottom.jsp"%>  
<div style="height:50px">
</div>
</body>
</html>