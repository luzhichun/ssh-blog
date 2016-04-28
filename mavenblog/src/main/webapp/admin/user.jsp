<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head> 
    <meta charset="UTF-8">
    <title>用户管理</title>
    <%@ include file="../path.jsp" %>
    <link rel="shortcut icon" href="admin/file/pic/blog.ico"/>
    <link rel="stylesheet" type="text/css" href="admin/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="admin/css/main.css"/>
    <script type="text/javascript" src="admin/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="admin/js/Login_javascript.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="admin/js/user.js"></script>
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
            	<span class="crumb-name">用户管理</span>
            </div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/user.jsp" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="sort" id="select-serach">
                                	<option value="all">所有用户</option>
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
                    	 <a id="userBatchThrough" href="javascript:void(0)"><i class="icon-font"></i>批量通过</a>
                        <a id="userBatchClosure" href="javascript:void(0)"><i class="icon-font"></i>批量封禁</a>
                        <a id="updateOrd" href="admin/user.jsp"><i class="icon-font"></i>刷新</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input id="allUserChoose" name="" type="checkbox"></th>
                            <th width="5%">ID</th>
                            <th width="10%">用户名</th>
                            <th width="20%">邮箱</th>
                            <th>博客地址</th>
                            <th width="10%">用户标识</th>
                            <th width="15%">注册时间</th>
                            <th>操作</th>
                        </tr>
                         <s:iterator value="users">
                        <tr id="tr_<s:property value="id"/>">
                            <td class="tc"><input name="userIds" value="<s:property value="id"/>" type="checkbox"></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="username"/></td>
                            <td><s:property value="email"/></td>
                            <td title="<s:property value="title"/>"><a target="_blank" href="admin/user/<s:property value="url"/>">user/<s:property value="url"/></a>
                            </td>
                            <td id="tr_<s:property value="id"/>-through"><s:property value="throughFlag"/></td>
                            <td><s:property value="registertime"/></td>
                            <td>
                            	<s:if test="%{throughFlag == 0}">
                                	<a class="link-through<s:property value="id"/>" href="javascript:void(0)" onclick="auditingUser(<s:property value="id"/>,1);">审核通过</a>
                                </s:if>
                                <s:else>
                                <a class="link-del<s:property value="id"/>" href="javascript:void(0)" onclick="auditingUser(<s:property value="id"/>,0);">封禁用户</a>
                            	</s:else>
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