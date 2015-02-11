package com.example.dbtest;



import com.example.dbtest.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Viewnote extends Activity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewnote);
		
		TextView tv=(TextView) findViewById(R.id.noteview);
		Databasehandler info=new Databasehandler(Viewnote.this);
		info.open();
		String data=info.getData();
		//String data="dsgfjs";
		info.close();
		tv.setText(data);
		
	}


}
