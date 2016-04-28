<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="pagetop">
	<div class="topinfo">
			<s:if test="%{#session.user != null}">
		<div id="navmenu"> 
			<ul>
				      <li><a href="user/<s:property value="#session.user.url"/>" title="<s:property value="#session.user.username"/>的主页">
								<img class="img-circle" src="upload/headpic/<s:property value="#session.user.headpicname"/>" width="40px" height="40px">
							</a>
					    <ul class="sub-menu" id="hidden-ul" style="display:none;">
							<li><a href="user/ReleaseArticle.action">个人中心</a></li>
							<li><a href="user/ArticleManage.action">文章管理</a></li>
							<li><a href="user/CommentManage.action">评论管理</a></li>
							<li><a href="user/modifyinfo.action">资料设置</a></li>
							<li><a href="user/loginout.action">注销</a></li>
						</ul>
						   </li> 
				</ul>
		  </div>
			</s:if>
			<s:else>
			<ul class="notlogin">
			<li><a href="register.action">注册</a></li>
			<li><a href="login.action">登录</a></li>
			</ul>
			</s:else>
	
	</div>
	</div>