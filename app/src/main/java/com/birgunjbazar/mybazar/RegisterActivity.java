package com.birgunjbazar.mybazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword,etuserName;
    Button btnRegister;

    final String url_Register = "https://www.birgunjbazar.com/demo/registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = findViewById(R.id.register_name);
        etuserName = (EditText) findViewById(R.id.register_username);
        etEmail = (EditText) findViewById(R.id.register_email);
        etPassword = (EditText) findViewById(R.id.register_password);
        btnRegister = (Button) findViewById(R.id.register_button);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = etName.getText().toString();
                String userName = etuserName.getText().toString();
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                //Toast.makeText(RegisterActivity.this, (Name +"->"+userName +"->"+Email +"->"+Password), Toast.LENGTH_SHORT).show();
                new RegisterUser().execute(Name,userName, Email, Password);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class RegisterUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String name = strings[0];
            String userName = strings[1];
            String Email = strings[2];
            String Password = strings[3];


            String finalURL = url_Register +"?name=" + name+ "&user_name=" + userName +
                    "&user_id=" + Email +
                    "&user_password=" + Password;

            try {
//                OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                builder.connectTimeout(1, TimeUnit.MINUTES) // connect timeout
//                        .writeTimeout(1, TimeUnit.MINUTES) // write timeout
//                        .readTimeout(1, TimeUnit.MINUTES); // read timeout
                OkHttpClient okHttpClient = new OkHttpClient(); //builder.build();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        System.out.println(result);
                        if (result.equals("User registered successfully")) {
                            showToast("Register successful");
                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else if (result.equals("User already exists")) {
                            showToast("User already exists");
                        } else {
                            showToast("oop! please try again");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showToast(e.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}