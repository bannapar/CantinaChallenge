package com.bhargavi;

public class LeafNode extends Node{
	private String Label;
	private Control cntrl;

	LeafNode() {
		super();

		this.setChildNodeList(null);
		this.cntrl = null;
		this.Label = null;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		this.Label = label;
	}

	public String toString() {
		return (this.classVar + "  with Label = " + this.getLabel() + "and control= " + this.getCntrl());
	}

	public Control getCntrl() {
		return cntrl;
	}

	public void setCntrl(Control cntrl) {
		this.cntrl = cntrl;
	}

	public void printInputSelectorView(String inputSel,boolean flag) {

		try {
			if(flag || (inputSel.equalsIgnoreCase("input"))) {
				if(this.getClassVar().equalsIgnoreCase("input")) {
					if(!flag)
						JsonRead.elemCount++;
					StringBuilder sb = new StringBuilder();
					sb.append("{\n\"class\": \"").append(this.getClassVar()).append("\",\n");
					sb.append("\"label\": { \"text\": { \"text\": \"").append(this.getLabel()).append("\" } },\n");
					sb.append("\"control\": { \"class\": \"");
					sb.append(this.getCntrl().getClassVar());
					sb.append("\", \"identifier\": \"");
					sb.append(this.getCntrl().getIdentifier());
					sb.append("\", \"var\": \"");
					sb.append(this.getCntrl().getVarStr());
					sb.append("\" } \n }");
					System.out.println(sb.toString());
				}
			}
			else if(flag || (inputSel.equalsIgnoreCase("button"))) {
				if(this.getClassVar().equalsIgnoreCase("button")) {
					if(!flag)
						JsonRead.elemCount++;
					StringBuilder sb = new StringBuilder();
					sb.append("{\n\"class\": \"").append(this.getClassVar()).append("\",\n");
					sb.append("\"title\": { \"text\": \"").append(this.getLabel()).append("\" } \n} \n");
					System.out.println(sb.toString());
				}
			}
			else if(flag || (inputSel.equalsIgnoreCase(this.getCntrl().getIdentifier()))) {
				if(!flag)
					JsonRead.elemCount++;
				StringBuilder sb = new StringBuilder();
				sb.append("{\n\"class\": \"").append(this.getClassVar()).append("\",\n");
				sb.append("\"label\": { \"text\": { \"text\": \"").append(this.getLabel()).append("\" } },\n");
				sb.append("\"control\": { \"class\": \"");
				sb.append(this.getCntrl().getClassVar());
				sb.append("\", \"identifier\": \"");
				sb.append(this.getCntrl().getIdentifier());
				sb.append("\", \"var\": \"");
				sb.append(this.getCntrl().getVarStr());
				sb.append("\" } \n }");
				System.out.println(sb.toString());
			} 
		}
		catch(Exception e) {
			//	System.out.println("exception occured");
		}

	}



}
