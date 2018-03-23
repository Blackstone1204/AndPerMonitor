package com.tool.activity;

import com.example.gatherclient.R;
import com.example.gatherclient.R.layout;
import com.example.gatherclient.R.menu;
import com.tool.WifiAjax.WifiAjax;
import com.tool.util.BasicUtil;
import com.tool.util.MemUtil;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class WifiActivity extends Activity {
	
    private Button     startBtn;
    private EditText   addressText;
    private EditText   packageName=null;
    
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        
        startBtn=(Button)findViewById(R.id.startBtn);
        addressText=(EditText)findViewById(R.id.addressText);
        packageName=(EditText)findViewById(R.id.packageName);
    
        
        
        startBtn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Log.i("event","click");
		    BasicUtil.setPackageName(packageName.getText().toString().trim());
		    new Thread(new WifiAjax(arg0.getContext(),addressText.getText().toString().trim())).start();
		}
    	   
    	   
       });
       
        
        

       
        
        //Log.i("cpuinfo£º",CpuUtil.getCpuFeature());
       // Log.i("meminfo",MemUtil.getTotalMemory()+"");
        //MemUtil.getRunningProcessInfo(this);
        //Log.i("cpuTotalAndIdel:",CpuUtil.getTotalCpuTimeAndIdel());
        //Log.i("appTime",CpuUtil.getAppCpuTime(this)+"");
       // Log.i("cpuTotalAndIdel:",CpuUtil.getTotalCpuTimeAndIdel());
        
        
        
    }
    
 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
