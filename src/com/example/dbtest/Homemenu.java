package com.example.dbtest;

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

public class Homemenu extends Activity implements OnClickListener{
	
	Button view,add;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		add= (Button) findViewById(R.id.badd);
		view= (Button) findViewById(R.id.bview);

		view.setOnClickListener(this);
		add.setOnClickListener(this);
		
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.badd:
			Intent i1 = new Intent(this, Addnote.class);
			startActivity(i1);
			break;
		case R.id.bview:
			Intent i2 = new Intent(this, Viewnote.class);
			startActivity(i2);
			
			break;
		}
		
	}

}
