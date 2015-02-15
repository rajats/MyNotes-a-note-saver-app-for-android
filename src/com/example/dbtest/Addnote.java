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
	
	Button save;
	EditText note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnote);
		save= (Button) findViewById(R.id.bsave);
		note = (EditText) findViewById(R.id.notes);
		save.setOnClickListener(this);		
	}
	
	//protected void onPause(Bundle savedInstanceState) {
	//	finish();
	//}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.bsave:
			boolean worked=true;
			try {
				String body=note.getText().toString();
				
				Calendar c1 = Calendar.getInstance();
				SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yy h:m:s a");
				String strdate = sdf1.format(c1.getTime());

				Databasehandler entry=new Databasehandler(Addnote.this);
				entry.open();
				entry.createEntry(body, strdate);
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
		
		}
		
	}

}
