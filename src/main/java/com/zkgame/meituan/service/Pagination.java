
package com.zkgame.meituan.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * @author Slbw
 * 
 */
public class Pagination<E> {
	public static final String SORT_DESC = "desc";
	public static final String SORT_ASC = "asc";

	/** 当前页数 */
	private int pageNumber;

	/** 每页记录数 */
	private int pageSize;

	/** 总记录数 */
	private int totalCount;

	/** 记录集 */
	public List<E> items;

	/** 当前页起始记录行 */
	private int firstResult;

	/** 排序字段 */
	private String sortCriterion;

	/** 排序类型（升序或降序） */
	private String sortType;

	private Pagination() {
		firstResult = 0;
		pageNumber = 1;
		pageSize = 20;
	}

	private Pagination(HttpServletRequest request) {
		init(request);
	}

	public static <T> Pagination<T> createPagination(HttpServletRequest request) {
		return new Pagination<T>(request);
	}

	public static <E> Pagination<E> createPaginationNoRequest(int pageNumber, int pageSize) {
		Pagination<E> page = new Pagination<E>();
		page.setPageNumber(pageNumber);
		page.setPageSize(pageSize);
		page.setFirstResult(pageNumber, pageSize);

		return page;
	}

	private void init(HttpServletRequest request) {
		setPageNumber(Integer.parseInt(StringUtils.isEmpty(request.getParameter("pageNum")) ? "1" : request.getParameter("pageNum")));
		setPageSize(Integer.parseInt(StringUtils.isEmpty(request.getParameter("pageSize")) ? "20" : request.getParameter("pageSize")));
		setSortCriterion(StringUtils.isEmpty(request.getParameter("sort")) ? null : request.getParameter("sort"));
		setFirstResult(pageNumber, pageSize);
		setSortType(request.getParameter("order"));
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int pageNumber, int pageSize) {
		if (this.pageNumber > 0) {
			this.firstResult = (this.pageNumber - 1) * this.pageSize;
		} else {
			this.firstResult = 0;
		}
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}