package com.tool.ajax.wifi;

import java.net.Socket;
import java.util.ArrayList;

public class MySocket {
	
	private static ArrayList<Socket> aList=new ArrayList<Socket>();
	
	
	public static void add(Socket cs){
		aList.add(cs);	
		
	}
	
	public  static ArrayList<Socket> getList(){
		return aList;
		
	}
	
	public static int getLength(){
		
		return aList.size();
	}
	
	
	public static void destoryAll(){
		
		aList=null;
	}
	
	
	public static void main(String[] args){
		System.out.println(new MySocket().getLength());
	}
	

}
