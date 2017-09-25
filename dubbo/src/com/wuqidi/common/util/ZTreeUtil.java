package com.wuqidi.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZTreeUtil {
	public static List<ZTreeNode> encapsulate(List<ZTreeNode> list) {
		if( list!=null && !list.isEmpty() ){ 
			List<ZTreeNode> roots = new ArrayList<ZTreeNode>();
			for (ZTreeNode node : list) {
				if( node.getPid()==null || "".equals(node.getPid()) ) {
					roots.add(node);
				}
			}
			for (ZTreeNode root : roots) {
				findChildNodes(root,list);
			}
			return roots;
		}
		return null;
	}
	private static void findChildNodes(ZTreeNode root,List<ZTreeNode> nodeList) {
		if(nodeList==null||nodeList.isEmpty()){
			return ;
		}
		for (ZTreeNode node : nodeList) {
			if( node.getPid()!=null && root.getId().equals(node.getPid()) ) {
				if(root.getChildren()==null){
					List<ZTreeNode> children = new ArrayList<ZTreeNode>();
					children.add(node);
					root.setChildren(children);
				}else{
					root.getChildren().add(node);
				}
			}
		}
		if(root.getChildren()!=null){
			for (ZTreeNode child : root.getChildren()) {
				findChildNodes(child,nodeList);
			}
		}
	}
	public static List<ZTreeNode> getZTree(String rootName){
		return getZTree(null,rootName,"0");
	}
	public static List<ZTreeNode> getZTree(List<ZTreeNode> ztree,String rootName, String rootId){
		if(ztree==null){
			ztree = new ArrayList<ZTreeNode>();
		}
		ZTreeNode root = new ZTreeNode();
		root.setId(rootId);
		root.setPid(null);
		root.setName(rootName);
		root.setType("root");
		ztree.add(root);
		return ztree;
	}
	/**
	 * 获取节点node深度
	 * @param list 节点树集合
	 * @param node 节点
	 * @return
	 */
	public static int getLevel(List<ZTreeNode> list, ZTreeNode node){
		HashMap<String, String> map = new HashMap<String, String>();
		for (ZTreeNode ztreeNode : parse2ArrayList(list)) {
			map.put(ztreeNode.getId(), ztreeNode.getPid());
		}
		int i=1;
		String id = node.getId();
		while(map.get(id)!=null){
			id = map.get(id);
			i++;
		}
		return i;
	}
	public static ArrayList<ZTreeNode> parse2ArrayList(List<ZTreeNode> list){
		ArrayList<ZTreeNode> nodes = new ArrayList<ZTreeNode>();
		for (ZTreeNode node :list) {
			getChildNodes(nodes,node);
		}
		return nodes;
	}
	private static void getChildNodes(ArrayList<ZTreeNode> nodes,ZTreeNode node) {
		nodes.add(node);
		if(node.getChildren()!=null && node.getChildren().size()>0 ){
			for (ZTreeNode zTreeNode : node.getChildren()) {
				getChildNodes(nodes,zTreeNode);
			}
		}
	}
	/**
	 * 生成树节点的level
	 * @param tree
	 */
	public static void parseZtreeDeep(List<ZTreeNode> tree) {
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<ZTreeNode> nodes = parse2ArrayList(tree);
		for (ZTreeNode node : nodes) {
			map.put(node.getId(), node.getPid());
		}
		for (ZTreeNode node : nodes) {
			int i=1;
			String id = node.getId();
			while(map.get(id)!=null){
				id = map.get(id);
				i++;
			}
			node.setLevel(i);
		}
	}
}