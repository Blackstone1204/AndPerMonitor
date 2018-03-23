/**
 * 
 */
package com.monkeyTest.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;

/**
 * @author Administrator
 *
 */
public class MonkeyBatch implements Runnable {

	private String configpath="";

	private static int finishSingle=0;
	
	public MonkeyBatch(String configpath){
		this.configpath=configpath;
	
		
	}
	public static void main(String[] args){
		String path="monkey.conf";
		getCMD(path);
	}

	

	
	/**
	 * 
		* @Description:获得要执行的所有命令
		* @param 
		* @return String[]
		* @author: BlackStone
		* @time:2016年6月23日下午1:57:22
	 */
	
	public static String[]  getCMD(String cmdSave){
		String[] monkeyCmd=new String[20];
		File f=new File(cmdSave);
		if(!f.exists()){
			
			System.out.println("monkeyBatch配置文件不在");
			System.exit(0);
		}
		
		
		try {
			BufferedReader bd=new BufferedReader(new InputStreamReader(new FileInputStream(cmdSave)));
			
			String line="";
			int index = 0;
			while((line=bd.readLine())!=null){
				if(line.trim()!="")
					monkeyCmd[index++]=line;
				
				//System.out.println("cmd_tmp_"+index+":"+line);
			
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return monkeyCmd;
		
		
	}
	
	
	/**
	 * 
		* @Description:拿到在线列表
		* @param 
		* @return String[]
		* @author: BlackStone
		* @time:2016年6月23日上午10:19:17
	 */
	
	public static String[]  getOnLineDevices() {
		String[] uidss=null;

		try {
			Process p=Runtime.getRuntime().exec("adb devices");
			BufferedReader bd=new BufferedReader(new InputStreamReader(p.getInputStream()));
			String rst="";
			String line="";
			while((line=bd.readLine())!=null){
				rst+=line;
				
			}
			
			//rst 处理
			
			rst=rst.replace("List of devices attached", "");
			if(!rst.contains("device")){
				System.out.println("online device 0,please check connect!");
				System.exit(0);
				
			}
			uidss=rst.split("device");
			
			for(int i=0;i<uidss.length;i++){
				uidss[i]=uidss[i].trim();
				
				//System.out.println(uidss[i]+":"+uidss[i].length());
				
				
			}
			
			//System.out.println(rst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uidss;
		
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
			* @time:2016年6月23日上午11:19:55
			* 
			*/
		
		File dir=new File("monkey_logs");
		
		if(!dir.exists()||!dir.isDirectory())dir.mkdir();

	

  
	    String [] cmds=getCMD(configpath);
		String[] uids=getOnLineDevices();
			
	
//	
	   //monkey 测试
		for(String cmd:cmds){
			
			  String temp=cmd;
				
				for(int i=0;i<uids.length;i++){
					if(cmd!=null){
						cmd=temp;
						cmd=cmd.replace("#uid#", uids[i]);
						//System.out.println("uid:"+uids[i]);
						
						System.out.println("cmd"+i+":"+cmd);
					    new Thread(new MonkeyTest(uids[i],cmd)).start();
					    new Thread(new SystemLog(uids[i])).start();
						
					}


					
				}
				//一个脚本有设备没执行玩 则等待

			
			
		}
		
		
		
	
		
		

		

		
		
	}


}



