package me.imwa.dictour;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TextTest extends Activity {
	private TextView txt;
	private EditText mi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_test);

		Typeface font = Typeface.createFromAsset(getAssets(), "DC_White.ttf");
		txt = (TextView) findViewById(R.id.manju_font);
		txt.setTypeface(font);

		mi = (EditText)findViewById(R.id.manju_input);
		mi.setTypeface(font);
	}
}
