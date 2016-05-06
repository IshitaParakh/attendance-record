package com.example.ishita.attendancerecord;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditi-PC on 4/20/2016.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog pDialog;
    TextView user, pass;
    Button blogin;

    JSONParser jParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        blogin = (Button) findViewById(R.id.email_sign_in_button);
        //  bregister = (Button) findViewById(R.id.btn_register);

        user = (TextView) findViewById(R.id.email);
        pass = (TextView) findViewById(R.id.password);
        blogin.setBackgroundColor(Color.WHITE);

        blogin.setOnClickListener(this);
        // bregister.setOnClickListener(this);
        // bgoogle.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setCancelable(true);
        builder.setTitle("Permission");
        builder.setMessage("Sure to exit?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_in_button:
                //Toast.makeText(this, "Successful Login", Toast.LENGTH_LONG).show();
                new AttemptLogin().execute();
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        boolean failure = false;
        String username = user.getText().toString();
        String password = pass.getText().toString();


        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting to login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            int success;

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));
                Log.d("request!", "starting");
                JSONObject json = jParser.makeHttpRequest("http://10.0.2.2/android/login.php", "GET", params);
                Log.d("Login attempt", json.toString());
                success = json.optInt(TAG_SUCCESS);

                if (success == 1) {
                    Log.d("Successful Login", json.toString());
                    return json.getString(TAG_MESSAGE);
                }
                else {
                    Log.d("unSuccessful Login", json.toString());
                    return json.getString(TAG_MESSAGE);
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message){
            try {
                pDialog.dismiss();
                if (message != null) {
                    Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();
                }
                if(message.equals("Successful login")) {
                    Intent intent = new Intent(Login.this, NavigationActivity.class);
                    intent.putExtra("username",username);


                    startActivity(intent);
                    finish();
                }
            }
            catch (final IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

}