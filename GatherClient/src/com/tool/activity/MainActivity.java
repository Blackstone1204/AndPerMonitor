package com.tool.activity;

import java.io.IOException;
import java.net.ServerSocket;

import com.example.gatherclient.R;
import com.tool.WifiAjax.WifiAjax;
import com.tool.util.BasicUtil;
import com.tool.util.CpuUtil;
import com.tool.util.MemUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    private Button wifiBtn;
    private Button usbBtn;

    
 
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        wifiBtn=(Button)findViewById(R.id.wifiBtn);
        usbBtn=(Button)findViewById(R.id.usbBtn);
        
   
        
        wifiBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, WifiActivity.class);
				startActivity(intent);
			
			}
        	
        	
        });
        
        
        usbBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, UsbActivity.class);
				startActivity(intent);
			
				
			}
        	
        	
        });
        
        


        
        
    }
    
 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
