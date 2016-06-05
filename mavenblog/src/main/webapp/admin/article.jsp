<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head> 
    <meta charset="UTF-8">
    <title>文章管理</title>
    <%@ include file="../path.jsp" %>
    <link rel="shortcut icon" href="file/pic/blog.ico"/>
    <link rel="stylesheet" type="text/css" href="admin/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="admin/css/main.css"/>
    <script type="text/javascript" src="admin/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="admin/js/Login_javascript.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="admin/js/myjquery.js"></script>
</head>
<body>
<%@include file="headerbar.jsp" %>
<div class="container clearfix">
    <%@include file="sidebar.jsp"%>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>
            	<a href="admin/index.jsp">首页</a>
            	<span class="crumb-step">&gt;</span>
            	<span class="crumb-name">博文管理</span>
            	<span class=\"crumb-step\">&gt;</span>
            	<s:if test="%{sort!=null&&sort!=\"all\"}">
					<span class=\"crumb-name\"><s:property value="articletype.value"/></span>
            	</s:if>
            	<s:else>
					<span class=\"crumb-name\">全部文章</span>
            	</s:else>
            </div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/article.jsp" method="post">
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
                        <a id="articleBatchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="admin/article.jsp"><i class="icon-font"></i>刷新</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input id="allArticleChoose" name="" type="checkbox"></th>
                            <th width="5%">ID</th>
                            <th width="10%">所属栏目</th>
                            <th>标题</th>
                            <th width="5%">审核状态</th>
                            <th width="8%">点击</th>
                            <th width="8%">发布人</th>
                            <th width="15%">更新时间</th>
                            <th width="5%">评论</th>
                            <th width="10%">操作</th>
                        </tr>
                         <s:iterator value="#request.articles">
                        <tr id="tr_<s:property value="id"/>">
                            <td class="tc"><input name="articleIds" value="<s:property value="id"/>" type="checkbox"></td>
                            <td><s:property value="id"/></td>
                            <td>
                            	<a target="_blank" href="sort?sortByColumn=<s:property value="articletype.linkname"/>" title="<s:property value="articletype.value"/>">
                            		<s:property value="articletype.value"/>
                            	</a>
                            </td>
                            <td title="<s:property value="title"/>"><a target="_blank" href="<%=request.getContextPath() %>/article/<s:property value="id"/>" title="<s:property value="title"/>"><s:property value="title"/>...</a>
                            </td>
                            <td>1</td>
                            <td><s:property value="visits"/>次</td>
                            <td><s:property value="user.username"/></td>
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
    <!--/main-->
</div>
</body>
</html>