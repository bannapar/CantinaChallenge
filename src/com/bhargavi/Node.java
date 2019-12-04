package com.bhargavi;

import java.util.ArrayList;
import java.util.List;

public class Node {
	protected String classVar;
	protected List<Node> childNodeList;
	
	Node() {
		this.classVar = null;
		this.childNodeList = new ArrayList<Node>();
	}

	public String getClassVar() {
		return classVar;
	}

	public void setClassVar(String classVar) {
		this.classVar = classVar;
	}

	public List<Node> getChildNodeList() {
		return childNodeList;
	}

	public void setChildNodeList(List<Node> childNodeList) {
		this.childNodeList = childNodeList;
	}

	public void addToChildNodesList(Node n) {
		this.childNodeList.add(n);
	}
	
	public void printInputSelectorView(String inputSel,boolean flag) {
		if (this.getClassVar().equalsIgnoreCase("stackview")) {
			if(flag && inputSel.equalsIgnoreCase("stackview")) {
				if(flag)
					JsonRead.elemCount++;
				StringBuilder sb = new StringBuilder();
				sb.append("{  \"class\": \"").append(this.getClassVar()).append("\",\n");
				sb.append("\"subviews\": [\n ");	
				System.out.println(sb.toString());
			}
		}
		else if (this.getClassVar().equalsIgnoreCase("contentview")) {
			if(flag && inputSel.equalsIgnoreCase("contentview")) {
				if(flag)
					JsonRead.elemCount++;
				StringBuilder sb = new StringBuilder();
				sb.append("{  \"contentView\": {");
				sb.append("\"subviews\": [\n ");	
				System.out.println(sb.toString());
			}
		}
	}

	public String toString() {
		return (this.classVar + "  with " + this.childNodeList.size() + "chldNodes");
	}
}
