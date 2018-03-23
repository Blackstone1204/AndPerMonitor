package com.tool.ajax.wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.tool.util.RecvData;

public class Worker implements Runnable{

	private Socket cs;
	private String tempData;
	
	
	double new_tcpu;
	double new_appcpu;
	double total_memory;
	double app_memory;
	
	private static Logger log=Logger.getLogger(Worker.class);;
	public Worker(Socket cs){
		this.cs=cs;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//*******���ؿ���ĳ���˿�*********
		
		
		
		try {
			//

			while(true){

				
				BufferedReader bd=new BufferedReader(new InputStreamReader(cs.getInputStream()));
				PrintWriter pw = new PrintWriter(cs.getOutputStream(), true);  
				String rcvData;
				
				if((rcvData=bd.readLine())!=null){
					
					log.info("Receive Data:"+rcvData);
					if(tempData==null){

						
					}else{
						Log.info("receiver length:"+tempData);
						//cpu����
						long old_t=Long.parseLong(tempData.split(",")[0]);
						long old_idle=Long.parseLong(tempData.split(",")[1]);
						long old_app=Long.parseLong(tempData.split(",")[2]);
						
						
					    long new_t=Long.parseLong(rcvData.split(",")[0]);
						long new_idle=Long.parseLong(rcvData.split(",")[1]);
						long new_app=Long.parseLong(rcvData.split(",")[2]);
						//��cpu���ָ������
						long c_t=new_t-old_t;
						long c_idle=new_idle-old_idle;
						//app cpu���ָ������
						long c_app=new_app-old_app;
						
						//memory����
						long totalRunning=Long.parseLong(rcvData.split(",")[3]);
						long someAppRunning=Long.parseLong(rcvData.split(",")[4]);
						
						//�㷨����

					    new_tcpu=(c_t-c_idle)*100.0/c_t;
					    new_appcpu=c_app*100.0/c_t;
					    total_memory=totalRunning*1.0/1024;
					    app_memory=someAppRunning*1.0/1024;
					    
					    
						RecvData.addData((int)Math.floor(new_tcpu));
						RecvData.addData2((int)Math.floor(new_appcpu));
						RecvData.addData3((int)Math.floor(total_memory));
						RecvData.addData4((int)(Math.floor(app_memory)));
						
						
						log.info("t_cpu:"+new_tcpu);
						log.info("app_cpu:"+new_appcpu);
						log.info("total_memory:"+total_memory);
						log.info("app_memory:"+app_memory);
					}
					
					tempData=rcvData;
					
					//System.out.println("imgData[3]:"+RecvData.getData()[3]);
					
				}
					
					
					
				
				

				
			
				

			}
			
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage().toString());
		
		}
		
	
		
		
	}

}
