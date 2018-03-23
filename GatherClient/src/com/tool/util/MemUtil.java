package com.tool.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tool.others.AndroidAppProcess;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Debug;
import android.util.Log;

public class MemUtil {
	private static int flag=1;
	private static int mem_pid=-1;
	 /**
	   * 获取设备内存大小值
	   * @return 内存大小,单位MB
	   */
	  public static long getTotalMemory() { 
	      String str1 = "/proc/meminfo";
	      String str2;        
	      String[] arrayOfString;
	      long initial_memory = 0;
	      try {
	        FileReader localFileReader = new FileReader(str1);
	        BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
	        str2 = localBufferedReader.readLine();
	        if (str2 != null) {
	        	arrayOfString = str2.split("\\s+");
	        	initial_memory = Integer.valueOf(arrayOfString[1]).intValue()/1024;
	        }
	        localBufferedReader.close();
	        return initial_memory;
	      } 
	      catch (IOException e) 
	      {       
	          return -1;
	      }
	  }
	  
	  /**获取正在运行进程信息==>知道memtoal后可以衍生出的几个指标:
	   * 1.小影占用总+内存值 或百分比
	   * 2.app占用内存值或百分比
	   * 
	   * **/
	  
	  public static String getRunningProcessInfo(Context context){
		  
		  long usedMemory=0;
		  long appMemory=0;
		  
		  String ver=android.os.Build.VERSION.RELEASE;
		  //if os version>4
		  if(!ver.startsWith("4")){
			  ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			  
			  if(flag==1){
				  List<AndroidAppProcess> infos = ProcessManager.getRunningAppProcesses();
					 for(int i=0;i<infos.size();i++){
					        int pid =infos.get(i).pid;
					        // 用户ID
					        int uid =infos.get(i).uid;
					        // 进程名
					        String processName = infos.get(i).name;
					        //
					        mem_pid=pid;
					        int[] pids = new int[] {pid};
					        Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(pids);
					        int memorySize = memoryInfo[0].dalvikPss;
					        usedMemory+=memorySize;
					        Log.i("runnning info","processName="+processName+",pid="+pid+",uid="+uid+",memorySize="+memorySize+"kb");
					        
					        if(BasicUtil.getPid(context)==pid){
					        	appMemory=memorySize;
					        	Log.i("param",i+"");
					        	break;
					        }
						
					 }
					 flag++;			  
					  return 0+","+appMemory;
				 
				  
				  
			  }else{
			        int[] pids = new int[] {mem_pid};
			        Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(pids);
			        int memorySize = memoryInfo[0].dalvikPss;
			        return 0+","+memorySize;
			  }

				
			  
		  }else{
			  
			  ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			  
			    //获得系统里正在运行的所有进程
			    List<RunningAppProcessInfo> runningAppProcessesList = mActivityManager.getRunningAppProcesses();
			 
			    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcessesList) {
			        // 进程ID号
			        int pid = runningAppProcessInfo.pid;
			        // 用户ID
			        int uid = runningAppProcessInfo.uid;
			        // 进程名
			        String processName = runningAppProcessInfo.processName;
			        // 占用的内存
			        int[] pids = new int[] {pid};
			        Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(pids);
			        int memorySize = memoryInfo[0].dalvikPss;
			        usedMemory+=memorySize;
			        Log.i("runnning info","processName="+processName+",pid="+pid+",uid="+uid+",memorySize="+memorySize+"kb");
			        
			        if(BasicUtil.getPid(context)==pid)appMemory=memorySize;
			    }
			       
			  
		  
			    Log.i("usedMemory",usedMemory+"");
			    return usedMemory+","+appMemory;
			  
		  }
		  
		
		    
	  }
	  
		// 获得系统正在运行的进程信息
		private void getRunningServiceInfo(Context context) {
			ActivityManager  mActivityManager=null;

			// 设置一个默认Service的数量大小
			int defaultNum = 20;
			// 通过调用ActivityManager的getRunningAppServicees()方法获得系统里所有正在运行的进程
			List<ActivityManager.RunningServiceInfo> runServiceList = mActivityManager
					.getRunningServices(defaultNum);

			System.out.println(runServiceList.size());

			// ServiceInfo Model类 用来保存所有进程信息
			//serviceInfoList = new ArrayList<RunSericeModel>();

			for (ActivityManager.RunningServiceInfo runServiceInfo : runServiceList) {

				// 获得Service所在的进程的信息
				int pid = runServiceInfo.pid; // service所在的进程ID号
				int uid = runServiceInfo.uid; // 用户ID 类似于Linux的权限不同，ID也就不同 比如 root等
				// 进程名，默认是包名或者由属性android：process指定
				String processName = runServiceInfo.process; 

				// 该Service启动时的时间值
				long activeSince = runServiceInfo.activeSince;

				// 如果该Service是通过Bind方法方式连接，则clientCount代表了service连接客户端的数目
				int clientCount = runServiceInfo.clientCount;

				// 获得该Service的组件信息 可能是pkgname/servicename
				ComponentName serviceCMP = runServiceInfo.service;
				String serviceName = serviceCMP.getShortClassName(); // service 的类名
				String pkgName = serviceCMP.getPackageName(); // 包名
				//runServiceInfo.
				// 打印Log
				Log.i("process info", "所在进程id :" + pid + " 所在进程名：" + processName + " 所在进程uid:"
						+ uid + "\n" + " service启动的时间值：" + activeSince
						+ " 客户端绑定数目:" + clientCount + "\n" + "该service的组件信息:"
						+ serviceName + " and " + pkgName);

				// 这儿我们通过service的组件信息，利用PackageManager获取该service所在应用程序的包名 ，图标等
				PackageManager mPackageManager = context.getPackageManager(); // 获取PackagerManager对象;

				try {
					// 获取该pkgName的信息
					ApplicationInfo appInfo = mPackageManager.getApplicationInfo(
							pkgName, 0);

					//RunSericeModel runService = new RunSericeModel();
					//runService.setAppIcon(appInfo.loadIcon(mPackageManager));
					//runService.setAppLabel(appInfo.loadLabel(mPackageManager) + "");
					//runService.setServiceName(serviceName);
					//runService.setPkgName(pkgName);
					// 设置该service的组件信息
					//Intent intent = new Intent();
					//intent.setComponent(serviceCMP);
					//runService.setIntent(intent);
///
					///runService.setPid(pid);
					///runService.setProcessName(processName);

					// 添加至集合中
					//serviceInfoList.add(runService);

				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("--------------------- error -------------");
					e.printStackTrace();
				}

			}
		}


}
