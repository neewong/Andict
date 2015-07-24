package me.imwa.dictour.dictionary;

public class DictItem {
	public String Pinyin;
	public String Hanzi;
	public String Description;
	public String Example;
	
	public DictItem(){
		// TODO:
	}
	
	public DictItem(String Pinyin, String Hanzi, String Description, String Example){
		this.Pinyin = Pinyin;
		this.Hanzi = Hanzi;
		this.Description = Description;
		this.Example = Example;
	}
}
