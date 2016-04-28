$(function(){
                $('.tagbox').click(function(){
                    $("input[name='editTag']").focus();
                })
                $(document).on("click", "#tags span", function(){
                    $(this).remove();
                });
                $('.old span').click(function(){
                	//$("#content").val(UE.getEditor('editor').getContent());
                    ids=new Array();
                    var txt=$(this).attr('name');
                    var id=$(this).attr('id')
                    $('#tags .label').each(function(){
                        ids+=$(this).attr('id')+','
                    });
                    if(ids==''){
                        ids=new Array();
                    }else{
                        ids = ids.split(",");
                    }
                    if(ids.length>5){
                        alert('标签最多添加5个哦！');
                        return false;
                    };
                    var exist=$.inArray(id,ids);
                    if(exist<0){
                        $('#tags').append('<span id='+id+' name='+txt+' class="label label-info">'+txt+'</span>');
                        var tags = $("input[name='tags']");
                        tags.attr("value",tags.attr("value") + txt);
                    }
                })
                $("input[name='editTag']").bind('keyup',function(event){ 
                    if(event.keyCode==13){  
                        var txt=$(this).val();
                        if(txt!=''){
                            txts=new Array();
                            $('#tags .label').each(function(){
                                txts+=$(this).attr('name')+','
                            });
                            if(txts==''){
                                txts=new Array();
                            }else{
                                txts = txts.split(",");
                            }
                            if(txts.length>5){
                                alert('标签最多添加5个哦！');
                                return false;
                            };
                            var exist=$.inArray(txt,txts);
                            if(exist<0){
                                $('#tags').append('<span name='+txt+' class="label label-info">'+txt+'</span>')
                                $(this).val('');
                            }else{
                                $(this).val('');
                            }
                        }
                    }     
                }); 
            })
$(document).ready(function(){
  $("input[name='uploadFile']").change( function(){
	  //alert("form已经改变");
	  var filepath=$("input[name='uploadFile']").val();
	  if(filepath == ""){
		  //表示未上传文件
		  return false;
	  }
	  var extStart=filepath.lastIndexOf(".");
	  var ext=filepath.substring(extStart,filepath.length).toUpperCase();
	  if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
		  alert("图片限于bmp,png,gif,jpeg,jpg格式");
		  $("input[name='uploadFile']").val("");//取消该文件
		  return false;
	  }
	  var file_size = 0;
	  if ( $.browser.msie) {
		  //表示ie浏览器
		  var img=new Image();
		  img.src=filepath;
		  while(true){
			  if(img.fileSize > 0){
				  if(img.fileSize>2*1024){
					  alert("图片大小不能超过2MB");
					  $("input[name='uploadFile']").val("");//取消该文件
					  return false;
				  }
				  break;
			  }
		  }
	  } else {
		  //其他浏览器
		  file_size = this.files[0].size;
		  var size = file_size / 1024;
		  if(size > 2048){
			  alert("图片大小不能超过2MB");
			  $("input[name='uploadFile']").val("");//取消该文件
			  return false;
		  }
	  }
	  return true;
	  });
 });
function submitForm(){
	var tagsValue ="?tagsValue=";//标签的值
	$("#tags span").each(function(){
		tagsValue += $(this).text() + "|";
	  });
	tagsValue = tagsValue.substring(0, tagsValue.length-1);
	$("#content").val(UE.getEditor('editor').getContent());//提交时，将content内容设置到input中
	$("#valueform").attr("action",$("#valueform").attr("action") + tagsValue);
	$("#valueform").submit();
}

