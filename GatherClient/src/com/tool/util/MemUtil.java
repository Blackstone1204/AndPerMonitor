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
	   * ��ȡ�豸�ڴ��Сֵ
	   * @return �ڴ��С,��λMB
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
	  
	  /**��ȡ�������н�����Ϣ==>֪��memtoal������������ļ���ָ��:
	   * 1.СӰռ����+�ڴ�ֵ ��ٷֱ�
	   * 2.appռ���ڴ�ֵ��ٷֱ�
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
					        // �û�ID
					        int uid =infos.get(i).uid;
					        // ������
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
			  
			    //���ϵͳ���������е����н���
			    List<RunningAppProcessInfo> runningAppProcessesList = mActivityManager.getRunningAppProcesses();
			 
			    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcessesList) {
			        // ����ID��
			        int pid = runningAppProcessInfo.pid;
			        // �û�ID
			        int uid = runningAppProcessInfo.uid;
			        // ������
			        String processName = runningAppProcessInfo.processName;
			        // ռ�õ��ڴ�
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
	  
		// ���ϵͳ�������еĽ�����Ϣ
		private void getRunningServiceInfo(Context context) {
			ActivityManager  mActivityManager=null;

			// ����һ��Ĭ��Service��������С
			int defaultNum = 20;
			// ͨ������ActivityManager��getRunningAppServicees()�������ϵͳ�������������еĽ���
			List<ActivityManager.RunningServiceInfo> runServiceList = mActivityManager
					.getRunningServices(defaultNum);

			System.out.println(runServiceList.size());

			// ServiceInfo Model�� �����������н�����Ϣ
			//serviceInfoList = new ArrayList<RunSericeModel>();

			for (ActivityManager.RunningServiceInfo runServiceInfo : runServiceList) {

				// ���Service���ڵĽ��̵���Ϣ
				int pid = runServiceInfo.pid; // service���ڵĽ���ID��
				int uid = runServiceInfo.uid; // �û�ID ������Linux��Ȩ�޲�ͬ��IDҲ�Ͳ�ͬ ���� root��
				// ��������Ĭ���ǰ�������������android��processָ��
				String processName = runServiceInfo.process; 

				// ��Service����ʱ��ʱ��ֵ
				long activeSince = runServiceInfo.activeSince;

				// �����Service��ͨ��Bind������ʽ���ӣ���clientCount������service���ӿͻ��˵���Ŀ
				int clientCount = runServiceInfo.clientCount;

				// ��ø�Service�������Ϣ ������pkgname/servicename
				ComponentName serviceCMP = runServiceInfo.service;
				String serviceName = serviceCMP.getShortClassName(); // service ������
				String pkgName = serviceCMP.getPackageName(); // ����
				//runServiceInfo.
				// ��ӡLog
				Log.i("process info", "���ڽ���id :" + pid + " ���ڽ�������" + processName + " ���ڽ���uid:"
						+ uid + "\n" + " service������ʱ��ֵ��" + activeSince
						+ " �ͻ��˰���Ŀ:" + clientCount + "\n" + "��service�������Ϣ:"
						+ serviceName + " and " + pkgName);

				// �������ͨ��service�������Ϣ������PackageManager��ȡ��service����Ӧ�ó���İ��� ��ͼ���
				PackageManager mPackageManager = context.getPackageManager(); // ��ȡPackagerManager����;

				try {
					// ��ȡ��pkgName����Ϣ
					ApplicationInfo appInfo = mPackageManager.getApplicationInfo(
							pkgName, 0);

					//RunSericeModel runService = new RunSericeModel();
					//runService.setAppIcon(appInfo.loadIcon(mPackageManager));
					//runService.setAppLabel(appInfo.loadLabel(mPackageManager) + "");
					//runService.setServiceName(serviceName);
					//runService.setPkgName(pkgName);
					// ���ø�service�������Ϣ
					//Intent intent = new Intent();
					//intent.setComponent(serviceCMP);
					//runService.setIntent(intent);
///
					///runService.setPid(pid);
					///runService.setProcessName(processName);

					// �����������
					//serviceInfoList.add(runService);

				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("--------------------- error -------------");
					e.printStackTrace();
				}

			}
		}


}
