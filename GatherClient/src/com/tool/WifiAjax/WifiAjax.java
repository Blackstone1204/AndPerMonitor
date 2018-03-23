package com.tool.WifiAjax;


import java.io.PrintWriter;

import java.net.Socket;

import com.tool.util.CpuUtil;
import com.tool.util.MemUtil;

import android.content.Context;

import android.util.Log;
import android.widget.Toast;


public class WifiAjax implements Runnable {
	

    private Socket cs;   
	private PrintWriter pw; 
	Context context;
	String pkgName;
	String url;
	String cpuInf;
	String memoryInf;

	
	public  WifiAjax(Context context,String url){

		this.context=context;
		this.url=url;

	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			String ip=url.split(":")[0];
			String port=url.split(":")[1];
			
			cs=new Socket(ip,Integer.parseInt(port));
			
			//bd=new BufferedReader(new InputStreamReader(cs.getInputStream()));
			pw=new PrintWriter(cs.getOutputStream());
			Log.i("here","1");

			//向远端传数据
			while(true){
				
				Log.i("here","2");
				
				cpuInf=CpuUtil.getCpuInf(context);
				memoryInf=MemUtil.getRunningProcessInfo(context);
				
				
				Log.i("cpuInf",cpuInf);
				pw.println(cpuInf+","+memoryInf);
				pw.flush();
				
				Thread.sleep(1000);
				
				
			}
			
			
		
			
		}catch(Exception ex){
			//Log.i("here",ex.toString());	
			if(cpuInf==null){
				Log.e("app error","监听目标app没有打开");
				//Toast.makeText(context, "被监听的app没有打开", Toast.LENGTH_LONG).show();
			
			
			}
			
		
	}

}
}
