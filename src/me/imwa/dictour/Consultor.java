package me.imwa.dictour;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Consultor {
	public Validator validator;
	public Context context;
	private DBHelper helper;
	private SQLiteDatabase database;
	
	public Consultor(Context context){
		this.context = context;
		this.validator = new Validator(context);
		this.helper = new DBHelper(context);
		this.database =  helper.openDatabase();
	}
	
	public Cursor getCursor(String keyword){
		Cursor cs;
		String[] searchString = validator.getSearchString(keyword);
		String qr = searchString[0];
		String kw = searchString[1];
		if(qr.equals("")){
			cs = null;
		} else{
			cs = database.rawQuery(qr, new String[]{kw});
		}

		return cs;
	}

	public String isNull(Cursor cs, String type){
		String result = cs.getString(cs.getColumnIndex(type));
		if(result==null||result.equals("")){
			return context.getString(R.string.item_null);
		}else {
			return result;
		}
	}

	public ArrayList<DictItem> getSearch( Cursor cs ){
		ArrayList<DictItem> dis = new ArrayList<DictItem>();
		while(cs.moveToNext()){
			DictItem di = new DictItem();
			di.Pinyin = isNull( cs, "Pinyin" );
			di.Hanzi  = isNull( cs, "Hanzi" );
			di.Description = isNull( cs, "Description" );
			di.Example     = isNull( cs, "Example" );
			dis.add(di);
		}
		cs.close();
		
		return dis;
	}

	public ArrayList<DictItem> query(String keyword){
		ArrayList<DictItem> dis = new ArrayList<DictItem>();
		Cursor c = getCursor(keyword);
		if(c!=null){
			dis = getSearch(c);
		} else {
			dis = null;
		}
		return dis;
	}


}
