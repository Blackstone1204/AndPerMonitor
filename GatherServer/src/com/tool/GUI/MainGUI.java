/**
 * 
 */
package com.tool.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.UIManager;

/**
 * @author Administrator
 *
 */
public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("Gather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpumemory = new JLabel("cpu && memory  ");
		lblCpumemory.setForeground(new Color(51, 153, 204));
		lblCpumemory.setFont(new Font("黑体", Font.PLAIN, 12));
		lblCpumemory.setBounds(6, 5, 105, 36);
	
		contentPane.add(lblCpumemory);
		
		JButton btnNewButton_1 = new JButton("USB");
		btnNewButton_1.setBackground(new Color(95, 158, 160));
		btnNewButton_1.setBounds(211, 9, 75, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							USBGUI frame = new USBGUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblMonkeybatch = new JLabel("monkeyBatch");
		lblMonkeybatch.setForeground(new Color(255, 153, 153));
		lblMonkeybatch.setFont(new Font("黑体", Font.PLAIN, 12));
		lblMonkeybatch.setBounds(16, 51, 75, 36);
		contentPane.add(lblMonkeybatch);
		
		JLabel label = new JLabel("");
		label.setBounds(6, 41, 75, 36);
		contentPane.add(label);
		
		JButton btnNewButton_2 = new JButton("start");
		btnNewButton_2.setBackground(new Color(95, 158, 160));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MonkeyGUI frame = new MonkeyGUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_2.setBounds(121, 55, 75, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("wifi");
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WifiGUI frame = new WifiGUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
			}
		});

		btnNewButton.setBounds(121, 9, 75, 29);
		contentPane.add(btnNewButton);
	}
}
