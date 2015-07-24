package me.imwa.dictour.dictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
*/


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {

	public static final String DATABASE_NAME = "dict.db";	
	public static final int DATABASE_VERSION = 1;
	
	public DBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public SQLiteDatabase openDatabase(){
		SQLiteDatabase db = getReadableDatabase();
		if(db==null){
			System.out.print("DB is null!");
		} else {
			System.out.print("DB is not null!");
		}
		return db;
	}
}
