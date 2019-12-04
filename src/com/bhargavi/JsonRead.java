package com.bhargavi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class JsonRead {

	public static int elemCount ;
	public static void main(String[] args) {	

		BufferedReader in;
	try {
			String url = "https://raw.githubusercontent.com/jdolan/quetoo/master/src/cgame/default/ui/settings/SystemViewController.json";
			URL urlRes = new URL(url);
			HttpURLConnection con = (HttpURLConnection)urlRes.openConnection();
		   in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while((inputLine=in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		TreeView tv = new TreeView();
		tv = tv.consturctTreeViewFromJson(response.toString());
		boolean flag=true;
		while(flag) {
		System.out.println("enter selector(enter 'Q' or 'q' to quit)");
		Scanner scanner = new Scanner(System.in);
		String selectorInput = scanner.next();
		if(selectorInput.substring(0,1).equals("#"))
			selectorInput = selectorInput.substring(1);
		System.out.println("user entered selector as: " + selectorInput );
		if(selectorInput.equalsIgnoreCase("q")) {
			scanner.close();
			flag=false;
		}
		else {
		JsonRead.elemCount=0;
		tv.getView(selectorInput);
		System.out.println("Total number of views for the input Selector '" + selectorInput + "' = "  
						+  JsonRead.elemCount);
		
		}
		}
		}
	    catch(IOException e) {
	    	System.out.println("IO exception occurred ");
	    }
		catch(Exception e) {
			System.out.println("exception occurred ");
		//	e.printStackTrace();
		}
	}

	public static void printIndent(int indent) {
		for(int i=0;i<indent;i++) {
			System.out.printf("\t");
		}
	}

}
