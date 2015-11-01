package com.example.saurabh.smartattendanceapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.saurabh.smartattendanceapplication.network.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class SemForm extends AppCompatActivity {
    private TextInputLayout ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_form);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FONT.TTF");
        ((TextView) findViewById(R.id.sem1)).setTypeface(typeface);
        ((TextView) findViewById(R.id.sem2)).setTypeface(typeface);
        ((TextView) findViewById(R.id.sem3)).setTypeface(typeface);
        ((TextView) findViewById(R.id.sem4)).setTypeface(typeface);
        ((TextView) findViewById(R.id.sem5)).setTypeface(typeface);
        ((TextView) findViewById(R.id.sem6)).setTypeface(typeface);
        ((TextView) findViewById(R.id.sem7)).setTypeface(typeface);
        ((TextView) findViewById(R.id.sem8)).setTypeface(typeface);
        ((TextView) findViewById(R.id.radioButton3)).setTypeface(typeface);
        ((TextView) findViewById(R.id.radioButton4)).setTypeface(typeface);
        ((TextView) findViewById(R.id.radioButton5)).setTypeface(typeface);
        ((TextView) findViewById(R.id.radioButton6)).setTypeface(typeface);
        ip= (TextInputLayout) findViewById(R.id.ip_address);

    }

    public void nextActivity(View view) {
        String url = "http://"+ip.getEditText().getText().toString()+"/Attendance/GetData.php?class=";
        final Intent intent = new Intent(this, AttendanceActivity.class);
        intent.putExtra("ip",ip.getEditText().getText().toString());
        switch (((RadioGroup) findViewById(R.id.btngp1)).getCheckedRadioButtonId()) {
            case R.id.radioButton3:
                intent.putExtra("class", "CSE3");
                url += "cse3&sem=";
                break;
            case R.id.radioButton4:
                intent.putExtra("class", "ECE");
                break;
            case R.id.radioButton5:
                intent.putExtra("class", "IT");
                break;
            case R.id.radioButton6:
                intent.putExtra("class", "EEE");
                break;
        }
        switch (view.getId()) {
            case R.id.sem1:
                intent.putExtra("sem", 1);
                break;
            case R.id.sem2:
                intent.putExtra("sem", 2);
                break;
            case R.id.sem3:
                intent.putExtra("sem", 3);
                break;
            case R.id.sem4:
                intent.putExtra("sem", 4);
                break;
            case R.id.sem5:
                intent.putExtra("sem", 5);
                break;
            case R.id.sem6:
                intent.putExtra("sem", 6);
                break;
            case R.id.sem7:
                intent.putExtra("sem", 7);
                url += '7';
                break;
            case R.id.sem8:
                intent.putExtra("sem", 8);
                break;
        }
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
        pd.setMessage("Getting Students...");
        pd.show();
        Log.d("abc",url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Iterator<String> keys = response.keys();
                        ArrayList<StudentModel> studentModelList = new ArrayList<>();
                        while (keys.hasNext()) {
                            String roll = keys.next();
                            try {
                                StudentModel student = new StudentModel(roll, response.getString(roll));
                                studentModelList.add(student);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        intent.putParcelableArrayListExtra("data", studentModelList);
                        pd.dismiss();
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Log.d("abc",error.toString());
                        Toast.makeText(SemForm.this, "Attendance already taken.", Toast.LENGTH_LONG).show();
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sem_form, menu);
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
