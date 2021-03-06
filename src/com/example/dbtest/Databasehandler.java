package com.example.dbtest;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehandler {
	
	public static final String KEY_ROWID="_id";
	public static final String KEY_BODY="note_body";
	public static final String KEY_DATETIME="note_datetime";
	public static final String KEY_HIDDEN="note_hidden";

	private static final String DATABASE_NAME="notesaverdb";
	private static final String DATABASE_TABLE="notetable";
	private static final int DATABASE_VERSION=1;
	
	private Dbhelper ourhelper;
	private final Context ourcontext;
	private SQLiteDatabase ourdatabase;
	private static class Dbhelper extends SQLiteOpenHelper {

		public Dbhelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+ DATABASE_TABLE+ " ("+
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
			KEY_BODY+" TEXT NOT NULL, "+
			KEY_DATETIME+" TEXT NOT NULL, "+
			KEY_HIDDEN+" INTEGER DEFAULT 0);"
		   );
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	public Databasehandler(Context c){
		ourcontext=c;
	}
	
	public Databasehandler open() throws SQLException{
		ourhelper=new Dbhelper(ourcontext);
		ourdatabase=ourhelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourhelper.close();
	}
	
	public long createEntry(String body, String datetime, int hidden) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(KEY_BODY, body);
		cv.put(KEY_DATETIME, datetime);
		cv.put(KEY_HIDDEN, hidden);
		return ourdatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public ArrayList<String> getData(boolean hide) {
		// TODO Auto-generated method stub
		ArrayList<String> noteslist = new ArrayList<String>();
		String[] columns=new String[]{KEY_ROWID,KEY_BODY,KEY_DATETIME,KEY_HIDDEN};
		Cursor c=ourdatabase.query(DATABASE_TABLE, columns, null, null, null, null, null, null);
		String result="";
		int iBody=c.getColumnIndex(KEY_BODY);
		int iDatetime=c.getColumnIndex(KEY_DATETIME);
		int ihidden=c.getColumnIndex(KEY_HIDDEN);
		if(!hide){
			for(c.moveToFirst();!c.isAfterLast(); c.moveToNext()){
				result=c.getString(iBody)+"\n"+"\n"+c.getString(iDatetime)+"\n";
				if (c.getInt(ihidden)==0)
					noteslist.add(result);
			}
		}
		else{
			for(c.moveToFirst();!c.isAfterLast(); c.moveToNext()){
				result=c.getString(iBody)+"\n"+"\n"+c.getString(iDatetime)+"\n";
				if (c.getInt(ihidden)==1)
					noteslist.add(result);
			}
		}
		return noteslist;
	}
	
	public ArrayList<String> getIndex(boolean hide) {
		// TODO Auto-generated method stub
		ArrayList<String> indexlist = new ArrayList<String>();
		String[] columns=new String[]{KEY_ROWID,KEY_BODY,KEY_DATETIME,KEY_HIDDEN};
		Cursor c=ourdatabase.query(DATABASE_TABLE, columns, null, null, null, null, null, null);
		String result="";
		int iRowid=c.getColumnIndex(KEY_ROWID);
		int ihidden=c.getColumnIndex(KEY_HIDDEN);
		if(!hide){
			for(c.moveToFirst();!c.isAfterLast(); c.moveToNext()){
				result=c.getString(iRowid);
				if (c.getInt(ihidden)==0)
					indexlist.add(result);
			}
		}
		else{
			for(c.moveToFirst();!c.isAfterLast(); c.moveToNext()){
				result=c.getString(iRowid);
				if (c.getInt(ihidden)==1)
					indexlist.add(result);
			}
		}
		return indexlist;
	}
	
	public String getBody(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID,KEY_BODY,KEY_DATETIME,KEY_HIDDEN};
		Cursor c=ourdatabase.query(DATABASE_TABLE, columns, KEY_ROWID+"="+l, null, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String body=c.getString(1);   //1 means second column
			return body;
		}
		return null;
	}
	
	public String getDatetime(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID,KEY_BODY,KEY_DATETIME,KEY_HIDDEN};
		Cursor c=ourdatabase.query(DATABASE_TABLE, columns, KEY_ROWID+"="+l, null, null, null, null, null);
		if(c!=null){
			c.moveToFirst();
			String datetime=c.getString(2);   //2 means third column
			return datetime;
		}
		return null;
	}
	
	public void updateEntry(long lmrow, String mbody, String mdatetime, int mhidden) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvupdate=new ContentValues();
		cvupdate.put(KEY_BODY, mbody);
		cvupdate.put(KEY_DATETIME, mdatetime);
		cvupdate.put(KEY_HIDDEN, mhidden);
		ourdatabase.update(DATABASE_TABLE, cvupdate, KEY_ROWID+"="+lmrow, null);
	}
	
	public void deleteEntry(long ldrow) throws SQLException {
		// TODO Auto-generated method stub
		ourdatabase.delete(DATABASE_TABLE, KEY_ROWID+"="+ldrow, null);
	}	
}

