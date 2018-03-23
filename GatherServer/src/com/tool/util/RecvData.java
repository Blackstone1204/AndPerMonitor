package com.tool.util;

public class RecvData {
	
	private static  int[] data=new int[25];//��cpuʹ�������ͼdata
	private static  int[] data2=new int[25];//app cpu��ͼdata
	
	private static  int[] data3=new int[25];//������������ڴ�
	private static  int[] data4=new int[25];//ĳ��app������ڴ�
	
	public  String  tempData=null;
	//******** bak data************
	
	public static void main(String[] args){
		bakData(14,88,789,10);
		
	}
	public static String bakData(int d1,int d2,int d3,int d4){
		String dv1=String.valueOf(d1);
		String dv2=String.valueOf(d2);
		String dv3=String.valueOf(d3);
		String  dv4=String.valueOf(d4);
		
		String rst=dv1+genarate(" ",6-dv1.length())+dv2+genarate(" ",6-dv2.length())+dv3+genarate(" ",6-dv3.length())+dv4+genarate(" ",6-dv4.length());
		
		return rst;
		
		
		
	}
	private static String genarate(String s,int count){
		String rst="";
		for(int i=0;i<count;i++){
			rst+=s;
		}
		return rst;
		
	}
	//*********data�Ĳ���*************/
	
	public static void addData(int newer){
		
		for(int i=data.length-1;i>=1;i--){
			data[i]=data[i-1];
			
			
		}
		
		
		data[0]=newer;
		
		
	}
	
	
	
	public static int[] getData(){
		
		return data;
	}
	
	//***********data2�Ĳ���**************/
public static void addData2(int newer){
		
		for(int i=data2.length-1;i>=1;i--){
			data2[i]=data2[i-1];
			
			
		}
		
		
		data2[0]=newer;
		
		
	}
	
	
	
	public static int[] getData2(){
		
		return data2;
	}
	
	
	

	//**data3
	
	public static void addData3(int newer){
		
		for(int i=data3.length-1;i>=1;i--){
			data3[i]=data3[i-1];
			
			
		}
		
		
		data3[0]=newer;
		
		
	}
	
	
	
	public static int[] getData3(){
		
		return data3;
	}
	//***data4****
	
	
	public static void addData4(int newer){
		
		for(int i=data4.length-1;i>=1;i--){
			data4[i]=data4[i-1];
			
			
		}
		
		
		data4[0]=newer;
		
		
	}
	
	
	
	public static int[] getData4(){
		
		return data4;
	}

}
