<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <%@ include file="../path.jsp" %>
    <link rel="shortcut icon" href="admin/file/pic/blog.ico"/>
    <link rel="stylesheet" type="text/css" href="admin/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="admin/css/main.css"/>
    <script type="text/javascript" src="admin/js/libs/modernizr.min.js"></script>
</head>
<body>
<%@include file="headerbar.jsp" %>
<div class="container clearfix">
   <%@include file="sidebar.jsp"%>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎管理员登录</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>快捷操作</h1>
            </div>
            <div class="result-content">
                <div class="short-wrap">
                    <a href="admin/article.jsp"><i class="icon-font">&#xe001;</i>文章管理</a>
                    <a href="admin/column.jsp"><i class="icon-font">&#xe005;</i>栏目管理</a>
                    <a href="admin/comment.jsp"><i class="icon-font">&#xe048;</i>评论管理</a>
                    <a href="admin/usermanage.jsp"><i class="icon-font">&#xe041;</i>用户管理</a>
                    <a href="admin/message.jsp"><i class="icon-font">&#xe01e;</i>留言管理</a>
                </div>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>系统基本信息</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">操作系统</label><span class="res-info"><s:property value="osName"/></span>
                    </li>
                    <li>
                        <label class="res-lab">java运行环境</label><span class="res-info"><s:property value="javaVersion"/></span>
                    </li>
                    <li>
                        <label class="res-lab">jvm名称</label><span class="res-info"><s:property value="jvmName"/></span>
                    </li>
                    <li>
                        <label class="res-lab">上传附件限制</label><span class="res-info">2M</span>
                    </li>
                    <li>
                        <label class="res-lab">服务器域名/IP</label><span class="res-info"><s:property value="ip"/></span>
                    </li>
                    <li>
                        <label class="res-lab">Host</label><span class="res-info"><s:property value="hostName"/></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>