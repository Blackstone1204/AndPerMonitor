/**
 * 
 */
package com.tool.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tool.util.Driver;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Administrator
 *
 */
public class WifiGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public WifiGUI() {
		setTitle("wifi\u914D\u7F6E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\u76D1\u542C\u7AEF\u53E3\u8BBE\u7F6E");
		lblNewLabel.setForeground(Color.BLUE);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setText("6666");
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u5F00\u59CB\u76D1\u542C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port=Integer.parseInt(textField.getText().toString().trim());
				Driver.wifiDriver(port);
			}
		});
		contentPane.add(btnNewButton);
	}

}
