package com.tool.ajax.wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable{
	
	private ServerSocket s;
	private Socket cs;
	private int i=0;
	
	public Listener(ServerSocket s){
		this.s=s;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				
				if((cs=s.accept())!=null){
					
					//socket对象维护	
				  MySocket.add(cs);
				
				  System.out.println("连接来自："+cs.getInetAddress().getHostAddress()+":"+cs.getPort());
				  
				  new Thread(new Worker(cs)).start();
		

					
				}
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
