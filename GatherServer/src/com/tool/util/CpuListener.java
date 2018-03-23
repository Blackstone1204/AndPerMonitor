package com.tool.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.Log;

import com.tool.ajax.wifi.Worker;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ChartPanel;
import javax.swing.*;

public class CpuListener extends JFrame implements Runnable{
 //constructor
	String title="cpu监控";
	JFreeChart chart;
	ChartPanel panel;
	JPanel jp;
	DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); 
	static CategoryPlot plot;
	
	private String xName="时间趋线";
	private String yName="百分比";

	private static Logger log=Logger.getLogger(CpuListener.class);
 public CpuListener(int[] data,int[] data2){

        chart=ChartFactory.createLineChart(title,xName, yName, dataSet, PlotOrientation.VERTICAL, false, false, false);
        plot = (CategoryPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.black);

        LineAndShapeRenderer r=(LineAndShapeRenderer) plot.getRenderer();
        r.setSeriesPaint(0, Color.BLUE);
        plot.getRangeAxis().setRange(0,100);//设置y轴范围
        
 
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        
        CategoryPlot plot = (CategoryPlot)chart.getPlot();
      //获取 X 轴
       CategoryAxis domainAxis = plot.getDomainAxis();
       ValueAxis numberAxis = plot.getRangeAxis();
       domainAxis.setTickLabelFont(new Font("黑体",Font.BOLD,10));//设置x轴坐标上的字体
       domainAxis.setLabelFont(new Font("黑体",Font.BOLD,15));//设置x轴上的标题的字体
       
       chart.getTitle().setFont(new Font("黑体", Font.BOLD,15));//设置标题
       
       numberAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));    



        
        //用来放置图表
        panel = new ChartPanel(chart);
        jp = new JPanel();
        jp.add(panel, BorderLayout.CENTER);
        this.add(jp);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 700, 500);
        this.setVisible(true);
        
        //chart.getLegend().setVisible(false);
        
        new Thread(this).start();
        

        
 }
 
 private void update(int[]data,int[] data2){
	 
	 
	 
	 dataSet = new DefaultCategoryDataset();
	 int timeSpace=1;//服务器采集间隔
     for(int i=0;i<data.length;i++){
         
    	 dataSet.addValue(data[i], "total",String.valueOf(i+1)+"x"+timeSpace);
    	 dataSet.addValue(data2[i], "app", String.valueOf(i+1)+"x"+timeSpace);
    	 
     }
   
	 plot.setDataset(dataSet);
 }
 
 public static CategoryPlot getPlot(){
	 return plot;
	 
 }

@Override
public void run() {
	// TODO Auto-generated method stub
	
	while(true){
		//log.info("UI_thread");
		int[] data=RecvData.getData();
		int[] data2=RecvData.getData2();
		update(data,data2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
 
 

}

