//公共JS函数

/*获取企业状态 -2|-1|0不可用 1招聘中 2已暂停 3已结束*/
function getCompanyState(state){
	switch(state){
	case -2:case -1:case 0:return "<span style='color: red'><i class='fa fa-times-circle'></i>不可用</span>";
	case 1:return "<span class='text-success'><i class='fa fa-play-circle'></i>招聘中</span>";
	case 2:return "<span class='text-info'><i class='fa fa-pause-circle'></i>已暂停</span>";
	case 3:return "<span class='text-danger'><i class='fa fa-stop-circle'></i>已结束</span>";
	default:return "";
	}
}
/*获取审核状态 -2审核未通过 -1等待审核 1|2|3审核通过*/
function getAuditState(state){
	switch(state){
	case -2:return "<span style='color: red'><i class='fa fa-warning'></i>审核未通过</span>";
	case -1:return "<span><i class='fa fa-spinner fa-spin'></i>等待审核</span>";
	case 1:case 2:case 3:return "<span style='color: green'><i class='fa fa-check-circle'></i>审核通过</span>";
	default:return "";
	}
}

/*<!-- 语言切换脚本 -->*/
function changeLanguage(lan){
	$.ajax({
		type:"post",
		url:"/SWRW/ChangeLanguage",
		datatype: "json",
		async:false,
		data:{
			"language":lan,
		},
		success:function(result) {
			location.reload();
		},
		error:function(){
			AjaxError();
		}
	});
}

/*<!-- 页面数据量切换脚本 -->*/
function changePageSize(size){
	$.ajax({
		type:"post",
		url:"/SWRW/ChangePageSize",
		datatype: "json",
		async:true,
		data:{
			"pageSize":size,
		},
		success:function(result) {
			SelectPage(1);
		},
		error:function(){
			AjaxError();
		}
	});
}

//输入不能为空
function InputNotNull(value,str){
	if(value.val().length == 0){
		ErrorTipBottomLeft(str);
        return false;
	}else{ 
		return true;
	}
}

//判断输出是否为null
function IfNull(str){
	if(typeof(str) == "undefined"){
		return '';
	}else{
		return str;
	}
}

//Date格式文本格式化
function DateFormat(date, format) {
	if(date.length==0)	return '';
    date = new Date(date);
    date.setHours(date.getHours());
    var o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'H+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //second
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp('(' + k + ')').test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
    return format;
}

//邮箱隐藏
function HideEmail(email){
	if (String (email).indexOf ('@') > 0) {
        let newEmail, str = email.split('@'), _s = '';

        if (str[0].length > 4) {
            _s = str[0].substr (0, 4);
            for (let i = 0; i < str[0].length - 4; i++) {
                _s += '*';
            }
        } else {
            _s = str[0].substr (0, 1);
            for (let i = 0; i < str[0].length - 1; i++) {
                _s += '*';
            }
        }
        newEmail = _s + '@' + str[1];
        return newEmail;
    } else {
        return email;
    }
}

//手机号隐藏
function HidePhone(phone){
	let newPhone = '';
　　if (phone.length > 7) {
		newPhone=phone.substr(0, 3) + '****' + phone.substr(7);
        return newPhone;
    } else {
        return phone;
    }
}

//获取URL参数
function GetUrlParam(paraName) {
　　var url = document.location.toString();
　　var arrObj = url.split("?");
　　if (arrObj.length > 1) {
　　　　var arrPara = arrObj[1].split("&");
　　　　var arr;
　　　　for (var i = 0; i < arrPara.length; i++) {
　　　　　　arr = arrPara[i].split("=");
　　　　　　if (arr != null && arr[0] == paraName) {
　　　　　　　　return arr[1];
　　　　　　}
　　　　}
　　　　return "";
　　}
　　else {
　　　　return "";
　　}
}

//发送验证码倒计时
function settime(obj,input) {
	if (countdown == 0) {
		input.attr('disabled',false);
		obj.attr('disabled', false);
		obj.html(language=='zh_CN'?"免费获取验证码":"Get Code Free")
		countdown = 60;
		return;
	} else {
		input.attr('disabled',true);
		obj.attr('disabled', true);
		obj.html((language=='zh_CN'?"重新发送":"Resend")+"(" + countdown + ")");
		countdown--;
	}
	setTimeout(function () {settime(obj,input)}, 1000)
}

//检测用户是否访问受限
function UserLimitedAccess(){
	var str;
	if(limitedAccess==1){
		str=language=='zh_CN'?"你是求职者，不能访问企业页面！":"You are a job seeker and cannot access the corporate page!";
	}
	if(limitedAccess==2){
		str=language=='zh_CN'?"你是企业，不能访问求职者页面！":"You are a business and cannot visit the job seeker page!";
	}
	if(limitedAccess==3){
		str=language=='zh_CN'?"管理员页面拒绝访问！":"The admin page is denied access!";
	}
	if(limitedAccess.length != 0){
		swal({
		  title: language=='zh_CN'?"访问受限":"Limited Access",
		  text: str,
		  type: "warning",
		  confirmButtonText: language=='zh_CN'?"关闭":"Close",
		  closeOnConfirm: false
		},
		function(){
			window.location.replace("/SWRW/index");
		});
	}
}

//检测本机用户是否在线
function UserTestLogout(){
	$.ajax({
		url:"/SWRW/UserTestLogout",
		async:true,
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isLogout==true){//用户已被挤下线
				swal({
				  title: language=='zh_CN'?"强制下线":"Forced Offline",
				  text: r.errorMes,
				  type: "warning",
				  confirmButtonText: language=='zh_CN'?"关闭":"Close",
				  closeOnConfirm: false
				},
				function(){
					location.reload();
				});
			}else{//用户在线
			}
		},
		error:function(){
		}
	});
	setTimeout(function () {UserTestLogout()}, 5000);
}

//刷新新消息
var messageType=["dot bg-info","dot bg-success","dot bg-danger","dot bg-warning","dot bg-primary"];//消息类型字符串数组
function RefreshNewMessage(){
	$.ajax({
		url:"/SWRW/RefreshNewMessage",
		async:true,
		success:function(result) {
			var r = JSON.parse(result);
			if(r.count==0){//无新消息
				$('#message_count_1').html('');
				$('#message_count_2').html('');
				$('#message_count_3').html('');
				$('#message_count_4').html('');
				$('#message_count_5').html('');
				$('#message_menu').html('<li><a href="#" class="more">'+(language=='zh_CN'?"未收到消息":"No message received")+'</a></li>');
			}else{//有新消息
				$('#message_count_1').html(r.count);
				$('#message_count_2').html(r.count);
				$('#message_count_3').html(r.count);
				$('#message_count_4').html(r.count);
				$('#message_count_5').html(r.count);
				var newMes = r.newMesList;
				var messageMenuHtml = "";
				$.each(newMes, function (index, mes) {
					var mesTime = new Date(DateFormat(mes.created_at,"yyyy-MM-dd HH:mm:ss")).getTime();
					var nowTime = new Date().getTime();
					if(nowTime-mesTime<1000){
						NewMessageTip(mes.message_summary);
					}
					if(index==10){//只展示10条消息
						messageMenuHtml+='<li><a style="text-align:center">......</a></li>';		
						return false;
					}
					messageMenuHtml+='<li><a href="/SWRW/message/unread_message?id='+mes.message_id+'" class="notification-item"><span class="'+messageType[mes.message_type]+'"></span>'+mes.message_summary+'</a></li>';		
				});
				messageMenuHtml+='<li><a href="/SWRW/message/unread_message" class="more">'+(language=='zh_CN'?"查看所有消息":"View all messages")+'</a></li>';
				$('#message_menu').html(messageMenuHtml.toString());
			}
		},
		error:function(){
		}
	});
	setTimeout(function () {RefreshNewMessage()}, 1000);
}


//页面加载完毕
$(function () {
	//检测用户是否访问受限
	UserLimitedAccess();
	//检测本机用户是否在线
	UserTestLogout();
	//刷新用户新消息
	RefreshNewMessage();
	
})