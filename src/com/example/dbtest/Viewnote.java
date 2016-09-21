package com.example.dbtest;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Viewnote extends ListActivity implements OnClickListener {
	
	Button add,viewhidden;
	List<String> notelist;
	List<String> indexlist;
	boolean hide=false;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Databasehandler info=new Databasehandler(Viewnote.this);
		info.open();
		Bundle b = getIntent().getExtras();
		if(b != null)
		    hide = b.getBoolean("hide");
		notelist = info.getData(hide);
		indexlist = info.getIndex(hide);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(Viewnote.this, android.R.layout.simple_list_item_1,android.R.id.text1 ,notelist)); 
		setContentView(R.layout.viewnote);
		info.close();
		add= (Button) findViewById(R.id.badd);
		viewhidden= (Button) findViewById(R.id.bviewhidden);
		if(hide)
			viewhidden.setText("View Unhidden Notes");
		viewhidden.setOnClickListener(this);
		add.setOnClickListener(this);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		AlertDialog.Builder builder2=new AlertDialog.Builder(Viewnote.this);
		  builder2.setPositiveButton("Edit",new DialogInterface.OnClickListener() {

			  @Override
			  public void onClick(DialogInterface dialog, int which) {
				  String indexed = indexlist.get(position);
				  long lmrow=Long.parseLong(indexed);
				  Databasehandler info=new Databasehandler(Viewnote.this);
				  info.open();
				  final String returnedbody=info.getBody(lmrow);
				  info.close();
				  Intent i1 = new Intent(Viewnote.this,Modifynote.class);
				  Bundle extras = new Bundle();
				  extras.putString("SELECTEDNOTE",returnedbody);
				  extras.putString("SELECTEDINDEX",indexed);
				  i1.putExtras(extras);
				  startActivity(i1); 
				  finish();
			  }
		  });
		  builder2.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
			  
			  @Override
			  public void onClick(DialogInterface dialog, int which) {
				String indexed = indexlist.get(position);
				long ldrow=Long.parseLong(indexed);
				Databasehandler info=new Databasehandler(Viewnote.this);
				info.open();
				info.deleteEntry(ldrow);
				info.close();
				Context context = getApplicationContext();
				CharSequence text = "Note Deleted!";
				int duration = Toast.LENGTH_SHORT;
	
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				Intent i1 = new Intent(Viewnote.this, Viewnote.class);
				startActivity(i1);
				finish();
			  }
		  });
		  builder2.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater blow= getMenuInflater();
		//getMenuInflater().inflate(R.menu.main, menu);
		blow.inflate(R.menu.main, menu );
		return true;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.aboutUs:
			Intent i1=new Intent(this,About.class);
			startActivity(i1);
			break;
		case R.id.exit:
			finish();
			break;
		}
	return false;
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
			case R.id.badd:
				Intent i1 = new Intent(this, Addnote.class);
				startActivity(i1);
				finish();
				break;
			case R.id.bviewhidden:
				if(hide){
					i1 = new Intent(this, Viewnote.class);
					Bundle b = new Bundle();
    				b.putBoolean("hide", false); 
    				i1.putExtras(b); 
				}
				else
					i1 = new Intent(this, SetPassword.class);
				startActivity(i1);
				finish();
				break;
		}
	}
}
