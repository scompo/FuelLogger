package org.scompo.android.fuellogger.DB;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class for the sqlite db.
 * 
 * @author scompo
 * @version 1.0
 */
public class DBHelper extends SQLiteOpenHelper {
	
	// DB stuff.
	private static final String DB_NAME = "FuelLogger.db";
	private static final int DB_VERSION = 1;
	
	// Fillup table names.
	public static final String TABLE_NAME_FILLUPS ="fillups";	
	
	private static final String DB_CREATE_STRING =
			"create table " + TABLE_NAME_FILLUPS +" ( "+
			Fillup.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			Fillup.COLUMN_NAME_PRICE +" REAL, " +
			Fillup.COLUMN_NAME_QT + " REAL, " +
			Fillup.COLUMN_NAME_DATE +" TEXT, " +
			Fillup.COLUMN_NAME_DISTANCE + "INTEGER );";

	/**
	 * Constructor.
	 * 
	 * @param context the actual context.
	 */
	public DBHelper(Context context){
		super(context,DB_NAME,null,DB_VERSION);
	}
	
	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DB_CREATE_STRING);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
	
	//CRUD operations.
	
	
	
	/**
	 * Add a fillup to the DB.
	 * 
	 * @param fillup the fillup to insert.
	 * 
	 * @return the DB returned value, -1 if error.
	 */
	public long addFillup(Fillup fillup){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues initialValues = createContentValues(fillup);
		return db.insertOrThrow(TABLE_NAME_FILLUPS,null,initialValues);
	}
	
	/**
	 * Create a new ContentValues container.
	 * 
	 * @param fillup the fillup to use.
	 * 
	 * @return the new created ContentValues.
	 */
	private ContentValues createContentValues(Fillup fillup) {
		ContentValues cv = new ContentValues();
		cv.put(Fillup.COLUMN_NAME_DATE, fillup.getDate());
		cv.put(Fillup.COLUMN_NAME_ID, fillup.getId());
		cv.put(Fillup.COLUMN_NAME_PRICE, fillup.getPrice());
		cv.put(Fillup.COLUMN_NAME_QT, fillup.getQt());
		cv.put(Fillup.COLUMN_NAME_DISTANCE, fillup.getDistance());
		cv.put(Fillup.COLUMN_NAME_PARTIAL, fillup.isPartial());
		return cv;
	}

	/**
	 * Update a fillup.
	 * 
	 * @param oldValues The id of the fillup to change should be set in id.
	 * @param newValues The new values to change.
	 * 
	 * @return true if ok. false if not.
	 */
	public boolean updateFillup(Fillup oldValues, Fillup newValues){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv=createContentValues(newValues);
		return db.update(TABLE_NAME_FILLUPS, cv, 
								Fillup.COLUMN_NAME_ID+"= ?", 
								new String[]{String.valueOf(oldValues.getId())}) > 0;
	}
	
	/**
	 * Delete a fillup.
	 * 
	 * @param target the fillup to be deleted, id should be set.
	 * 
	 * @return true if deleted, false elsewhere.
	 */
	public boolean deleteFillup(Fillup target){
		SQLiteDatabase db = getWritableDatabase();
		return db.delete(TABLE_NAME_FILLUPS, Fillup.COLUMN_NAME_ID + " = ?", 
								new String[]{String.valueOf(target.getId())}) > 0;
	}
	
	/**
	 * Get a fillup by id.
	 * 
	 * @param id the id.
	 * 
	 * @return the selected fillup with the id passed.
	 */
	public Fillup getFillup(int id){
		//TODO: need to do this too.
		return null;
	}
	
	/**
	 * Get all the fillups.
	 * 
	 * @return a List with all the fillups.
	 */
	public List<Fillup> getAllFillups(){
		//TODO: need to do this too.
		return null;
	}
	
}
