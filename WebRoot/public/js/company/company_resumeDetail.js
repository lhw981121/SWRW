//企业查看求职者简历页面脚本

//通知求职者面试
function HireApplicant(apply_id){
	swal({
		title : language=='zh_CN'?"操作提示":"Operation Tips",
		text : language=='zh_CN'?"确定通知该求职者面试？":"Make sure to notify the candidate for an interview?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : false
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/HireApplicant",
			datatype: "json", 
			async:false,
			data:{
				"apply_id":apply_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					swal({
						title: language=='zh_CN'?"操作成功":"Operate Successfully",
						text: r.successMes,
						type: "success",
					},
					function(){
						self.location=document.referrer;
					});
					setTimeout(function () {self.location=document.referrer;}, 2000);
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: r.errorMes,
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

//候选求职者简历
function ShortlistApplicant(apply_id){
	$.ajax({
		type:"post",
		url:"/SWRW/ShortlistApplicant",
		datatype: "json", 
		async:false,
		data:{
			"apply_id":apply_id,
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//操作成功
				swal({
					title: language=='zh_CN'?"操作成功":"Operate Successfully",
					text: r.successMes,
					type: "success",
				},
				function(){
					self.location=document.referrer;
				});
				setTimeout(function () {self.location=document.referrer;}, 2000);
			}else{//操作失败
				swal({
					title: language=='zh_CN'?"操作失败":"Operate Failed",
					text: r.errorMes,
					type: "error",
				});
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

//驳回求职者简历
function RejectedApplicant(apply_id){
	swal({
		title : language=='zh_CN'?"操作提示":"Operation Tips",
		text : language=='zh_CN'?"确定驳回该求职者的简历？":"Make sure to reject the candidate's resume?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : false
	},
	function() {
		$.ajax({
			type:"post",
			url:"/SWRW/RejectedApplicant",
			datatype: "json", 
			async:false,
			data:{
				"apply_id":apply_id,
			},
			success:function(result) {
				var r = JSON.parse(result);
				if(r.isOK==true){//操作成功
					swal({
						title: language=='zh_CN'?"操作成功":"Operate Successfully",
						text: r.successMes,
						type: "success",
					},
					function(){
						self.location=document.referrer;
					});
					setTimeout(function () {self.location=document.referrer;}, 2000);
				}else{//操作失败
					swal({
						title: language=='zh_CN'?"操作失败":"Operate Failed",
						text: r.errorMes,
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