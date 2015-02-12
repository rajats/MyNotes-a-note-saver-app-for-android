package com.example.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Modifynote extends Activity implements OnClickListener{
	
	Button save;
	EditText note;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatenote);
		save= (Button) findViewById(R.id.bsave);
		note = (EditText) findViewById(R.id.notes);
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String gotnote = extras.getString("SELECTEDNOTE");
		note.setText(gotnote);
		save.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.bsave:

			break;
		}
	}

}
