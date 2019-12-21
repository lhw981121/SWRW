<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 分页 -->
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12 mx-auto d-block">
		<div class="pagi-text clearfix">
			<ul class="pagination clearfix">

			</ul>
		</div>
	</div>
	<div class="col-md-12">
        <form class="form-inline" id="pageSizeForm" role="form" method="get" action="">
            <div class="form-group" style="margin-top:20px">
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
</div>

<script>
/* JS渲染页码 */
function RenderPagination(pagination){
	$('.pagination').html('');
	if(pagination.hasPreviousPage){
		$('.pagination').append('<li class="page-item"><a class="page-link" href="javascript:;" onclick="SelectPage(1)">'+(language=="zh_CN"?"首页":"First")+'</a></li>');
		$('.pagination').append('<li class="page-item"><a class="page-link" href="javascript:;" onclick="SelectPage('+(pagination.pageNo-1)+')"><i class="fa fa-angle-double-left" aria-hidden="true"></i></a></li>');
	}else{
		$('.pagination').append('<li class="page-item disabled"><a class="page-link">'+(language=="zh_CN"?"首页":"First")+'</a></li>');
	}
	for(var i=pagination.start;i<=pagination.end;i++){
		if(pagination.pageNo==i){
			$('.pagination').append('<li class="page-item active"><a class="page-link">'+i+'</a></li>');
		}else{
			$('.pagination').append('<li class="page-item"><a class="page-link" href="javascript:;" onclick="SelectPage('+i+')">'+i+'</a></li>');
		}
	}
	if(pagination.hasNextPage){
		$('.pagination').append('<li class="page-item"><a class="page-link" href="javascript:;" onclick="SelectPage('+(pagination.pageNo+1)+')"><i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>');
		$('.pagination').append('<li class="page-item"><a class="page-link" href="javascript:;" onclick="SelectPage('+pagination.totalPages+')">'+(language=="zh_CN"?"尾页":"Last")+'</a></li>');
	}else{
		$('.pagination').append('<li class="page-item disabled"><a class="page-link">'+(language=="zh_CN"?"尾页":"Last")+'</a></li>');
		/* $('.pagination').append('<li class="disabled"><a>尾页</a></li>'); */
	}
	//选中单页数据
	$('#pageSize').val(pagination.pageSize);
}
</script>