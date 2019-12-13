//弹出框封装JS

//确认框 提示文本，确认事件
function swalConfirm(str,func) {
	swal({
		title : str,
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : language=='zh_CN'?"确定":"Sure",
		cancelButtonText: language=='zh_CN'?"取消":"Cancel",
		closeOnConfirm : false
	},
	function() {
		func();
	});
}

//确认框 提示标题，提示文本，确认事件，取消事件
function swalConfirmCancel(title,text,func1,func2){
	swal({
		title : "Are you sure?",
		text : "You will not be able to recover this imaginary file!",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Yes, delete it!",
		cancelButtonText : "No, cancel plx!",
		closeOnConfirm : false,
		closeOnCancel : false
	},
	function(isConfirm) {
		if (isConfirm) {
			func1();
		} else {
			func2();
		}
	});
}