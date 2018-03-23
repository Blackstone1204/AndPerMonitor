package com.tool.activity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.gatherclient.R;
import com.example.gatherclient.R.layout;
import com.example.gatherclient.R.menu;
import com.tool.USBAjax.Listener;
import com.tool.util.BasicUtil;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UsbActivity extends Activity {
    private Button     listenBtn;
    private Button     mapBtn;
    private EditText   portText;
    private EditText   packageName;
    private ServerSocket s;
    private Socket cs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usb);
		
		listenBtn=(Button) findViewById(R.id.listenBtn);
		portText=(EditText) findViewById(R.id.portListen);
		packageName=(EditText) findViewById(R.id.pkgNameTxt);

		//¿ªÊ¼¼àÌý
		listenBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("clickevent","click");
				
				BasicUtil.setPackageName(packageName.getText().toString().trim());
				try {
					
					int port=Integer.parseInt(portText.getText().toString().trim());
					s=new ServerSocket(port);
					new Thread(new Listener(arg0.getContext(),s)).start();
					
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usb, menu);
		return true;
	}


}
