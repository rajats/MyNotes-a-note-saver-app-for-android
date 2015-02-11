package com.example.dbtest;



import java.util.ArrayList;

import com.example.dbtest.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Viewnote extends ListActivity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.viewnote);
		ArrayList<String> notelist;
		Databasehandler info=new Databasehandler(Viewnote.this);
		info.open();
		notelist = info.getData();
		//String data="dsgfjs";
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(Viewnote.this, android.R.layout.simple_list_item_1,notelist)); 
		setContentView(R.layout.viewnote);
		info.close();
		
	}
	


}
