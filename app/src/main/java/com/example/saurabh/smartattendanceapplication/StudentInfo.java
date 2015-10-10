package com.example.saurabh.smartattendanceapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class StudentInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/FONT.TTF");
        ((TextView)findViewById(R.id.textView5)).setTypeface(typeface);
        ((TextView)findViewById(R.id.textView6)).setTypeface(typeface);
        ((TextView)findViewById(R.id.textView7)).setTypeface(typeface);
        ((TextView)findViewById(R.id.textView8)).setTypeface(typeface);
        ((TextView)findViewById(R.id.textView9)).setTypeface(typeface);

        ((TextView)findViewById(R.id.editText3)).setTypeface(typeface);
        ((TextView)findViewById(R.id.editText4)).setTypeface(typeface);
        ((TextView)findViewById(R.id.editText5)).setTypeface(typeface);
        ((TextView)findViewById(R.id.editText6)).setTypeface(typeface);

        ((TextView)findViewById(R.id.button6)).setTypeface(typeface);
        ((TextView)findViewById(R.id.button7)).setTypeface(typeface);
        ((TextView)findViewById(R.id.button8)).setTypeface(typeface);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_info, menu);
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
