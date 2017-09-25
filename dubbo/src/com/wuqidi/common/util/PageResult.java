package com.wuqidi.common.util;

import java.util.HashMap;
import java.util.List;

public class PageResult {
	/**
	 * 总行数
	 */
	private Integer total;
	/**
	 * 数据行
	 */
	@SuppressWarnings("rawtypes")
	private List rows;
	
	public PageResult() {
	}
	public PageResult(Integer total, @SuppressWarnings("rawtypes") List rows) {
		this.total = total;
		this.rows = rows;
	}
	public PageResult(HashMap<String, Object> map, @SuppressWarnings("rawtypes") List rows) {
		this.total = Integer.parseInt((String)map.get("totalRows"));
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}
	public void setRows(@SuppressWarnings("rawtypes") List rows) {
		this.rows = rows;
	}
}