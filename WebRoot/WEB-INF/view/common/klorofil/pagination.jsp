<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 选择页码 -->
<div class="panel-footer">
    <div class="col-md-2">
        <form class="form-inline" id="pageSizeForm" role="form" method="get" action="">
            <div class="form-group">
                <select title="显示行数" id="pageSize" name="pageSize" class="form-control field" onchange="changePageSize($('#pageSize').val());">
                    <option value="10"><fmt:message key="ShowLine1"/></option>
                    <option value="25"><fmt:message key="ShowLine2"/></option>
                    <option value="50"><fmt:message key="ShowLine3"/></option>
                    <option value="100"><fmt:message key="ShowLine4"/></option>
                    <option value="250"><fmt:message key="ShowLine5"/></option>
                    <option value="500"><fmt:message key="ShowLine6"/></option>
                </select>
            </div>
        </form>
    </div>
    <hr>
</div>

<!-- 分页 -->
<div>
	<div class="pull-left">
		<ul class="pagination">
			<!-- 首页 -->
			<c:if test="${notUseScriptPagination }">
			<c:choose>
			<c:when test="${pagination.hasPreviousPage }">
				<li><a href="${urlQueryStr}page=1">首页</a></li><!-- 有首页 -->
				<li><a href="${urlQueryStr}page=${pagination.pageNo-1 }">&laquo;</a></li><!-- 有上一页 -->
			</c:when>
			<c:otherwise>
				<li class="disabled"><a>首页</a></li>
			</c:otherwise>
			</c:choose>
			<!-- 页码 -->
			<c:forEach begin="${pagination.start}" end="${pagination.end}" var="i">
				<c:if test="${i==pagination.pageNo }">
					<li class="active"><span>${i}</span></li><!-- 高亮当前页码 -->
				</c:if>
				<c:if test="${i!=pagination.pageNo }">
					<li><a href="${urlQueryStr}page=${i}">${i}</a></li>
				</c:if>
			</c:forEach>
			<!-- 尾页 -->
			<c:choose>
			<c:when test="${pagination.hasNextPage }">
				<li><a href="${urlQueryStr}page=${pagination.pageNo+1 }">&raquo;</a></li><!-- 有下一页 -->
				<li><a href="${urlQueryStr}page=${pagination.totalPages}">尾页</a></li><!-- 有尾页 -->
			</c:when>
			<c:otherwise>
				<li class="disabled"><a>尾页</a></li>
			</c:otherwise>
			</c:choose>
			</c:if>
		</ul>
	</div>
</div>
<script>
/* JS渲染页码 */
function RenderPagination(pagination){
	$('.pagination').html('');
	if(pagination.hasPreviousPage){
		$('.pagination').append('<li><a href="javascript:;" onclick="SelectPage(1)">首页</a></li>');
		$('.pagination').append('<li><a href="javascript:;" onclick="SelectPage('+(pagination.pageNo-1)+')">&laquo;</a></li>');
	}else{
		$('.pagination').append('<li class="disabled"><a>首页</a></li>');
	}
	for(var i=pagination.start;i<=pagination.end;i++){
		if(pagination.pageNo==i){
			$('.pagination').append('<li class="active"><span>'+i+'</span></li>');
		}else{
			$('.pagination').append('<li><a href="javascript:;" onclick="SelectPage('+i+')">'+i+'</a></li>');
		}
	}
	if(pagination.hasNextPage){
		$('.pagination').append('<li><a href="javascript:;" onclick="SelectPage('+(pagination.pageNo+1)+')">&raquo;</a></li>');
		$('.pagination').append('<li><a href="javascript:;" onclick="SelectPage('+pagination.totalPages+')">尾页</a></li>');
	}else{
		$('.pagination').append('<li class="disabled"><a>尾页</a></li>');
	}
	//选中单页数据
	$('#pageSize').val(pagination.pageSize);
}
</script>

<!-- java.net.URLDecoder
//获取当前url参数urlQueryStr
String urlQueryStr = "?";
if(request.getQueryString()!=null){
	urlQueryStr += URLDecoder.decode(request.getQueryString(),"utf-8") +"&";
}
if(urlQueryStr.indexOf("page")!=-1){
	int pageNo = request.getAttribute("pageNo")==null?1:(Integer)request.getAttribute("pageNo");
	int pageLen = 6+(pageNo+"").length();
	urlQueryStr = urlQueryStr.substring(0, urlQueryStr.length()-pageLen);
}
pageContext.setAttribute("urlQueryStr", urlQueryStr); -->
