/**
 * 
 */
package com.tool.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.monkeyTest.util.MonkeyBatch;
import com.monkeyTest.util.MonkeyTest;
import com.monkeyTest.util.TableData;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

/**
 * @author Administrator
 *
 */
public class MonkeyGUI extends JFrame implements Runnable {

	private static JPanel contentPane;
	private JTextField txtAdbsuid;
	private JLabel label;
	private JButton btnEdit;
	private static JTable table;
	private static JScrollPane scrollPane;
	
	static String[] names={"Index","time","deviceID","command","result"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public MonkeyGUI() {
		setTitle("MonkeyBatch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label = new JLabel("配置文件");
		contentPane.add(label);
		
		txtAdbsuid = new JTextField();
		txtAdbsuid.setText("monkey.txt");
		contentPane.add(txtAdbsuid);
		txtAdbsuid.setColumns(15);
		
		JButton btnNewButton = new JButton("开始");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				String configpath=txtAdbsuid.getText();
				 new Thread(new MonkeyBatch(configpath)).start();
				 
				 
	
				 
			
				
			}
		});
		
		btnEdit = new JButton("编辑");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String configpath=txtAdbsuid.getText();
				File f=new File(configpath);
				
					try {
						//
						
						if(!f.exists())
							f.createNewFile();
						
						System.out.println("absolutePaht:"+f.getAbsolutePath());
						Runtime.getRuntime().exec("cmd.exe /c "+f.getAbsolutePath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
			}
		});
		contentPane.add(btnEdit);
		contentPane.add(btnNewButton);
		
	
	
		
		new Thread(this).start();
		

	}

	
	
public static void refreshTable(){
	ArrayList<TableData> list=MonkeyTest.getAlist();
	int width=4;
	int length=list.size();
	
	System.out.println("len:"+length);
	
	Object[][] playInfo=new Object[length][5];
	for(int j=0;j<length;j++){
		
		TableData data=list.get(j);
		
			playInfo[j][0]=data.getIndex();
			playInfo[j][1]=data.getTimestr();
			playInfo[j][2]=data.getUid();
			playInfo[j][3]=data.getCmdIndex();
			playInfo[j][4]=data.getResult();
							
			
			//System.out.println("getdata:"+playInfo[j][1]);
		
		
	}
    if(scrollPane!=null)contentPane.remove(scrollPane);
	table=new JTable(playInfo,names);
	table.setPreferredScrollableViewportSize(new Dimension(400, 150));
	
	scrollPane = new JScrollPane(table);
	contentPane.add(scrollPane);
    contentPane.updateUI();

	
}

/* (non-Javadoc)
 * @see java.lang.Runnable#run()
 */
@Override
public void run() {
	// TODO Auto-generated method stub
		/**
		* @Description:TODO
		* @param 
		* @author: BlackStone
		* @time:2016年6月23日下午5:17:03
		*
		*/
	
	while(true){
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshTable();
	}

}
	
	
	

}
