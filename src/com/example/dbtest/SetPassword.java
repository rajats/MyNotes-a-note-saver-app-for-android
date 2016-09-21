package com.example.dbtest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetPassword  extends Activity implements OnClickListener{

	EditText password,enteredpassword;
	Button savepasswd, checkpasswd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SharedPreferences storeHash = getSharedPreferences("STOREHASH", MODE_PRIVATE); 
		final String gethash = storeHash.getString("hash", null);
		if (gethash!=null){
			setContentView(R.layout.askpasswd);
			checkpasswd= (Button) findViewById(R.id.bcheckpasswd);
			enteredpassword = (EditText) findViewById(R.id.enteredpasswd);
			checkpasswd.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                	String inputpasswd=enteredpassword.getText().toString();
                	String hashgenerated="";
                	try {
                		hashgenerated = SHA1(inputpasswd);
	   				} catch (NoSuchAlgorithmException e) {
	   					// TODO Auto-generated catch block
	   					e.printStackTrace();
	   				} catch (UnsupportedEncodingException e) {
	   					// TODO Auto-generated catch block
	   					e.printStackTrace();
	   				}
                	if(hashgenerated.equals(gethash)){
                		Context context = getApplicationContext();
        				CharSequence text = "Password Matched";
        				int duration = Toast.LENGTH_SHORT;
        				Toast toast = Toast.makeText(context, text, duration);
        				toast.show();
        				Intent i1 = new Intent(SetPassword.this, Viewnote.class);
        				Bundle b = new Bundle();
        				b.putBoolean("hide", true); 
        				i1.putExtras(b); 
        				startActivity(i1);
        				finish();
                	}
                	else{
                		Context context = getApplicationContext();
        				CharSequence text = "Incorrect Password!";
        				int duration = Toast.LENGTH_SHORT;
        				Toast toast = Toast.makeText(context, text, duration);
        				toast.show();
        				Intent i1 = new Intent(SetPassword.this, Viewnote.class);
        				startActivity(i1);
        				finish();
                	}
                }
            });
		}	
		else{
			setContentView(R.layout.setpasswd);
			savepasswd= (Button) findViewById(R.id.bsavepasswd);
			password = (EditText) findViewById(R.id.passwdtext);
			savepasswd.setOnClickListener(new View.OnClickListener()
            {
				@Override
                public void onClick(View arg0) {            	
                	SharedPreferences.Editor storeHashEditor = getSharedPreferences("STOREHASH", MODE_PRIVATE).edit();
    				String passwd=password.getText().toString();
    				String hash="";
    				try {
    					 hash = SHA1(passwd);
    				} catch (NoSuchAlgorithmException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (UnsupportedEncodingException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				storeHashEditor.putString("hash", hash);
    				storeHashEditor.commit();
    				Context context = getApplicationContext();
    				CharSequence text = "Password Saved!";
    				int duration = Toast.LENGTH_SHORT;
    				Toast toast = Toast.makeText(context, text, duration);
    				toast.show();
    				Intent i1 = new Intent(SetPassword.this, Viewnote.class);
    				startActivity(i1);
    				finish();
                }
            });
			
		}
	}
	
	private  static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public  static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
