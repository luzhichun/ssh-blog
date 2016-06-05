<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<div class="art_img_box clearfix"> 
 <div class="fl innerimg_box"> 
  <a href="article/<s:property value="id" />" title="<s:property value="title" />"><img src="upload/<s:property value="imagename" />" alt="<s:property value="title" />" width="200" height="154" original="./article/<s:property value="id" />" /></a> 
 </div> 
 <div class="fr box_content"> 
  <h2><a href="article/<s:property value="id" />" title="<s:property value="title" />"><s:property escape="false" value="title" /></a></h2> 
  <div class="info"> 
   <span>发布时间：<small><s:property value="releasetime" /></small></span>| 
   <span>点击：<small> <s:property value="visits" />次</small></span>| 
   <span><a href="article/<s:property value="id" />" title="<s:property value="title" />">
   	<s:property value="comments.{?#this.throughFlag==1}.size()" />条评论</a>
   	</span>|
  </div>
  
  <ul class="clearfix"> 
   <li>所属栏目：<span>
	  			<a href="sort?sortByColumn=<s:property value="articletype.linkname" />" title="查看<s:property value="articletype.value" />的全部文章" rel="category tag">
	  			<s:property value="articletype.value"/>
	  			</a>
  		</span>
  </li>
  <li class="art_author">
  			<span>作者：</span> 
   <span >
   <a href="user/<s:property value="user.url" />" title="<s:property value="title" />">
   	<s:property value="user.username" /></a>
   	</span>
  </li>
  <div class="clearfix"></div> 
   <li class="art_tag">标签：
   		<s:iterator value="tags">
	   <a href="sort?sortByTag=<s:property value="id"/>" title="查看包含 <s:property value="value"/> 标签的全部文章"><s:property value="value"/></a>
	   </s:iterator> 
   </li> 
  </ul> 
  <p class="intro"><s:property value="beagincontent" /></p> 
 </div> 
</div>