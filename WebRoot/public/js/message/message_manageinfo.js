//消息管理JS

//删除消息
function deleteMessage(message_id) {
	swal({
		title : language=='zh_CN'?"确定要删除这条消息吗？":"Are you sure you want to delete this message?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : true
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/MessageDelete",
			datatype: "json",
			async:true,
			data:{
				"message_id":message_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					$('#message_block_'+message_id).remove();
					RefreshNewMessage();
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	});
}

//删除所有未读消息
function deleteUnReadMessage(receiver_id){
	swal({
		title : language=='zh_CN'?"确定要删除所有未读消息？":"Are you sure you want to delete all unread messages?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : true
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/MessageDeleteUnRead",
			datatype: "json",
			async:true,
			data:{
				"receiver_id":receiver_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					window.location.reload();
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	});
}

//删除所有已读消息
function deleteReadMessage(receiver_id){
	swal({
		title : language=='zh_CN'?"确定要删除所有已读消息？":"Are you sure you want to delete all read messages?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : true
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/MessageDeleteRead",
			datatype: "json",
			async:true,
			data:{
				"receiver_id":receiver_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					window.location.reload();
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	});
}

//删除全部消息
function deleteAllMessage(receiver_id){
	swal({
		title : language=='zh_CN'?"确定要删除所有消息？":"Are you sure you want to delete all messages?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : true
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/MessageDeleteAll",
			datatype: "json",
			async:true,
			data:{
				"receiver_id":receiver_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					window.location.reload();
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	});
}

//标记消息为已读
function markMessageRead(message_id){
	$.ajax({
		type:"post",
		url:"/SWRW/MessageMarkRead",
		datatype: "json",
		async:true,
		data:{
			"message_id":message_id,
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//操作成功
				$('#message_block_'+message_id).remove();
				RefreshNewMessage();
			}else{//操作失败
				swal({
					title: language=='zh_CN'?"操作失败":"Operate Failed",
					text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

//标记消息为未读
function markMessageUnRead(message_id){
	$.ajax({
		type:"post",
		url:"/SWRW/MessageMarkUnRead",
		datatype: "json",
		async:true,
		data:{
			"message_id":message_id,
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//操作成功
				$('#message_block_'+message_id).remove();
				RefreshNewMessage();
			}else{//操作失败
				swal({
					title: language=='zh_CN'?"操作失败":"Operate Failed",
					text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

//标记所有消息为已读
function markAllMessageRead(receiver_id){
	swal({
		title : language=='zh_CN'?"确定要标记所有消息为已读吗？":"Are you sure you want to mark all messages as read?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : true
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/MessageMarkAllRead",
			datatype: "json",
			async:true,
			data:{
				"receiver_id":receiver_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					window.location.reload();
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: language=='zh_CN'?"遇到未知问题，请重试或联系管理员！":"Encounter unknown problem, please try again or contact administrator!",
						type: "error",
					});
				}
			},
			error:function(){
				AjaxError();
			}
		});
	});
}



/*<!-- 滑动到指定消息 -->*/
var x = $("#"+GetUrlParam('id')).offset().left;
var y = $("#"+GetUrlParam('id')).offset().top;
$.scrollTo(y-350,x);
