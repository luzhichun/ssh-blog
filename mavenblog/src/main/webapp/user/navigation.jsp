<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="crumb-wrap">
            <ul class="nav nav-tabs">
            <s:if test="%{currentPage == \"releaseArticle\"}">
			   <li class="active"><a href="user/ReleaseArticle.jsp">发布文章</a></li>
			   <li><a href="user/ArticleManage.jsp">文章管理</a></li>
			   <li>
			   		<a href="user/CommentManage.jsp">评论管理</a>
			   		<s:if test="%{#session.user.msgCounts != null && #session.user.msgCounts != 0}">
						<span class="remind"><s:property value="#session.user.msgCounts"/></span>
					</s:if>
			   </li>
			   <li><a href="user/modifyinfo.jsp">个人资料设置</a></li>
			</s:if>
			<s:elseif test="%{currentPage == \"articleManage\"}">
				<li><a href="user/ReleaseArticle.jsp">发布文章</a></li>
			   <li class="active"><a href="user/ArticleManage.jsp">文章管理</a></li>
			   <li>
			   		<a href="user/CommentManage.jsp">评论管理</a>
			   		<s:if test="%{#session.user.msgCounts != null && #session.user.msgCounts != 0}">
						<span class="remind"><s:property value="#session.user.msgCounts"/></span>
					</s:if>
			   </li>
			   <li><a href="user/modifyinfo.jsp">个人资料设置</a></li>
			</s:elseif>
			<s:elseif test="%{currentPage == \"commentManage\"}">
				<li><a href="user/ReleaseArticle.jsp">发布文章</a></li>
			   <li><a href="user/ArticleManage.jsp">文章管理</a></li>
			   <li class="active">
			   		<a href="user/CommentManage.jsp">评论管理</a>
			   		<s:if test="%{#session.user.msgCounts != null && #session.user.msgCounts != 0}">
						<span class="remind"><s:property value="#session.user.msgCounts"/></span>
					</s:if>
			   </li>
			   <li><a href="user/modifyinfo.jsp">个人资料设置</a></li>
			</s:elseif>
			<s:else>
				<li><a href="user/ReleaseArticle.jsp">发布文章</a></li>
			   <li><a href="user/ArticleManage.jsp">文章管理</a></li>
			   <li>
			   		<a href="user/CommentManage.jsp">评论管理</a>
			   		<s:if test="%{#session.user.msgCounts != null && #session.user.msgCounts != 0}">
						<span class="remind"><s:property value="#session.user.msgCounts"/></span>
					</s:if>
			   </li>
			   <li class="active"><a href="user/modifyinfo.jsp">个人资料设置</a></li>
			</s:else>
			</ul>
</div>