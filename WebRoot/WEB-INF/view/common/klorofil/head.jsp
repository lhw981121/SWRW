<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta content="IE=edge,chrome=1" charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=0.3, maximum-scale=0.5, minimum-scale=0.3">
<title><fmt:message key="WebTitle"/></title>
<!-- Favicon -->
<link rel="icon" href="/SWRW/public/klorofilAssets/img/favicon.png" type="image/png" sizes="32x32">
<!-- VENDOR CSS -->
<link rel="stylesheet" href="/SWRW/public/klorofilAssets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/SWRW/public/klorofilAssets/vendor/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/SWRW/public/klorofilAssets/vendor/linearicons/style.css">
<link rel="stylesheet" href="/SWRW/public/klorofilAssets/vendor/chartist/css/chartist-custom.css">
<!-- MAIN CSS -->
<link rel="stylesheet" href="/SWRW/public/klorofilAssets/css/main.css">
<!-- GOOGLE FONTS -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">

<!-- Sweet Alert -->
<link rel="stylesheet" href="/SWRW/public/assets/css/sweet-alert/sweet-alert.css">

<!-- iziToast -->
<link rel="stylesheet" href="/SWRW/public/assets/css/iziToast/iziToast.css">

<!-- 初始化JS全局变量 -->
<script>
/* 页面语言 */
var language = '${language}';
/* 被登录过滤器拦截的url */
var requestPath = '${requestPath}';
if(requestPath.indexOf("WEB-INF")!=-1)requestPath='';
/* 用户访问受限 */
var limitedAccess = '${limitedAccess}';
/* 单页数据量 */
var pageSize = '${pageSize}';
</script>