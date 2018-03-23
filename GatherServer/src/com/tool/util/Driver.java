package com.tool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.tool.GUI.USBGUI;
import com.tool.ajax.usb.Worker1;
import com.tool.ajax.wifi.Listener;

public class Driver {
	private static Logger log=Logger.getLogger(Driver.class);
	/**
	 * 
		* @Description:usb��ʽ
		* @param 
		* @return void
		* @author: BlackStone
		* @time:2016��6��8������4:33:58
	 */
	public static void wifiDriver(int port){
		//int port=6666;
		
		ServerSocket s;
		
		try {
			
			
			s = new ServerSocket(port);
			System.out.println("����˿�"+port);
			new Thread(new Listener(s)).start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] data=RecvData.getData();
		int[] data2=RecvData.getData2();
		int[] data3=RecvData.getData3();
		int[] data4=RecvData.getData4();
		
		
		new CpuListener(data,data2);
		
		new MemoryListener(data3,data4);

		
		
	}
	/**
	 * 
		* @Description:wifi��ʽ
		* @param 
		* @return void
		* @author: BlackStone
		* @time:2016��6��8������4:33:44
	 */
	public static void usbDriver(int port){
		//
		
		 new Thread(new Worker1(port)).start();
		
		//��ʼ��ָ�������
		
		int[] data=RecvData.getData();
		int[] data2=RecvData.getData2();
		int[] data3=RecvData.getData3();
		int[] data4=RecvData.getData4();
		
		
		new CpuListener(data,data2);
		
		new MemoryListener(data3,data4);
		
		
	

		
	}

}
