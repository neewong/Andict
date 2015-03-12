package me.imwa.dictour;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;

public class Formater {
	
	private static final String HEAD = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scale=no\">";
	private static final String TITLE =	"<title></title>";
	private static final String STYLE = "</head><body>";
	private static final String TAIL = "</body></html>";
	private Context context;
	
	Formater(Context context){
		this.context = context;
	}
	public String toHTML(ArrayList<DictItem> dis){
		String rs;
		if(dis==null){
			return context.getResources().getString(R.string.invalid_hint);
		} else if(dis.isEmpty()){
			return context.getResources().getString(R.string.null_item_hint);
		} else {
			rs = "";
			Iterator<DictItem> it = dis.iterator();
			while(it.hasNext()){
				DictItem di=it.next();
				rs += "<p><strong>"+context.getResources().getString(R.string.des_pinyin)+"</strong>" + di.Pinyin + "</p>";
				rs += "<p><strong>"+context.getResources().getString(R.string.des_hanzi)+"</strong>" + di.Hanzi + "</p>";
				rs += "<p><strong>"+context.getResources().getString(R.string.des_description)+"</strong><br>" + di.Description + "</p>";
				rs += "<p><strong>"+context.getResources().getString(R.string.des_example)+"</strong><br>" + di.Example + "</p>";
			}
			return rs;
		}
	}

}
