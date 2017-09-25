package com.wuqidi.common.util;

import java.util.List;

public class ZTreeNode {
	private String id;
	private String pid;
	private String name;
	private String type;
	private int level;
	private List<ZTreeNode> children;
	private boolean checked;
	private boolean nocheck;
	private boolean leaf;
	private Object source;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public List<ZTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<ZTreeNode> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public Object getSource() {
		return source;
	}
	public void setSource(Object source) {
		this.source = source;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}