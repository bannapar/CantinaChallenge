package com.bhargavi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TreeView {
	
	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	public void getView(String inputSel) {
		Node node = this.getRoot();
		getSubViewsForInput(node,inputSel,false);
	}

	public void getSubViewsForInput(Node node,String inputSel,boolean inputFlag) {
		for(int i=0;i<node.getChildNodeList().size();i++) {
			Node childNode = node.getChildNodeList().get(i);
			if(childNode instanceof LeafNode)
				childNode.printInputSelectorView(inputSel,inputFlag);
			else {
				if((childNode.getClassVar().equalsIgnoreCase("contentview")) && (inputSel.equalsIgnoreCase("contentview"))) {
					inputFlag = true;
					childNode.printInputSelectorView(inputSel,inputFlag);
				}
				if((childNode.getClassVar().equalsIgnoreCase("stackview")) && (inputSel.equalsIgnoreCase("stackview"))) {
					inputFlag = true;
					childNode.printInputSelectorView(inputSel,inputFlag);
				}
				getSubViewsForInput(childNode,inputSel,inputFlag);
			}
		}
		if(inputFlag) {
			System.out.println("] }");
			inputFlag=false;
		}
	}

	public TreeView consturctTreeViewFromJson(String response) {
		JSONParser jsonParser = new JSONParser();

		try {
			JSONObject obj = (JSONObject) jsonParser.parse(response);
			if(obj.containsKey("subviews")) {
				JSONArray subviews = (JSONArray)obj.get("subviews");

				Node root = new Node();
				root.setClassVar("root");
				this.setRoot(root);
				readSubViews(subviews,root);
			}
		}
		catch(Exception e) {
			System.out.println("exception occurred ");
			//	e.printStackTrace();
		};
		return this;
	}

	public void readSubViews(JSONArray subviews,Node parent) {
		for(int i=0;i<subviews.toArray().length;i++) {
			JSONObject obj = (JSONObject)subviews.get(i);
			if(obj.containsKey("class")) {
				if(((String)(obj.get("class"))).equals("StackView")) {
					Node node = new Node();
					node.setClassVar("StackView");
					parent.addToChildNodesList(node);
					if(obj.containsKey("subviews")) {
						JSONArray subviews1 = (JSONArray)obj.get("subviews");
						readSubViews(subviews1,node);
					}
				}
				else if(((String)(obj.get("class"))).equals("Box")) {
					if(obj.containsKey("contentView")) {
						JSONObject contentView = (JSONObject)obj.get("contentView");
						Node node = new Node();
						node.setClassVar("ContentView");
						parent.addToChildNodesList(node);
						if(contentView.containsKey("subviews")) {
							JSONArray subviews1 = (JSONArray)contentView.get("subviews");
							readSubViews(subviews1,node);	
						}
					}
				}
				else if(((String)(obj.get("class"))).equals("Input")) {
					LeafNode node = new LeafNode();
					node.setClassVar("Input");			
					if(obj.containsKey("label")) {
						if (((JSONObject)obj.get("label")).containsKey("text")) {
							if (((JSONObject)((JSONObject)obj.get("label")).get("text")).containsKey("text")){
								node.setLabel(((String)((JSONObject)((JSONObject)obj.get("label")).get("text")).get("text")));
							}
						}
					}

					Control tempctrl = new Control();
					if(obj.containsKey("control")) {
						if (((JSONObject)obj.get("control")).containsKey("class")) {
							tempctrl.setClassVar((String)(((JSONObject)obj.get("control")).get("class")));
						}
						if (((JSONObject)obj.get("control")).containsKey("identifier")) {
							tempctrl.setIdentifier((String)(((JSONObject)obj.get("control")).get("identifier")));
						}
						if (((JSONObject)obj.get("control")).containsKey("var")) {
							tempctrl.setVarStr((String)(((JSONObject)obj.get("control")).get("var")));
						}
						node.setCntrl(tempctrl);
					}
					parent.addToChildNodesList(node);

				}
				else if(((String)(obj.get("class"))).equals("Button")) {
					LeafNode node = new LeafNode();
					node.setClassVar("Button");
					if(obj.containsKey("title")) {
						if (((JSONObject)obj.get("title")).containsKey("text")) {
							node.setLabel((String)(((JSONObject)obj.get("title")).get("text")));
						}
					}
					parent.addToChildNodesList(node);

				}
			}
		}
	}



}
