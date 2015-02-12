package com.example.dbtest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.dbtest.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
				entry.createentry(body, strdate);
				entry.close();
			}
			catch(Exception e){
				worked=false;
				String error=e.toString();
				Dialog d=new Dialog(this);
				d.setTitle("errror");
				TextView tv=new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			finally{
				if (worked) {
					Dialog d=new Dialog(this);
					d.setTitle("Note saved!!!");
					TextView tv=new TextView(this);
					tv.setText("Success!!");
					d.setContentView(tv);
					d.show();
				}
			}
		
		}
		
	}

}
