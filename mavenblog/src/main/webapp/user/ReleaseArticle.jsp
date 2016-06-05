<%@ page contentType="text/html;charst=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
 <head>  
  	<title>发布文章</title> 
  	<%@ include file="../path.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="file/pic/blog.ico"/>
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="file/js/jquery_cmhello.js"></script>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link href="file/css/style.css" rel="stylesheet" type="text/css">
   <link rel="stylesheet" type="text/css" href="file/css/common.css"/>
   <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
   <script type="text/javascript" src="admin/js/release.js"></script>
    
    <script type="text/javascript" charset="utf-8" src="file/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="file/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="../file/ueditor/lang/zh-cn/zh-cn.js"></script>
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
				<a>发布文章</a>
				</div>
	</div> 
   <div id="art_container clearfix"> 
    <div id="user_art_main" class="fl"> 
    	<div class="main-wrap">
    	<div class="gap"></div>
		<%@include file="navigation.jsp"%>
       
        <div class="result-wrap">
            <div class="result-content">

                <form action="user/Release" method="post" id="valueform" name="valueform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120"><i class="require-red">*</i>分类：</th>
                            <td>
                                <select name="articletypeId" id="catid" class="required">
                                <s:iterator value="chiArticletypes">
                                    <option value="<s:property value="id"/>"><s:property value="value"/></option>
                                </s:iterator>
                                </select>
                            </td>
                        </tr>
                            <tr>
                                <th><i class="require-red">*</i>标题：</th>
                                <td>
                                    <input class="common-text required" id="title" name="title" size="50" value="" type="text">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>缩略图：</th>
                                <td><input name="uploadFile" type="file"><!--<input type="submit" onclick="submitForm('/jscss/admin/design/upload')" value="上传图片"/>--></td>
                            </tr>
                            <tr>
                                <th>内容：</th>
                                <td><script id="editor" type="text/plain" style="width:100%;height:300px;"></script></textarea></td>
                            	<input id="content" type="hidden" name="content" value="">
                            </tr>
                            <tr>
                                <th>文章标签：</th>
                                <td>
                                	<div class="tagbox">
                                	添加文章标签：<input type="text" value="" name="editTag">（输入标签后按Enter,最多添加5个标签）
                						<div id="tags">
										</div>
                		<div class="clearfix"></div>
        
                		<div class="old">
                    	常用标签：
                    		<div class="clearfix"></div>
                    		<s:iterator value="tags">
                    			<span id="<s:property value="id"/>" class="label label-info" name="<s:property value="value"/>"><s:property value="value"/></span>
                    		</s:iterator>
               				 </div>
            						</div>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" onclick="submitForm();" type="button">
                                    <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>
    </div>
    </div> 
   </div> 
</div>
<%@ include file="../bottom.jsp" %>
</div>
</body>
<script type="text/javascript">

	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('editor');
	    function getContent() {
	        var arr = [];
	        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
	        arr.push("内容为：");
	        arr.push(UE.getEditor('editor').getContent());
	        alert(arr.join("\n"));
	    }
    </script>
</html>