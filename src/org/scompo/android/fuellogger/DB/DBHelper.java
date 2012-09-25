/*  Android fuel logger keeps track of the user fuel consumption.
 *  
 *  Copyright (C) 2012  Mauro Scomparin <http://scompoprojects.wordpress.com>
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scompo.android.fuellogger.DB;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class for the sqlite db.
 * 
 * @author scompo
 * @version 1.0
 */
public class DBHelper extends SQLiteOpenHelper {
	
	/**
	 * static instance to pass.
	 */
	private static DBHelper mInstance = null;
	
	// DB stuff.
	private static final String DB_NAME = "FuelLogger.db";
	private static final int DB_VERSION = 1;
	
	/**
	 * Fillup table names.
	 */
	public static final String TABLE_NAME_FILLUPS ="fillups";	
	
	/**
	 * Database Fillup table creation statement.
	 */
	private static final String DB_CREATE_STRING =
			"create table " + TABLE_NAME_FILLUPS +" ( "+
			Fillup.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			Fillup.COLUMN_NAME_PRICE +" REAL, " +
			Fillup.COLUMN_NAME_QT + " REAL, " +
			Fillup.COLUMN_NAME_DATE +" TEXT, " +
			Fillup.COLUMN_NAME_DISTANCE + " INTEGER," +
			Fillup.COLUMN_NAME_PARTIAL + " INTEGER );";

	SQLiteDatabase dataBase;
	
	public static DBHelper getInstance(Context ctx){
		if(mInstance == null){
			mInstance = new DBHelper(ctx);
		}
		return mInstance;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param context the actual context.
	 */
	private DBHelper(Context context){
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
		// On upgrade of the table.
	}
	
	/**
	 * Open the database for operation.
	 * 
	 * @param readOnly if open in read or write mode.
	 */
	private void openDB(boolean readOnly){
		dataBase= readOnly==true?this.getReadableDatabase():this.getWritableDatabase();
	}
	
	private void closeDB(){
		dataBase.close();
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
		openDB(false);
		ContentValues initialValues = createContentValues(fillup);
		long tmp = dataBase.insertOrThrow(TABLE_NAME_FILLUPS,null,initialValues);
		closeDB();
		return tmp;
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
		//cv.put(Fillup.COLUMN_NAME_ID, fillup.getId());
		cv.put(Fillup.COLUMN_NAME_PRICE, fillup.getPrice());
		cv.put(Fillup.COLUMN_NAME_QT, fillup.getQt());
		cv.put(Fillup.COLUMN_NAME_DISTANCE, fillup.getDistance());
		cv.put(Fillup.COLUMN_NAME_PARTIAL, fillup.isPartial()?1:0);
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
		openDB(false);
		ContentValues cv=createContentValues(newValues);
		int tmp = dataBase.update(TABLE_NAME_FILLUPS, cv, 
								Fillup.COLUMN_NAME_ID+"= ?", 
								new String[]{String.valueOf(oldValues.getId())});
		closeDB();
		return tmp>0;
	}
	
	/**
	 * Delete a fillup.
	 * 
	 * @param target the fillup to be deleted, id should be set.
	 * 
	 * @return true if deleted, false elsewhere.
	 */
	public boolean deleteFillup(Fillup target){
		openDB(false);
		int tmp = dataBase.delete(TABLE_NAME_FILLUPS, Fillup.COLUMN_NAME_ID + " = ?", 
								new String[]{String.valueOf(target.getId())});
		closeDB();
		return tmp>0;
	}
	
	/**
	 * Get a fillup by id.
	 * 
	 * @param id the id.
	 * 
	 * @return the selected fillup with the id passed null if it doesn't exists.
	 */
	public Fillup getFillup(int id){
		openDB(true);
		Cursor cur= dataBase.query(TABLE_NAME_FILLUPS, 
									new String[]{Fillup.COLUMN_NAME_ID,
												Fillup.COLUMN_NAME_DATE,
												Fillup.COLUMN_NAME_DISTANCE,
												Fillup.COLUMN_NAME_PRICE,
												Fillup.COLUMN_NAME_QT,
												Fillup.COLUMN_NAME_PARTIAL}, 
									Fillup.COLUMN_NAME_ID +"=?", 
									new String[]{String.valueOf(id)}, 
									null, 
									null, 
									null);
		if(cur!=null){
			cur.moveToFirst();
			Fillup fU = new Fillup();
			fU.setId(cur.getInt(0));
			fU.setDate(cur.getString(1));
			fU.setDistance(cur.getLong(2));
			fU.setPrice(cur.getFloat(3));
			fU.setQt(cur.getFloat(4));
			fU.setPartial(cur.getInt(5)==1);
			cur.close();
			closeDB();
			return fU;
		}
		closeDB();
		return null;
	}
	
	/**
	 * Get all the fillups.
	 * 
	 * @return a List with all the fillups.
	 */
	public List<Fillup> getAllFillups(){
		openDB(true);
		Cursor cur= dataBase.query(TABLE_NAME_FILLUPS, 
				new String[]{Fillup.COLUMN_NAME_ID,
				Fillup.COLUMN_NAME_DATE,
				Fillup.COLUMN_NAME_DISTANCE,
				Fillup.COLUMN_NAME_PRICE,
				Fillup.COLUMN_NAME_QT,
				Fillup.COLUMN_NAME_PARTIAL}, 
				null, 
				null, 
				null, 
				null, 
				null);
		List<Fillup> fillups=new ArrayList<Fillup>();
		if(cur.moveToFirst()){
			do{
				Fillup fU = new Fillup();
				fU.setId(cur.getInt(0));
				fU.setDate(cur.getString(1));
				fU.setDistance(cur.getLong(2));
				fU.setPrice(cur.getFloat(3));
				fU.setQt(cur.getFloat(4));
				fU.setPartial(cur.getInt(5)==1);
				fillups.add(fU);
			}while(cur.moveToNext());
			cur.close();
		}
		closeDB();
		return fillups;
	}
	
}
