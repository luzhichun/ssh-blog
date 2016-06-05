<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>留言管理</title>
    <%@ include file="../path.jsp" %>
    <link rel="shortcut icon" href="admin/file/pic/blog.ico"/>
    <link rel="stylesheet" type="text/css" href="admin/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="admin/css/main.css"/>
    <script type="text/javascript" src="admin/js/libs/modernizr.min.js"></script>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="admin/index">首页</a>
            <span class="crumb-step">&gt;</span>
            <span class="crumb-name">留言管理</span>
            <span class="crumb-step">&gt;</span>
            <s:if test="%{sort==all||sort==null}">
            	<span class="crumb-name">全部留言</span>
            </s:if>
            <s:else>
            	<span class="crumb-name">待审核留言</span>
            </s:else>
        </div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="admin/message" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="sort" id="">
                                    <option value="all">全部留言</option>
                                    <option value="unaditing" 
                                    	<s:if test="%{sort!=\"all\"&&sort!=null}">
                                    		selected="selected"
                                    	</s:if>
                                    >待审核留言</option>
                                </select>
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a id="messageBatchThrough" href="javascript:void(0)"><i class="icon-font"></i>批量通过</a>
                        <a id="messageBatchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>刷新</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input id="allMessageChoose" name="" type="checkbox"></th>
                            <th width="5%">ID</th>
                            <th width="8%">留言人</th>
                            <th width="15%">邮箱</th>
                            <th>留言内容</th>
                            <th width="13%">留言时间</th>
                            <th width="8%">通过状态</th>
                            <th width="8%">审核状态</th>
                            <th width="16%">操作</th>
                        </tr>
                         <s:iterator value="messages">
                        <tr id="tr_<s:property value="id"/>">
                            <td class="tc"><input name="messageIds" value="<s:property value="id"/>" type="checkbox"></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="user.username"/></td>
                            <td><s:property value="user.email"/></td>
                            <td><s:property value="content"/></td>
                            <td><s:property value="time"/></td>
                            <td id="tr_<s:property value="id"/>-through"><s:property value="throughFlag"/></td>
                            <td id="tr_<s:property value="id"/>-auditing"><s:property value="auditingFlag"/></td>
                            <td>
                                <a class="link-auditing-success" href="javascript:void(0)" onclick="auditing(<s:property value="id"/>,1);">审核通过</a>
                                <a class="link-auditing-failure" href="javascript:void(0)" onclick="auditing(<s:property value="id"/>,0);">审核失败</a>
                                <a class="link-del" href="javascript:void(0)" onclick="deleteMessage(<s:property value="id"/>);">删除</a>
                            </td>
                        </tr>
                        </s:iterator>
                    </table>
                    <div class="list-page" rows=""></div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>