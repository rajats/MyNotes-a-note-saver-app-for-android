package com.example.dbtest;



import java.util.ArrayList;
import java.util.List;

import com.example.dbtest.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Viewnote extends ListActivity implements OnClickListener {
	
	Button add;
	List<String> notelist;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Databasehandler info=new Databasehandler(Viewnote.this);
		info.open();
		notelist = info.getData();
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(Viewnote.this, android.R.layout.simple_list_item_1,android.R.id.text1 ,notelist)); 
		setContentView(R.layout.viewnote);
		info.close();
		add= (Button) findViewById(R.id.badd);
		add.setOnClickListener(this);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String selected = notelist.get(position);
		
		Intent i3 = new Intent(Viewnote.this,Modifynote.class);
		Bundle extras = new Bundle();
		extras.putString("SELECTEDNOTE",selected);
		i3.putExtras(extras);
		startActivity(i3);

	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.badd:
			Intent i1 = new Intent(this, Addnote.class);
			startActivity(i1);
			break;
		}
		
	}

}
