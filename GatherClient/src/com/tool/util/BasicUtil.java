package com.tool.util;

import java.lang.reflect.Field;
import java.util.List;

import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.util.Log;

public class BasicUtil {
	
	private  static String packageName;
	
	/** ��ȡ�û�Ӳ����Ϣ[serial/cpu_abi] */

	  public static String getMobileInfo() {
	    //StringBuffer sb = new StringBuffer();
	    JSONObject mbInfo = new JSONObject();
	    
	    //ͨ�������ȡ�û�Ӳ����Ϣ
	    try {

	      Field[] fields = Build.class.getDeclaredFields();
	      for (Field field : fields) {
	        // ��������,��ȡ˽����Ϣ
	        field.setAccessible(true);
	        String name = field.getName();
	        String value = field.get(null).toString();
	        

	        mbInfo.put(name, value);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	    //return sb.toString();
	    return mbInfo.toString();
	  }
	  
	  public  static String getPackageName(){
		  return packageName;
		  
		  
	  }
	  
	  public  static void setPackageName(String packageName){
		  BasicUtil.packageName=packageName;
	  
		  
	  }
	  
	  
	  
	  /**��ȡ���̺�-ͨ������**
	  ***�Ҳ�������-1**/

	  public  static int getPid(Context context){
		  int pid=-1;
		  Log.i("pkgName",BasicUtil.getPackageName());
		  //check os version
		 // Log.i("param",android.os.Build.VERSION.SDK);
		  //Log.i("param",android.os.Build.VERSION.RELEASE);
		  String ver=android.os.Build.VERSION.RELEASE;
		  //if os version>4
		  if(!ver.startsWith("4")){
			  
			 List<RunningAppProcessInfo> infos = ProcessManager.getRunningAppProcessInfo(context);
			 for(RunningAppProcessInfo info:infos){
				// Log.i("runnning info",info.processName+"---"+info.pid);
				 if(info.processName.equals(BasicUtil.getPackageName()))
					 pid=info.pid;
			 }
			 
			  Log.i("pid",String.valueOf(pid));
			 
			 return pid;
			 
			//if version =4  
		  }else{
			  
			  ActivityManager mActivityManager =(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

			  for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()){
				  //Log.i("runnning info",appProcess.processName+":"+appProcess.pid);
				  if(appProcess.processName.equals(BasicUtil.getPackageName())){
					  pid=appProcess.pid;
					  //Log.i("here","4");
					  
					  
					  break;
	
				  }
			  }
			  
			  Log.i("pid",String.valueOf(pid));

		 
			  
		  return pid;
			  
		  }
		
		  
	  
	
		  
	  }
	  

		  

}
