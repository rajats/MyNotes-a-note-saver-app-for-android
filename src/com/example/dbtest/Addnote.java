package com.example.dbtest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addnote extends Activity implements OnClickListener{
	
	Button save,savehidden;
	EditText note;
	boolean hide=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		if(b != null)
		    hide = b.getBoolean("hide");
		setContentView(R.layout.addnote);
		save= (Button) findViewById(R.id.bsave);
		savehidden= (Button) findViewById(R.id.bsavehide);
		note = (EditText) findViewById(R.id.notes);
		save.setOnClickListener(this);	
		savehidden.setOnClickListener(this);	
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.bsave:
			boolean worked=true;
			try {
				String body=note.getText().toString();
				int hide=0;
				Calendar c1 = Calendar.getInstance();
				SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yy h:m:s a");
				String strdate = sdf1.format(c1.getTime());
				Databasehandler entry=new Databasehandler(Addnote.this);
				entry.open();
				entry.createEntry(body, strdate, hide);
				entry.close();
			}
			catch(Exception e){
				Context context = getApplicationContext();
				CharSequence text = "Error!";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			finally{
				if (worked) {
					Context context = getApplicationContext();
					CharSequence text = "Note Saved!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					Intent i1 = new Intent(this, Viewnote.class);
					startActivity(i1);
					finish();
				}
			}
		break;
		case R.id.bsavehide:
			worked=true;
			try {
				String body=note.getText().toString();
				int hide=1;
				Calendar c1 = Calendar.getInstance();
				SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yy h:m:s a");
				String strdate = sdf1.format(c1.getTime());
				Databasehandler entry=new Databasehandler(Addnote.this);
				entry.open();
				entry.createEntry(body, strdate, hide);
				entry.close();
			}
			catch(Exception e){
				Context context = getApplicationContext();
				CharSequence text = "Error!";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			finally{
				if (worked) {
					
					Context context = getApplicationContext();
					CharSequence text = "Note Saved!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					Intent i1 = new Intent(this, Viewnote.class);
					Bundle b = new Bundle();
    				b.putBoolean("hide", hide); 
    				i1.putExtras(b); 
					startActivity(i1);
					finish();
				}
			}		
		}
	}
}
