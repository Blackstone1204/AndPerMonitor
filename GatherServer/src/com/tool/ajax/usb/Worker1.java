package com.tool.ajax.usb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.tool.ajax.wifi.Worker;
import com.tool.util.RecvData;

public class Worker1 implements Runnable{
	
	Socket cs;
	String tempData = null;
	
	double new_tcpu;
	double new_appcpu;
	double total_memory;
	double app_memory;
	
	int port;
	

	private static Logger log=Logger.getLogger(Worker1.class);
	
	
	
	public Worker1(int port){
		
		this.port=port;
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
			/**
			* @Description:TODO
			* @param 
			* @author: BlackStone
			* @time:2016��6��8������4:47:28
			*/
		
		try{
			cs=new Socket("127.0.0.1",port);
			
			File f=new File("bak.data");
			if(!f.exists())f.createNewFile();
			PrintWriter pw_bak=new PrintWriter(new FileOutputStream(f.getAbsolutePath()),true);
			pw_bak.println("cpuT  cpuA  memT  memA\n\n");
			
			while(true){
				
				BufferedReader bd=new BufferedReader(new InputStreamReader(cs.getInputStream()));
	
		
				
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
					    
					    int d1=(int)Math.floor(new_tcpu);
					    int d2=(int)Math.floor(new_appcpu);
					    int d3=(int)Math.floor(total_memory);
					    int d4=(int)Math.floor(app_memory);
						RecvData.addData(d1);
						RecvData.addData2(d2);
						RecvData.addData3(d3);
						RecvData.addData4(d4);
						
						
						//data bak
						pw_bak.println(RecvData.bakData(d1, d2, d3, d4)+"\n\n");
		
						
						
						log.info("t_cpu:"+new_tcpu);
						log.info("app_cpu:"+new_appcpu);
						log.info("total_memory:"+total_memory);
						log.info("app_memory:"+app_memory);
					}
					
					tempData=rcvData;
					
					//System.out.println("imgData[3]:"+RecvData.getData()[3]);
					
				}
					
						
			}
			
			

			
		}catch(Exception ex){
			
			log.info("generate pic data error:"+ex.getMessage().toString());
			
		}
		
		
	}


}
