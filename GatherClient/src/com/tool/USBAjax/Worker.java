package com.tool.USBAjax;

import java.io.PrintWriter;
import java.net.Socket;

import android.content.Context;
import android.util.Log;

import com.tool.util.CpuUtil;
import com.tool.util.MemUtil;

public class Worker implements Runnable{
	Socket cs;
	private String cpuInf;
	private String memoryInf;
	private Context context;
	
	public Worker(Context context,Socket cs){
		
	    this.context=context;
		this.cs=cs;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.i("here","worker");
		try{
			
			PrintWriter pw=new PrintWriter(cs.getOutputStream());
			
			while(true){
				Log.i("worker","here55");
				cpuInf=CpuUtil.getCpuInf(context);
				memoryInf=MemUtil.getRunningProcessInfo(context);
				
				pw.println(cpuInf+","+memoryInf);
				pw.flush();	
				
				Thread.sleep(1000);
			
		}
			
		}catch(Exception ex){
			
			Log.e("error",ex.getMessage().toString());
			
		}

	}

}
