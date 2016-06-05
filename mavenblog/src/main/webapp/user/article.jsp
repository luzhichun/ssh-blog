<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
 <head>  
  	<title><s:property value="article.title"/></title>
  	<%@ include file="../path.jsp" %> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="file/pic/blog.ico"/>
	<script type="text/javascript" src="file/js/jquery.min.js"></script>
	<script type="text/javascript" src="file/js/jquery_cmhello.js"></script>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <script src="file/js/comment.js">"></script>
   <link href="file/css/style.css" rel="stylesheet" type="text/css">
<style >
   		#user-all{
			width:100%;
			height:auto;
			background: url(file/pic/skin/<s:property value="userSider.user.bloginfo.background"/>) repeat;
		}
   </style>
</head>
<body style="background:#464646;">
<%@ include file="./pagetop.jsp" %>
	<div id="user-all">
	<div id="user-header">   
		<div id="blog_title"> 
            <h2>
                <a href="user/<s:property value="article.user.url"/>"><s:property value="article.user.username"/> 的博客</a></h2>
            <h3><s:property value="article.user.bloginfo.intro"/></h3>
            <div class="clear">
            </div>
        </div>
	</div>
	<div id="wrapper" class="clearfix">
	<div class="gap"></div>
	<div id="breadcrumbs" class="con_box clearfix">
				<div class="bcrumbs"><strong><a href="index" title="返回首页">home</a></strong>
				<a href="user/<s:property value="article.user.url"/>" title="<s:property value="article.user.username"/>"><s:property value="article.user.username"/></a>
				<a><s:property value="article.title"/></a>
				</div>
	</div> 
   <div id="art_container clearfix"> 
	<div id="art_main" class="fl"> 
			<div id="art_main1" class="art_white_bg fl"> 
				<div class="art_title clearfix">
            <span class="face_img"></span>
				<h1><s:property value="article.title" /></h1>
				<p class="info">
				<small>时间:</small><s:property value="article.releasetime" />  
				<small>栏目:</small><a href="sort?sortByColumn=<s:property value="article.articletype.linkname" />" title="查看 <s:property value="article.articletype.value" /> 的全部文章" rel="category tag"><s:property value="article.articletype.value" /></a>
				<small>作者:</small><s:property value="article.user.username" />
				<small>评论:</small><s:property value="article.comments.{?#this.throughFlag==1}.size()" />
				<small>点击:</small><s:property value="article.visits" />次
										</p><!-- /info -->  
			</div>
			<div class="article_content">
				<div class="article-tag">
					<p><strong>本文标签</strong>：
					<s:iterator value="article.tags"> 
					<a href="sort?sortByTag=<s:property value="id"/>"><span name="标签" class="label label-info"><s:property value="value"/></span></a>
					</s:iterator>
					</p>				
				</div>
				<div class="clear"></div>
				<div id="content">
				<s:property escape="false" value="article.articleContent.content" />
				</div>			﻿
				<div class="clear"></div>	
				
							</div><!--正文--> 

				<div class="page_navi"></div>
		<div class="showmessage">
				<ul>
			<s:iterator value="article.comments">
			<s:if test="%{parComment==null&&throughFlag==1}">
			<li class="ds-post">
			<div class="ds-post-self"  comment-id="">
				<div class="ds-avatar">
					<a href="user/<s:property value="user.url"/>"><img src="upload/headpic/<s:property value="user.headpicname"/>" alt="picture"></a>
				</div>
				<div class="ds-comment-body">
					<div class="ds-comment-header">
						<a class="ds-user-name ds-highlight" rel="nofollow" target="_blank">
						<s:property value="user.username" />
						</a>
					</div>
					<p><s:property  value="content" /></p>
					<div class="ds-comment-footer ds-comment-actions">
					<span class="ds-time" datetime="2014-04-11T15:46:13+08:00" title="">
						<s:property value="time" />
					</span>
				<a class="ds-post-reply" id="" href="javascript:void(0);" onclick="reply1(this)">
					<span class="ds-icon ds-icon-reply"></span>回复
				</a>
				<a  class="ds-post-likes" href="javascript:void(0);" comment-id="<s:property value="id" />" onclick="light(this)">
					<span class="ds-icon ds-icon-like"></span>顶(<h><s:property value="light" /></h>)
				</a>
				</div>
				<div class="ds-post-self" style="display:none" id="<s:property value="id" />">
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
						<input type="hidden" name="parent_id" value="<s:property value="id" />">
						<input type="hidden" name="reply_id" value="<s:property value="id" />">
						<p><input type="hidden"  name="username" value="" size="22" tabindex="1" onblur="validateUsername(this)"><label></label><label id="reply-username-hint" style="color:red;"></label></p>
						<p><input type="hidden"  name="email" value="" size="22" tabindex="1" onblur="validateEmail(this)"><label></label><label id="reply-email-hint" style="color:red;"></label></p>
						<s:if test="%{#session.user == null}">
							<textarea readonly="readonly" name="textarea" class="form-control" rows="3" placeholder="你还没有登录，请登录后评论" onblur="validateTextarea(this)"></textarea>
						</s:if>
						<s:else>
							<textarea name="textarea" class="form-control" rows="3" placeholder="说点什么吧..." onblur="validateTextarea(this)"></textarea>
						</s:else>
						<p></p>
						<p><input class="comment-reply-button" comment-id="<s:property value="id" />" name="reply-submit" type="button" value="评论" onclick="submitReply(this)"/>
						<label id="textarea-hint" style="color:red;"></label></p>
					</form>
					</div>
				</div>
			</div>
				<!-- 测试 -->

						<div class="ds-post-self"  comment-id="">
	<s:iterator value="chiComments">
	<s:if test="%{throughFlag == 1}">
				<div class="ds-avatar">
					<a href="user/<s:property value="user.url"/>"><img src="<%=request.getContextPath() %>/upload/headpic/<s:property value="user.headpicname"/>" alt="picture"></a>
				</div>
				<div class="ds-comment-body">
					<div class="ds-comment-header">
						<a class="ds-user-name ds-highlight" rel="nofollow" target="_blank">
						<s:property value="user.username" />
						</a>
						&nbsp&nbsp回复&nbsp&nbsp
						<a class="ds-user-name ds-highlight" rel="nofollow" target="_blank">
						<s:property  value="replyComment.user.username" />
						</a>
					</div>
					<p><pre><s:property value="content" /></pre></p>
					<div class="ds-comment-footer ds-comment-actions">
					<span class="ds-time" datetime="2014-04-11T15:46:13+08:00" title="">
						<s:property value="time" />
					</span>
				<a class="ds-post-reply" id="" href="javascript:void(0);" onclick="reply1(this)">
					<span class="ds-icon ds-icon-reply"></span>回复
				</a>
				<a  class="ds-post-likes" href="javascript:void(0);" comment-id="<s:property value="id" />" onclick="light(this)">
					<span class="ds-icon ds-icon-like"></span>顶(<h><s:property value="light" /></h>)
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
						<input type="hidden" name="parent_id" value="<s:property value="parComment.id" />">
						<input type="hidden" name="reply_id" value="<s:property value="id" />">
						<p><input type="hidden"  name="username" value="" size="22" tabindex="1" onblur="validateUsername(this)"><label></label><label id="reply-username-hint" style="color:red;"></label></p>
						<p><input type="hidden"  name="email" value="" size="22" tabindex="1" onblur="validateEmail(this)"><label></label><label id="reply-email-hint" style="color:red;"></label></p>
							<s:if test="%{#session.user == null}">
							<textarea readonly="readonly" name="textarea" class="form-control" rows="3" placeholder="你还没有登录，请登录后评论" onblur="validateTextarea(this)"></textarea>
						</s:if>
						<s:else>
							<textarea name="textarea" class="form-control" rows="3" placeholder="说点什么吧..." onblur="validateTextarea(this)"></textarea>
						</s:else>
						<p></p>
						<p><input class="comment-reply-button" comment-id="<s:property value="id" />" name="reply-submit" id="reply-comment-button_" type="button" value="评论" onclick="submitReply(this)"/>
						<label id="textarea-hint" style="color:red;"></label></p>
					</form>
					</div>
				</div>
			</div>
				</div>
			</s:if>
	</s:iterator>
			</div>
				<!-- 测试 -->
				</div>
			</div>
		</li>
		</s:if>
</s:iterator>		
	<li>&nbsp;
	<div class="ds-post-self" style="display:none"  id="new-comment">
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
						<a class="ds-user-name ds-highlight" rel="nofollow">
						用户名
						</a>
					</div>
					<p><pre>评论内容</pre></p>
					<div class="ds-comment-footer ds-comment-actions">
					<span class="ds-time"  title="">
						评论时间
					</span>
				<a class="ds-post-reply" id="" href="javascript:void(0);" onclick="reply()">
					<span class="ds-icon ds-icon-reply"></span>回复
				</a>
				<a  class="ds-post-likes" href="javascript:void(0);" message-id="" onclick="light()">
					<span class="ds-icon ds-icon-like"></span>顶(0)
				</a>
				</div>
				</div>
			</div>
	</li>
	<li class="ds-post" id="messageboard">
		<div class="ds-post-self">
				<div class="ds-avatar">
					<s:if test="%{#session.user == null}">
					<img src="upload/headpic/default_head.jpg">
				</s:if>
				<s:else>
					<a href="user/<s:property value="#session.user.url"/>"><img src="upload/headpic/<s:property value="#session.user.headpicname"/>"></a>
				</s:else>
				</div>
				<div class="ds-comment-body" id="comment">
					<div class="ds-comment-header">
					<form method="post" action="AddMessageServlet">
						<p><input type="hidden" id="comment-username" name="username" value="" size="22" tabindex="1"><label></label><label id="comment-username-hint" style="color:red;"></label></p>
						<p><input type="hidden" id="comment-email" name="email" value="" size="22" tabindex="1"><label></label><label id="comment-email-hint" style="color:red;"></label></p>
						<input type="hidden" id="comment-article_id" name="article_id" value="<s:property value="article.id" />">
						<s:if test="%{#session.user == null}">
							<textarea readonly="readonly" id="comment-textarea" name="textarea" class="form-control" rows="3" placeholder="你还没有登录，请登录后评论"></textarea>
						</s:if>
						<s:else>
						<textarea id="comment-textarea" name="textarea" class="form-control" rows="3" placeholder="说点什么吧…"></textarea>
						</s:else>
						<p></p>
						<p><input name="submit" class="comment-reply-button" id="comment-button" type="button" value="评论"/>
						<label id="comment-textarea-hint" style="color:red;"></label></p>
					</form>
					</div>
				</div>
			</div>
	</li>
	</ul>
	</div>
	</div>                          
</div> 
    <!-- art_main end--> 
     <%@ include file="./sider.jsp" %>
   </div> 
</div>
<%@ include file="../bottom.jsp" %>
</div>
 <script type="text/javascript"> 
  uParse('#content', {
	    rootPath: '../'});
  </script>
</body>
</html>