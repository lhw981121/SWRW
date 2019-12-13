package com.qst.itoffer.bean;

/**
 * 分页类
 * 
 * @author 节奏葳
 * @version 1.0
 */
public class Pagination {
	// 每页显示记录数
	private int pageSize = 10;
	// 当前页码
	private int pageNo = 1;
	// 总页数
	private int totalPages;
	//起始页
	private int start = 1;
	//结束页
	private int end = 10;
	// 是否有下一页
	@SuppressWarnings("unused")
	private boolean hasNextPage;
	// 是否有上一页
	@SuppressWarnings("unused")
	private boolean hasPreviousPage;
	
	public Pagination() {
	}

	/**
	* 初始化分页对象
	* @param recordCount 数据量总数
	* @param pageNo 当前页码
	* @param pageSize 每页显示记录数
	*/
	public Pagination(int recordCount, int pageNo, int pageSize) {
		//根据数据量求总页数
		this.totalPages = recordCount == 0 ? 1 : (recordCount + pageSize - 1) / pageSize;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		//计算起始页和结束页
		if(totalPages <= 10) {//总页数不超过10
			start = 1;
			end = totalPages;
		}else {//总页数大于10
			if (pageNo >= 7) {
				start = pageNo - 5;
				end = pageNo + 4;
			}
			if (start > (totalPages - 10)) {
				start = totalPages - 9;
				end = totalPages;
			}
		}
		//计算是否有上一页
		this.hasPreviousPage = pageNo > 1;
		this.hasNextPage = pageNo < totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		pageNo = pageNo > totalPages ? totalPages : pageNo; 	// 页码大于最大页码的情况
		pageNo = pageNo < 1 ? 1 : pageNo;					 	// 页码小于1的情况
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isHasNextPage() {
		return (this.getPageNo() < this.getTotalPages());
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return (this.getPageNo() > 1);
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
}
