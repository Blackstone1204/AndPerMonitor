/**
 * 
 */
package com.tool.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.tool.ajax.usb.Worker1;
import com.tool.util.Driver;
import com.tool.util.RecvData;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 *
 */
public class USBGUI extends JFrame {

	private static Logger log=Logger.getLogger(USBGUI.class);
	

	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	



	/**
	 * Create the frame.
	 */
	public USBGUI() {
		setTitle("USB\u65B9\u5F0F\u914D\u7F6E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\u6620\u5C04\u7AEF\u53E3\u8BBE\u7F6E");
		lblNewLabel.setForeground(Color.BLUE);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PC");
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText("31000");
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u8BBE\u5907");
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setText("8888");
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u5F00\u542F\u6620\u5C04");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ʼӳ��
				try{
					int port1=Integer.parseInt(textField.getText().toString().trim());
					int port2=Integer.parseInt(textField_1.getText().toString().trim());
				    Runtime.getRuntime().exec("adb forward tcp:"+String.valueOf(port1)+" tcp:"+String .valueOf(port2));
					log.info("map success");
					
				}catch(Exception ex){
					log.error("map error!");
					return;
				}
				
				

				
				
			}
		});
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("\u5F00\u542F\u8FDE\u63A5");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�������
				int port1=Integer.parseInt(textField.getText().toString().trim());
				Driver.usbDriver(port1);
			
	
			}
		});
		contentPane.add(btnNewButton);
	}

}
