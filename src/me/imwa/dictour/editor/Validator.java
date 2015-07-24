package me.imwa.dictour.editor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;

public class Validator {
	private static final int IS_PINYIN_FULL  = 1;
	private static final int IS_PINYIN_FUZZY = 2;
	private static final int IS_HANZI_FULL   = 3;
	private static final int IS_HANZI_FUZZY  = 4;
	private static final int IS_INVALID_INPUT= 0;
	public Context context;
	
	public Validator( Context context){
		this.context = context;
	}

	public boolean isMatch(String regex, String match){
		Pattern pt = Pattern.compile(regex);
		Matcher mt = pt.matcher(match);
		boolean found = false;
		if(mt.find()){
			found = true;
		}
		return found;
	}

	public int getType(String keyword){
		String isPinyin = "^([A-Za-z]+[0-9]{1}[ ]*)+$";
		String isPinyinFuzzy = "^([A-Za-z]+[0-9]?[ ]*)+$";
		
		String isHanzi = "^([\u4E00-\u9FA5]+)+$";
		String isHanziFuzzy = "^([\u4E00-\u9FA5]+[ ]*)+$";
		
		if(isMatch(isPinyin, keyword)){
			return IS_PINYIN_FULL;
		} else if(isMatch(isPinyinFuzzy, keyword)){
			return IS_PINYIN_FUZZY;
		} else if(isMatch(isHanzi, keyword)){
			return IS_HANZI_FULL;
		} else if(isMatch(isHanziFuzzy, keyword)){
			return IS_HANZI_FUZZY;
		} else {
			return IS_INVALID_INPUT;
		}
	}
	
	public String[] getSearchString(String keyword){
		String[] sta = new String[2];
		int Type = getType(keyword);

		switch(Type){
			case IS_PINYIN_FULL:
				sta[0] = new String("SELECT * FROM DICT where Pinyin=?");
				sta[1] = new String( fullPinyin(keyword) );
				break;
			case IS_PINYIN_FUZZY:
				sta[0] = new String("SELECT * FROM DICT where Pinyin like ?");
				sta[1] = new String( fuzzyPinyin(keyword) );
				break;
			case IS_HANZI_FULL:
				sta[0] = new String("SELECT * FROM DICT where Hanzi=?");
				sta[1] = new String(keyword);
				break;
			case IS_HANZI_FUZZY:
				sta[0] = new String("SELECT * FROM DICT where Hanzi like ?");
				sta[1] = new String(keyword);
				break;
			default:
				sta[0] = "";
				sta[1] = "";
		}
		return sta;
	}
	
	public String fullPinyin(String keyword){
		String kw;
		if(keyword.matches("[A-Za-z]+[0-9]{1}") || keyword.indexOf(" ")>=0){
			kw = keyword;
		} else {
			kw = keyword.replaceAll("([0-9])","$1 ");
			kw = kw.trim();
		}
		return kw;
	}
	public String fuzzyPinyin(String keyword){
		String kw;
		if(keyword.indexOf(" ")>=0){
			kw = keyword;
			kw = kw.replaceAll("([A-Za-z])([ ])", "$1_$2");
			kw = kw.trim();
			kw = kw.replaceAll("([A-Za-z])$", "$1_");
		} else {
			kw = keyword.replaceAll("([0-9])", "$1 ");
			kw = kw.replaceAll("([A-Za-z])([ ])", "$1_$2");
			kw = kw.trim();
			kw = kw.replaceAll("([A-Za-z])$", "$1_");
		}
		return kw;
	}
	public String fullHanzi(String keyword){
		String kw;
		if(keyword.length()>=2){
			kw = keyword+"%";
		} else {
			kw = keyword;
		}
		return kw;
	}
	public String fuzzyHanzi(String keyword){
		String kw;
		if(keyword.indexOf(" ")>=0){
			kw = keyword.replaceAll(" ", "%");
		} else {
			kw = keyword;
		}
		return kw;
	}
	

}
