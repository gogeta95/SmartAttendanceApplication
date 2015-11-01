package com.example.saurabh.smartattendanceapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class AttendanceActivity extends AppCompatActivity {
    List<StudentModel> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_page);
        if (getSupportActionBar() != null) {
            String cls = getIntent().getStringExtra("class");
            int sem = getIntent().getIntExtra("sem", 0);
            getSupportActionBar().setTitle(cls + " Semester " + sem);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        students = getIntent().getParcelableArrayListExtra("data");
        RecyclerAdapter adapter = new RecyclerAdapter(this, students);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Button b = (Button) findViewById(R.id.save);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int present = 0;
                for (StudentModel student : students) {
                    if (student.present) {
                        present++;
                    }
                }
                new AlertDialog.Builder(AttendanceActivity.this).setMessage("Students Present: " + present + '/' + students.size())
                        .setPositiveButton("Send Data", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, final int which) {
                                        new SendDataTask().execute();
                                    }
                                }
                        )
                        .setNegativeButton("Cancel", null)
                        .create()
                        .show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    class SendDataTask extends AsyncTask<Void, Void, Integer> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(AttendanceActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Sending data...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                final JSONObject object = new JSONObject();
                object.put("class", getIntent().getStringExtra("class"));
                object.put("sem", getIntent().getIntExtra("sem", 0));
                final JSONObject data = new JSONObject();
                for (StudentModel model : students) {
                    data.put(model.roll_no, model.present);
                }
                object.put("data", data);
                Log.d("abc", object.toString());
                final URL url = new URL("http://"+getIntent().getStringExtra("ip")+"/Attendance/SendData.php");
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setFixedLengthStreamingMode(object.toString().getBytes().length);
                connection.getOutputStream().write(object.toString().getBytes());
                connection.getOutputStream().flush();
                Scanner sc = new Scanner(connection.getInputStream());
                String s = "";
                while (sc.hasNextLine()) {
                    s += sc.nextLine();
                }
                Log.d("abc", s);
                connection.disconnect();
                return 1;
            } catch (JSONException | IOException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            pd.dismiss();
            if (integer == 1) {
                Toast.makeText(AttendanceActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Toast.makeText(AttendanceActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
