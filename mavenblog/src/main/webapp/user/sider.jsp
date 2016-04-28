<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="sider" class="fr"> 
     <div class="clear"></div>
     <div class="con_box hot_box"> 
      <h3>个人资料</h3> 
      <div class="readers clearfix"> 
      		<div class="person-info">
      			<div class="headpic">
      			<a href="user/<s:property value="userSider.user.url"/>" title="<s:property value="userSider.user.username"/>的主页">
					<img class="img-rounded" src="upload/headpic/<s:property value="userSider.user.headpicname"/>" width="120px" height="120px">
				</a>
				<strong><s:property value="userSider.user.username"/></strong>
				</div>
				<div class="blog-intro">
					<ul>
						<li>博客访问：<s:property value="userSider.user.bloginfo.visits"/></li>
						<li>博客文章：<s:property value="userSider.user.articles.size()"/></li>
						<li>博客评论：<s:property value="userSider.blogComments"/></li>
					</ul>
				</div>
      		</div>
      </div> 
     </div>
          <div class="con_box hot_box"> 
          	<div class="person-info">
      <h3>阅读排行</h3> 
      <div class="readers clearfix"> 
      			<ul>
			<s:iterator value="userSider.userHotArticles">                  
					<li>
					<a href="article/<s:property value="id" />" title="<s:property value="title" />" class="title">
						<s:if test="%{title.length() >= 16}">
							<s:property value="title.substring(0,15)" />
						</s:if>
						<s:else>
							<s:property value="title" />
						</s:else>
					</a>
 				<div class="visits">
 					(<s:property value="visits"/>)
 				</div>
				</li>
			</s:iterator>
			</ul>
      </div> 
      	</div>
     </div> 
     <div class="con_box hot_box">
		<h3>最新评论</h3>
		<div class="r_comments">
			<ul>
				<s:iterator value="userSider.userLatestComments">
				<li>
					<a href="article/<s:property value="article.id" />" title="发表在： <s:property value="article.title" />">
						<s:if test="%{article.title.length() >= 19}">
							<s:property value="article.title.substring(0,18)" /><br>
						</s:if>
						<s:else>
							<s:property value="article.title" /><br>
						</s:else>
					</a>
					<a href="user/<s:property value="userSider.user.url" />" title="发表在： <s:property value="article.title" />">
						<s:property value="user.username" />
					</a>:
					<s:property value="content" /><br>
				</li>
				</s:iterator>
			</ul>
		</div>				
	</div>
	<div class="con_box hot_box">
		<h3>随机推荐</h3>
		<div class="r_comments">
			<ul>
				<s:iterator value="userSider.userRandomArticles">
				<li>
					<a href="article/<s:property value="id" />" title="<s:property value="title" />" class="title">
						<s:if test="%{title.length() >= 19}">
							<s:property value="title.substring(0,18)" />
						</s:if>
						<s:else>
							<s:property value="title" />
						</s:else>
					</a>
				</li>
				</s:iterator>
			</ul>
		</div>				
	</div>
     <div class="hcms_rollbox"></div> 
     <div id="rollstart"></div> 
    </div>