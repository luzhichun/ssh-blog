<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>栏目管理</title>
    <%@ include file="../path.jsp" %>
    <link rel="shortcut icon" href="file/pic/blog.ico"/>
    <link rel="stylesheet" type="text/css" href="admin/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="admin/css/main.css"/>
    <script type="text/javascript" src="admin/js/libs/modernizr.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="admin/js/column.js"></script>
    
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
            <span class="crumb-name">栏目管理</span>
        </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a id="add-column" href="javascript:void(0)"><i class="icon-font"></i>添加栏目</a>
                        <a id="updateOrd" href="column.jsp"><i class="icon-font"></i>刷新</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>栏目</th>
                            <th>linkName</th>
                            <th>父栏目</th>
                            <th>操作</th>
                        </tr>
						<s:iterator value="chiArticletypes">
                        <tr>
                        	<td><s:property value="id"/></td>
                       		<td><s:property value="value"/></td>
                            <td><s:property value="linkname"/></td>
                            <td><s:property value="parArticletype.value"/></td>
                            <td>
                             	<a class="link-auditing-success" href="javascript:void(0)">修改栏目</a>
                            </td>
                        </tr>
						</s:iterator>
                    </table>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
<div id="ds-wrapper" style="display:none">
	<div class="ds-dialog" id="ds-reset" style="width: 320px;">
		<div class="ds-dialog-inner ds-rounded">
			<div class="ds-dialog-body">
				<form>
					<div class="ds-control-group">
					<label for="ds-dialog-name">请选择要添加栏目的类型</label>
					<select name="parentColumn" id="parentColumn">
					<s:iterator value="parArticletypes">
						<option value="<s:property value="id"/>"><s:property value="value"/></option>
					</s:iterator>
					</select>
					</div>
					<div class="ds-control-group">	
						<br>
						<p><label for="ds-dialog-email">栏&nbsp目&nbsp名(必填)</label></p>
						<br>
						<p><input type="text" name="columnNname" id="columnNname"></p>
						<br>
						<p><label for="ds-dialog-email">访&nbsp问&nbsp名(必填)</label></p>
						<br>
						<p><input type="text" name="linkName" id="linkName"></p>
						<br>
					</div>
				</form>
				<button id="submit">确定</button><button id="cancel">取消</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>