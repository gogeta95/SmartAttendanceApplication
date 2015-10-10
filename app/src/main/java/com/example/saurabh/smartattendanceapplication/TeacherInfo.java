package com.example.saurabh.smartattendanceapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class TeacherInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tchr_info);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/FONT.TTF");
        ((TextView)findViewById(R.id.textView)).setTypeface(typeface);
        ((TextView)findViewById(R.id.textView11)).setTypeface(typeface);
        ((TextView)findViewById(R.id.textView12)).setTypeface(typeface);
        ((TextView)findViewById(R.id.textView14)).setTypeface(typeface);

        ((TextView)findViewById(R.id.editText7)).setTypeface(typeface);
        ((TextView)findViewById(R.id.editText8)).setTypeface(typeface);
        ((TextView)findViewById(R.id.editText9)).setTypeface(typeface);

        ((TextView)findViewById(R.id.button9)).setTypeface(typeface);
        ((TextView)findViewById(R.id.button10)).setTypeface(typeface);
        ((TextView)findViewById(R.id.button11)).setTypeface(typeface);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tchr_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
