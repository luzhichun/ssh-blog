<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.action" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.action">首页</a></li>
                <li><a href="../index.action" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="<%=request.getContextPath()%>/admin/login.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</div>