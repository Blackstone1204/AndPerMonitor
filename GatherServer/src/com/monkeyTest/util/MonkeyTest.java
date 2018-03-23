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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class MonkeyTest implements Runnable{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	private static  ArrayList<TableData> alist=new  ArrayList<TableData>();
	private static int data_index=0;
	static String[] tags={"CRASH","ANR","crash","anr","Crash"};
	private String uid;
	private String cmd;
	public MonkeyTest(String uid,String cmd){
		this.uid=uid;
		this.cmd=cmd;
	}
	@Override
	public void run() {
		
		//SystemLog.exit=false;
		
		// TODO Auto-generated method stub
			/**
			* @Description:TODO
			* @param 
			* @author: BlackStone
			* @time:2016年6月27日下午6:08:09
			*/
		File f=new File("monkey_logs\\"+uid+"_monkey.txt");	
		if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String resultTag="";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");//设置日期格式
		Date d=new Date();
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			
			BufferedReader bd=new BufferedReader(new InputStreamReader(p.getInputStream()));	
			
			
			PrintWriter pw=new PrintWriter(new FileOutputStream(f.getAbsolutePath(),true));
			String rst="";
			String line="";
			while((line=bd.readLine())!=null){
				//System.out.println(line);
				for(String tag:tags){
					if(line.contains(tag)){
						resultTag+=tag+" ";
		
					}
						
				}
				rst+=line+"\n";
				
			}
			
			if(resultTag.equals(""))resultTag="pass";
			
		    //SystemLog.exit=true;
			String time=df.format(d);
			String content=time+" "+resultTag+" command["+cmd+"]\n*************************************************\n\n"+rst;
			System.out.println("content:"+content);
			pw.append(content);
			
		
			pw.flush();
			pw.close();
			
			
			//准备表格数据
			TableData td=new TableData();
			data_index++;
			String index=data_index+"";
			td.setTimestr(time);
			td.setIndex(index);
			td.setUid(uid);
			td.setCmdIndex(cmd);
		    td.setResult(resultTag.toString());
		    System.out.println("tableData:"+index+uid+cmd+resultTag);
		    alist.add(td);
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		
	}
	
	/**
	 * @return the alist
	 */
	public static ArrayList<TableData> getAlist() {
		return alist;
	}
	/**
	 * @param alist the alist to set
	 */
	public static void setAlist(ArrayList<TableData> alist) {
		MonkeyTest.alist = alist;
	}
	
	


}
