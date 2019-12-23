<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 修改简历照片模态框 -->
<link href="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.css" rel="stylesheet">
<div class="modal fade" id="changeResumePhotoModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false" style="top:3%">
	<div class="modal-dialog">
		<div class="modal-content" style="text-align:center">
			<div class="modal-header">
				<h4 class="modal-title text-primary"><i class="fa fa-file-image-o"></i>&nbsp;<fmt:message key="ChangeResumePhoto" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<p class="tip-info text-center" id="resumePhotoTip"><fmt:message key="UnselectedPicture" /></p>
				<div class="img-container hidden">
					<img src="" alt="" id="photo" height="auto" width="100%">
				</div>
			</div>
			<div class="modal-footer">
				<label class="btn btn-danger" for="photoInput" style="margin-top:8px"><fmt:message key="SelectedPicture" />
					<input type="file" class="sr-only" id="photoInput" accept="image/*" style="display:none">
				</label>
				<button class="btn btn-primary" disabled="disabled" id="submitResumePhoto" onclick="submitResumePhoto();"><fmt:message key="Submit" /></button>
				<button class="btn btn-info" aria-hidden="true" data-dismiss="modal"><fmt:message key="Cancel" /></button>
			</div>
		</div>
	</div>
</div>
<script src="https://cdn.bootcss.com/cropper/3.1.3/cropper.min.js"></script>
<script type="text/javascript">
var initCropperInModal = function(img, input, modal){
	var $image = img;
	var $inputImage = input;
	var $modal = modal;
	var options = {
		aspectRatio : 1, 			//纵横比
		viewMode : 1,				//显示模式
		dragMode : 'move',		//定义cropper的拖拽模式：‘crop’: 可以产生一个新的裁剪框3 ‘move’: 只可以移动3 ‘none’: 什么也不处理
		center : true,				//裁剪区域正中央是否显示+号
		scalable : false, 		//是否允许缩放图像
		zoomable : true,			//是否允许放大图像
		movable : false,			//是否允许可以移动后面的图片
		rotatable : false, 		//是否允许旋转图像
		minCropBoxWidth : 150,	//剪切框宽度最小值
		minCropBoxHeight : 150, //剪切框高度最小值
		autoCropArea : 1,			//自动显示的裁剪框的大小
	};
	// 模态框隐藏后需要保存的数据对象
	var saveData = {};
	var URL = window.URL || window.webkitURL;
	var blobURL;
	$modal.on('show.bs.modal', function() {
		// 如果打开模态框时没有选择文件就点击“打开图片”按钮
		if (!$inputImage.val()) {
			$('#resumePhotoTip').html('<fmt:message key="UnselectedPicture" />');
			$inputImage.click();
		}else{
			$('#resumePhotoTip').html('<fmt:message key="CropPicture" />');
		}
	}).on('shown.bs.modal', function() {
		// 重新创建
		$image.cropper($.extend(options, {
			ready : function() {
				// 当剪切界面就绪后，恢复数据
				if (saveData.canvasData) {
					$image.cropper('setCanvasData', saveData.canvasData);
					$image.cropper('setCropBoxData', saveData.cropBoxData);
				}
			}
		}));
	}).on('hidden.bs.modal', function() {
		// 保存相关数据
		saveData.cropBoxData = $image.cropper('getCropBoxData');
		saveData.canvasData = $image.cropper('getCanvasData');
		// 销毁并将图片保存在img标签
		$image.cropper('destroy').attr('src', blobURL);
	});
	if (URL) {
		$inputImage.change(function() {
			var files = this.files;
			var file;
			if (!$image.data('cropper')) {
				return;
			}
			if (files && files.length) {
				file = files[0];
				if (/^image\/\w+$/.test(file.type)) {
					if (blobURL) {
						URL.revokeObjectURL(blobURL);
					}
					blobURL = URL.createObjectURL(file);
					// 重置cropper，将图像替换
					$image.cropper('reset').cropper('replace', blobURL);
					// 选择文件后，显示和隐藏相关内容
					$('.img-container').removeClass('hidden');
					$('#submitResumePhoto').removeAttr('disabled');
					$('#resumePhotoTip').html('<fmt:message key="CropPicture" />');
				} else {
					alert('<fmt:message key="SelectedPictureError" />');
				}
			}
		});
	} else {
		$inputImage.prop('disabled', true).addClass('disabled');
	}
}

var submitResumePhoto = function () {
	// 得到PNG格式的dataURL
	var photo = $('#photo').cropper('getCroppedCanvas', {
		width: 300,
		height: 300
	}).toDataURL('image/png');
	InfoTipBottomRight(language=='zh_CN'?"简历照片正在上传中。。。":"The resume photo is be uploading...");
	$.ajax({
		type:"post",
		url:"/SWRW/ApplicantUploadResumePhoto",
		datatype: "json",
		async:false,
		data:{
			"basicInfo_id":${basicInfo.getID()},
			"dataURL":photo
		},
		success:function(result) {
			var r = JSON.parse(result);
			if(r.isOK==true){//图片上传成功
				SuccessTipBottomRight(r.successMes);
				// 将上传的图片的地址填入，为保证不载入缓存加个随机数
				$('#resume_photo').attr('src', '/SWRW/public/images/applicant/${basicInfo.getHeadShot()}?t='+Math.random());
				$('#changeResumePhotoModal').modal('hide');
			}else{//图片上传失败
				ErrorTipBottomCenter(r.errorMes);
			}
		},
		error:function(){
			AjaxError();
		}
	});
}

$(function() {
	initCropperInModal($('#photo'), $('#photoInput'), $('#changeResumePhotoModal'));
});
</script>
