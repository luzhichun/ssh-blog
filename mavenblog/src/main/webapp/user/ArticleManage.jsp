<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
 <head>  
  	<title>文章管理</title> 
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
			background: url(file/pic/skin/<s:property value="background"/>) repeat;
		}
   </style>
</head>
<body style="background:#464646;">
	<%@ include file="./pagetop.jsp" %>
	<div id="user-all">
	<div id="user-header">
		<div id="blog_title">
            <h2>
                <a href="u/<s:property value="user.url"/>"><s:property value="user.username"/> 的博客</a></h2>
            <h3><s:property value="user.bloginfo.intro"/></h3>
            <div class="clear">
            </div>
        </div>
	</div>
	<div id="wrapper" class="clearfix">
	<div class="gap"></div>
	<div id="breadcrumbs" class="con_box clearfix">
				<div class="bcrumbs"><strong><a href="index" title="返回首页">home</a></strong>
				<a href="<s:property value="user.url"/>"><s:property value="user.username"/></a>
				<a>个人中心</a>
				<a>文章管理</a>
				</div>
	</div> 
   <div id="art_container clearfix"> 
    <div id="user_art_main" class="fl"> 
    	<div class="main-wrap">
		<div class="gap"></div>
        <%@include file="navigation.jsp"%>
        <div class="search-wrap">
            <div class="search-content">
                <form action="article.jsp" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="sort" id="select-serach">
                                	<option value="all">全部文章</option>
                                   <s:iterator value="articletypes">
                                    	<option 
                                    		value="<s:property value="linkname"/>" 
	                                    	<s:if test="%{articletype.value==value}">
	                                    		<%-- 默认选择该分类 --%>
	                                    		selected = "selected"
	                                    	</s:if>
                                    	>
                                    	<s:property value="value"/></option>
                                	</s:iterator>
                                </select>
                            </td>
                            <td><input id="columnSerach" class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="ReleaseArticle.jsp"><i class="icon-font"></i>新增作品</a>
                        <a id="articleBatchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="./ArticleManage.jsp"><i class="icon-font"></i>刷新</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input id="allArticleChoose" name="" type="checkbox"></th>
                            <th width="10%">所属栏目</th>
                            <th>标题</th>
                            <th width="8%">点击</th>
                            <th width="18%">更新时间</th>
                            <th width="5%">评论</th>
                            <th width="10%">操作</th>
                        </tr>
                         <s:iterator value="user.articles">
                        <tr id="tr_<s:property value="id"/>">
                            <td class="tc"><input name="articleIds" value="<s:property value="id"/>" type="checkbox"></td>
                            <td>
                            	<a target="_blank" href="sort?sortByColumn=<s:property value="articletype.linkname"/>" title="<s:property value="articletype.value"/>">
                            		<s:property value="articletype.value"/>
                            	</a>
                            </td>
                            <td title="<s:property value="title"/>"><a target="_blank" href="article/<s:property value="id"/>" title="<s:property value="title"/>"><s:property value="title"/>...</a>
                            </td>
                            <td><s:property value="visits"/>次</td>
                            <td><s:property value="releasetime"/></td>
                            <td><s:property value="comments.size()"/></td>
                            <td>
                               <a class="link-del<s:property value="id"/>" href="javascript:void(0)" onclick="deleteArticle(<s:property value="id"/>);">删除</a>
                            </td>
                        </tr>
                         </s:iterator> 
                    </table>
                    <div class="list-page" rows="1"></div>
                </div>
            </form>
        </div>
    </div> 
    </div> 
   </div> 
</div>
<%@ include file="../bottom.jsp" %>
</div>
</body>
</html>