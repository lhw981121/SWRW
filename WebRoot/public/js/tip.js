//提示气泡JS

//错误提示（底部左边）
function ErrorTipBottomLeft(str){
	iziToast.error({
	    title: language=='zh_CN'?"错误":"Error",
	    message: str,
	    position: 'bottomLeft',
	    transitionIn: 'bounceInRight'
	});
}

//错误提示（底部中间）
function ErrorTipBottomCenter(str){
	iziToast.error({
		title: language=='zh_CN'?"错误":"Error",
		message: str,
		position: 'bottomCenter',
		transitionIn: 'flipInX',
		transitionOut: 'flipOutX',
	});
}

//一般提示（底部右边）
function InfoTipBottomRight(str){
	iziToast.info({
		title: language=='zh_CN'?"消息":"Message",
		message: str,
		position: 'bottomRight',
		transitionIn: 'bounceInLeft',
	});
}

//成功提示（底部右边）
function SuccessTipBottomRight(str){
	iziToast.success({
		title: language=='zh_CN'?"成功":"Success",
		message: str,
		position: 'bottomRight',
		transitionIn: 'bounceInLeft',
	});
}

//警告提示
function WarningTipBottomCenter(str){
	iziToast.warning({
		title: language=='zh_CN'?"警告":"Warning",
		message: str,
		position: 'bottomCenter',
		transitionIn: 'flipInX',
		transitionOut: 'flipOutX',
	});
}

//新消息提示
function NewMessageTip(str){
	iziToast.info({
		title: language=='zh_CN'?"新消息":"New Message",
		message: str,
		position: 'topRight',
		transitionIn: 'bounceInLeft',
	});
}




//异步错误提示（中间底部）
function AjaxError(){
	iziToast.error({
		title: language=='zh_CN'?"错误":"Error",
		message: language=='zh_CN'?"遇到未知错误，请重试或联系管理员！":"Encounter an unknown error, please try again or contact your administrator!",
		position: 'bottomCenter',
	});
}