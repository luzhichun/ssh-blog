<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="sider" class="fr"> 
      
     <div class="clear"></div>
   		<s:if test="%{#session.user != null}">
   		<div class="con_box userinfo_box clearfix">
     		<div class="userinfo">
				    <div class="head pub-style ">
				    	<div class="user_img">
				    	<a href="user/<s:property value="#session.user.url"/>" title="我的博客">
				    	<img class="img-rounded img-thumbnail" src="upload/headpic/<s:property value="#session.user.headpicname"/>" width="80px" height="80px">
				    	</a>
				    	</div>
				    	<div class="username">
				    		<label style="font-size:18px;"><s:property value="#session.user.username"/></label>
				    		<p><a href="user/CommentManage?sort" class="notice">消息(<span msgtype="notice"><s:property value="#session.user.msgCounts"/></span>)</a> | 
				    		<a href="user/loginout" class="logout">退出</a>
				    		</p>
				    	</div>
				    </div>
				    <div class="bloginfo pub-style">
				    		<ul class="clearfix">
				    			<li class="fl">博客文章：<span msgtype="level"><s:property value="#session.user.articleCounts"/></span></li>
				    			<li class="fl">博客访问：<span msgtype="pv"><s:property value="#session.user.bloginfo.visits"/></span></li>
				    			<li class="fl">博客评论：<span msgtype="userpoint"><s:property value="#session.user.BlogComments"/></span></li>
				    			<li class="fl">博客年龄：<span msgtype="num">0</span></li>
				    		</ul>
				    </div>
				    <div class="shortcut pub-style">
				    	<button type="button" class="btn btn-success" onclick="window.open('user/ReleaseArticle')">发表文章</button>
				    	<button type="button" class="btn btn-success" onclick="window.open('user/ArticleManage')">文章管理</button>
				    	<button type="button" class="btn btn-success" onclick="window.open('user/CommentManage')">评论管理</button>
				    </div>
   			</div>
   		</div>
   		</s:if>
     	<div class="con_box htabs_art clearfix"> 
		<ul class="cooltab_nav sj_nav clearfix">
			<li><a href="#" class="active" title="new_art" >最新文章</a></li>
			<li><a href="#" title="hot_art">热门文章</a></li>
			<li><a href="#" title="rand_art">随机推荐</a></li>
		</ul>   
		<div id="new_art" class="com_cont" style="display: block;">   
			<ul>
				<s:iterator value="#application.headerAndSider.latestArticles">                   
					<li><a href="article/<s:property value="id" />" title="<s:property value="title" />" class="title">
						<s:if test="%{title.length() >= 19}">
							<s:property value="title.substring(0,18)" />...
						</s:if>
						<s:else>
							<s:property value="title" />
						</s:else>
					</a></li>
				</s:iterator>
			</ul>                    
		</div>
        <div id="hot_art" class="com_cont" style="display: none;">  
    		<ul>
				<s:iterator value="#application.headerAndSider.hotArticles">                   
					<li><a href="article/<s:property value="id" />" title="<s:property value="title" />" class="title">
						<s:if test="%{title.length() >= 19}">
							<s:property value="title.substring(0,18)" />...
						</s:if>
						<s:else>
							<s:property value="title" />
						</s:else>
					</a></li>
				</s:iterator>
			</ul> 
		</div>
		<div id="rand_art" class="com_cont" style="display: none;">  
			<ul>
				<s:iterator value="#application.headerAndSider.randomArticles">                   
					<li><a href="article/<s:property value="id" />" title="<s:property value="title" />" class="title">
						<s:if test="%{title.length() >= 19}">
							<s:property value="title.substring(0,18)" />...
						</s:if>
						<s:else>
							<s:property value="title" />
						</s:else>
					</a></li>
				</s:iterator>
			</ul>  
		</div>   
	</div>
<script type="text/javascript">
 		$(document).ready(function(){
 			//设置随机颜色标签
 			$(".tagcloudy a").each(function(){
			var rgbColor ="#" + Math.round(Math.random() * 255).toString(16) + Math.round(Math.random() * 255).toString(16) + Math.round(Math.random() * 255).toString(16);
			var randomSize = Math.round(Math.random() * 6) + 12 + "px";
			var styleAttr = "color:" + rgbColor + ";" + "font-size:" + randomSize +";";
			$(this).attr("style",styleAttr);
 			});
		});
</script>
     <div class="con_box hot_box"> 
      <h3>热门标签</h3>
      <ul class="tagcloudy">
				<li>
				<s:iterator value="#application.headerAndSider.hotTags"> 
					<a href="sort?sortByTag=<s:property value="id"/>" title="包含  <s:property value="value"/>  标签文章"><s:property value="value"/></a>
				</s:iterator>
				</li>
				</ul> 
     </div> 
     <div class="con_box hot_box"> 
      <h3>最新留言</h3> 
      <div class="readers clearfix"> 
      			<ul>
			<s:iterator value="#application.headerAndSider.latestMsgs">
				<li>
					<a href="user/<s:property value="user.url"/>" title="<s:property value="content" />">
					<s:property value="user.username" />:<br>
					</a>
					<s:if test="%{content.length() >= 19}">
							<s:property value="content.substring(0,18)" />...
					</s:if>
					<s:else>
							<s:property value="content" />
					</s:else>
				</li>
			</s:iterator>
			</ul>
      </div> 
     </div> 
     <div class="con_box hot_box">
		<h3>最新评论</h3>
		<div class="r_comments">
			<ul>
				<s:iterator value="#application.headerAndSider.latestComments">
				<li>
					<a href="user/<s:property value="user.url" />" title="<s:property value="user.username" />的主页">
					<s:property value="user.username" />
					</a>:<br>
					<a href="article/<s:property value="article.id" />" title="发表在： <s:property value="aritlce.title" />">
					<s:if test="%{content.length() >= 19}">
							<s:property value="content.substring(0,18)" />...
					</s:if>
					<s:else>
							<s:property value="content" />
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