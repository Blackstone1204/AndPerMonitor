package com.tool.USBAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import android.content.Context;
import android.util.Log;

import com.tool.util.CpuUtil;
import com.tool.util.MemUtil;

public class Listener implements Runnable{
	private Context context;
	
	private ServerSocket s;

	private Socket cs;

	public Listener(Context context,ServerSocket s){
		this.context=context;
		this.s=s;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.i("here","listener");
		while(true){
			
			try {
				if((cs=s.accept())!=null)new Thread(new Worker(context,cs)).start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
     }
	
		

		
		
	}
	


