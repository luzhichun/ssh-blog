$(document).ready(function(){
		  $("#submit").click(function(){
		  		var cardID = $("[name='cardID']").val();
		  		//alert(cardID);
		  		if(!IDCardCheck(cardID)){
		  			 $("#cardID").text("请输入有效的18位身份证号码");
		  			 $("#place").text("");
	  				 $("#date").text("");
	  				 $("#nl").text("");
	  				 $("#sex").text("");
	  				 $("#number").text("");
	  				 $("#jwd").text("");
		  			return 
		  		}
		  		else{
//			  		$.get("CardID_Serach?cardID=" + cardID, function(data){
//		  				var cMsg = eval('(' + data + ')');//将json字符串转换为json对象
//		  				//alert(jsonObject.number);
//		  				 $("#cardID").text(cardID);
//		  				 $("#place").text(cMsg.place);
//		  				 $("#date").text(cMsg.date);
//		  				 $("#nl").text(cMsg.nl);
//		  				 $("#sex").text(cMsg.sex);
//		  				 $("#number").text(cMsg.number);
//		  				 $("#jwd").text(cMsg.jwd);
//					});
			  		$.ajax({ 
			            type: "post", 
			            url: "CardID_Serach?cardID=" + cardID, 
			            dataType: "json", 
			            success: function (cMsg) { 
			            	$("#cardID").text(cardID);
			  				$("#place").text(cMsg.place);
			  				$("#date").text(cMsg.date);
			  				$("#nl").text(cMsg.nl);
			  				$("#sex").text(cMsg.sex);
			  				$("#number").text(cMsg.number);
			  				$("#jwd").text(cMsg.jwd); 
			            }, 
			            error: function (XMLHttpRequest, textStatus, errorThrown) { 
			                alert(errorThrown); 
			            } 
			        });
		  		}
		  });
});
		
function IDCardCheck(num) {
    num = num.toUpperCase();
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
        // alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
        return false;
    }
    // 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    // 下面分别分析出生日期和校验位
    var len, re;
    len = num.length;
    if (len == 15) {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);

        // 检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
           // alert('输入的身份证号里出生日期不对！');
            return false;
        }
        else {
            // 将15位身份证转成18位
            // 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            num += arrCh[nTemp % 11];
            return true;
        }
    }
    if (len == 18) {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);

        // 检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
           // alert(dtmBirth.getYear());
           // alert(arrSplit[2]);
           // alert('输入的身份证号里出生日期不对！');
            return false;
        }
        else {
            // 检验18位身份证的校验码是否正确。
            // 校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != num.substr(17, 1)) {
                // alert('18位身份证的校验码不正确！'); //应该为： + valnum
                return false;
            }
            return true;
        }
    }
    return false;
}
		