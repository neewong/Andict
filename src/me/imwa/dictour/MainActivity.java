package me.imwa.dictour;

import me.imwa.dictour.dictionary.*;
import me.imwa.dictour.presentator.Formater;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	
	private Button search_button;
	private EditText search_content;
	private TextView search_result;
	private Consultor consultor;
	private Formater formater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		search_button  = (Button)findViewById(R.id.main_search_button);
		search_content = (EditText)findViewById(R.id.main_search);
		search_result  = (TextView)findViewById(R.id.search_result);
		this.consultor = new Consultor(MainActivity.this);
		this.formater  = new Formater(MainActivity.this);

		search_button.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				String content = search_content.getText().toString().trim();
				if( content.equals("") ){
					Toast.makeText(MainActivity.this, getResources().getString(R.string.null_hint), Toast.LENGTH_SHORT).show();
					search_content.setText("");
				} else {
					
					ArrayList<DictItem> dis = new ArrayList<DictItem>();
					dis = consultor.query(content);

					String rs;
					if(dis==null){
						search_result.setText(R.string.invalid_hint);
					} else if(dis.isEmpty()){
						search_result.setText(R.string.null_item_hint);
					} else {
						rs = formater.toHTML(dis);
						Spanned sp = Html.fromHtml(rs);
						search_result.setText(sp);
					}
				}
			}
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
		
			return true;
		} else if(id == R.id.about_author ){
			Intent jump2author = new Intent();
			jump2author.setClass(MainActivity.this, AboutAuthor.class);
			startActivity(jump2author);
	
			return true;
		} else if(id == R.id.text_test ) {
			Intent jump2text_test = new Intent();
			jump2text_test.setClass(MainActivity.this, TextTest.class);
			startActivity(jump2text_test);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
