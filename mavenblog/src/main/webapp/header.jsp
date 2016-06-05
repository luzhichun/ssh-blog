<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
	<div id="header"> 
   <div id="header_inner"> 
    <strong class="logo"> <a href="index" class="png_bg" title="卧颜沉默">卧颜沉默</a> </strong> 
    <div class="header_bg png_bg"></div> 
    <div class="toplinks">
     <div id="_userlogin"> 
     <s:if test="%{#session.user == null}">
      <a href="user/login" title="用户登录">登录</a> 
      <span>|</span> 
      	<a href="user/register" title="注册">注册</a>
      	<span>|</span> 
      	<a href="admin/login.jsp" title="注册">管理员登录</a>
      </s:if>
      <s:else>
      	<a href="user/loginout" title="注销">注销</a>
      	<span>|</span> 
      	<a href="admin/login.jsp" title="注册">管理员登录</a>
      </s:else> 
     </div> 
     <div id="top_nav"> 
      <form name="formsearch" method="get" action="serach"> 
       <div class="form clearfix"> 
        <label for="s"></label> 
        <input type="text" name="q" class="search-keyword"  onfocus="if (this.value == '请输入关键字进行搜索') {this.value = '';}" onblur="if (this.value == '') {this.value = '请输入关键字进行搜索';}" value="请输入关键字进行搜索"/> 
        <button type="submit" class="select_class">搜索</button> 
       </div> 
      </form> 
     </div> 
    </div> 
    <div id="navmenu"> 
     <ul> 
      <li><a href="index">网站首页</a></li> 
      <s:iterator value="#application.headerAndSider.parArticletypes">
	      <li><a href="sort?sortByColumn=<s:property value="linkname" />"><s:property value="value" /></a> 
	       <ul class="sub-menu">
	        <s:if test="%{chiArticletypes!=null}">
        		<s:iterator value="chiArticletypes">
	        		<li><a href="sort?sortByColumn=<s:property value="linkname" />"><s:property value="value" /></a></li>  
	        	</s:iterator>
	        </s:if>
	        <s:else>
   			 </s:else>
	       </ul>
	       </li>
	   </s:iterator>
      <li>
      	<a href="msgboard">留言板</a>
      </li> 
     </ul> 
    </div> 
    <div class="clearfix"></div> 
   </div> 
  </div> 