package com.tool.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;



public class CpuUtil {
	
	  /**
	   * 获取android CPU特性
	   * 
	   * @return String CPU特性
	   */
	  public static String getCpuFeature(){
	    	String cpu_feature = "";
	        	
	    CPUInfo in = getCPUInfo();
	        	
	    if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_NEON ) == CPUInfo.CPU_FEATURE_NEON)
	      cpu_feature="neon";
	    else if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_VFP ) == CPUInfo.CPU_FEATURE_VFP)
	      cpu_feature="vfp";
	    else if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_VFPV3 ) == CPUInfo.CPU_FEATURE_VFPV3)
	      cpu_feature="vfpv3";
	    else
	      cpu_feature="unknown"; 
	    return cpu_feature;
	  }
	  
	  
         /**** 获取系统CPU百分比基础数据  *****/
	  
	  public  static String getCpuInf(Context context) { 
		  //  
		  String[] cpuInfos = null;    
		  try {        
			  BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream("/proc/stat")), 1000);  
			 
			  String load = reader.readLine();
			  reader.close();        
			  cpuInfos = load.split(" ");    
			  } 
		  catch (IOException ex) 
			  { 
			  ex.printStackTrace();    
			  }    
		  long totalCpu = Long.parseLong(cpuInfos[2]) + Long.parseLong(cpuInfos[3]) + Long.parseLong(cpuInfos[4]) + Long.parseLong(cpuInfos[6]) + Long.parseLong(cpuInfos[5]) + Long.parseLong(cpuInfos[7]) + Long.parseLong(cpuInfos[8]);
		  
		  return totalCpu+","+Long.parseLong(cpuInfos[5])+","+getAppCpuTime(context);
		  
		  
		  }
	  
	  /**获取应用占用的CPU时间   **/
	  public  static long getAppCpuTime(Context context) { 
		  String[] cpuInfos = null;    
		  try {        
			  int pid = BasicUtil.getPid(context); 
			  if(-1==pid){
				  
			
				  Log.i("error","pid error");
			  }
			  BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream("/proc/" + pid + "/stat")), 1000);       
			  String load = reader.readLine();        
			  reader.close();       
			  cpuInfos = load.split(" ");    
			  } 
		  catch (IOException ex) 
			  { 
				 ex.printStackTrace();    
				 }    
		       long appCpuTime = Long.parseLong(cpuInfos[13])+ Long.parseLong(cpuInfos[14]) + Long.parseLong(cpuInfos[15]) + Long.parseLong(cpuInfos[16]);    
		       return appCpuTime;
		       
		       
		       
	  }
		       
		       
	
	  
	  
	  
	
	  
	  
	  /** 获取cpu信息**/
	  public static CPUInfo getCPUInfo() {
		    String strInfo = null;
		    try
		    {
		      byte[] bs = new byte[1024];
		      RandomAccessFile reader = new RandomAccessFile("/proc/cpuinfo", "r");
		      reader.read(bs);
		      String ret = new String(bs);
		      int index = ret.indexOf(0);
		      if(index != -1) {
		        strInfo = ret.substring(0, index);
		      } else {
		        strInfo = ret;
		      }
		    }
		    catch (IOException ex)
		    {
		      strInfo = "";
		      ex.printStackTrace();
		    }
		    
		    CPUInfo info = parseCPUInfo(strInfo);
		    info.mCPUMaxFreq = getMaxCpuFreq();
		        
		    return info;
		  }
	  
	  
	  
	  
	  private final static String kCpuInfoMaxFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
	  private static int getMaxCpuFreq() {
	    int result = 0;
	    FileReader fr = null;
	    BufferedReader br = null;
	    try {
	      fr = new FileReader(kCpuInfoMaxFreqFilePath);
	      br = new BufferedReader(fr);
	      String text = br.readLine();
	      if (text != null) {
	        result = Integer.parseInt(text.trim());
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (fr != null)
	        try {
	          fr.close();
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      if (br != null)
	        try {
	          br.close();
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	    }

	    return result;
	  }
	  
	  
	  public static class CPUInfo{
		    public CPUInfo(){
		      
		    }
		    
		    public static final int CPU_TYPE_UNKNOWN			=   0x00000000;
		    public static final int CPU_TYPE_ARMV5TE 			= 	0x00000001;
		    public static final int CPU_TYPE_ARMV6		 		= 	0x00000010;
		    public static final int CPU_TYPE_ARMV7				= 	0x00000100;
		    
		    public static final int CPU_FEATURE_UNKNOWS			=	0x00000000;
		    public static final int CPU_FEATURE_VFP				= 	0x00000001;
		    public static final int CPU_FEATURE_VFPV3			= 	0x00000010;
		    public static final int CPU_FEATURE_NEON			=	0x00000100;
		    
		    public int mCPUType;
		    public int mCPUCount;
		    public int mCPUFeature;		
		    public double mBogoMips;
		    public long mCPUMaxFreq;
		  }
		  
		  /**
		   * 
		   * @param cpuInfo
		   * @return
		   * @hide
		   */
		  private static CPUInfo parseCPUInfo(String cpuInfo) {
		    if (cpuInfo == null || "".equals(cpuInfo)) {
		      return null;
		    }

		    CPUInfo ci = new CPUInfo();
		    ci.mCPUType = CPUInfo.CPU_TYPE_UNKNOWN;
		    ci.mCPUFeature = CPUInfo.CPU_FEATURE_UNKNOWS;
		    ci.mCPUCount = 1;
		    ci.mBogoMips = 0;

		    if (cpuInfo.contains("ARMv5")) {
		      ci.mCPUType = CPUInfo.CPU_TYPE_ARMV5TE;
		    } else if (cpuInfo.contains("ARMv6")) {
		      ci.mCPUType = CPUInfo.CPU_TYPE_ARMV6;
		    } else if (cpuInfo.contains("ARMv7")) {
		      ci.mCPUType = CPUInfo.CPU_TYPE_ARMV7;
		    }

		    if (cpuInfo.contains("neon")) {
		      ci.mCPUFeature |= CPUInfo.CPU_FEATURE_NEON;
		    }

		    if (cpuInfo.contains("vfpv3")) {
		      ci.mCPUFeature |= CPUInfo.CPU_FEATURE_VFPV3;
		    }

		    if (cpuInfo.contains(" vfp")) {
		      ci.mCPUFeature |= CPUInfo.CPU_FEATURE_VFP;
		    }

		    String[] items = cpuInfo.split("\n");

		    for (String item : items) {
		      if (item.contains("CPU variant")) {
		        int index = item.indexOf(": ");
		        if (index >= 0) {
		          String value = item.substring(index + 2);
		          try {
		            ci.mCPUCount = Integer.decode(value);
		            ci.mCPUCount = ci.mCPUCount == 0 ? 1 : ci.mCPUCount;
		          } catch (NumberFormatException e) {
		            ci.mCPUCount = 1;
		          }
		        }
		      } else if (item.contains("BogoMIPS")) {
		        int index = item.indexOf(": ");
		        if (index >= 0) {
		          String value = item.substring(index + 2);
		        }
		      }
		    }
		    
		    return ci;
		  }

	  
	  
	  

	
	

}
