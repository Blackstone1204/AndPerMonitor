/**
 * 
 */
package com.monkeyTest.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Administrator
 *
 */
public class SystemLog implements Runnable {
	private String uid;
	public static  boolean exit=false;
	
	public SystemLog(String uid){
		this.uid=uid;
		
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
			* @time:2016年6月27日下午4:45:37
			*/
		

		try {
			File f1=new File("monkey_logs\\"+uid+"_system.log");
			String cmd="adb -s #uid#  logcat";

			Process p;

			if(!f1.exists())f1.createNewFile();
			cmd=cmd.replace("#uid#",uid);
			System.out.println("cmd： "+cmd);
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader bd=new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			PrintWriter pw=new PrintWriter(new FileOutputStream(f1.getAbsolutePath(),true));
			

			String rst="";
			String line="";
			while((line=bd.readLine())!=null){
				//System.out.println(line);
		
				//rst+=line+"\n\n";
				pw.append(line+"\n");
				pw.flush();
			
				
			}
			
					
			//String content=rst;
			//System.out.println("systemlog:"+content);
	
		
			pw.close();
			

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
