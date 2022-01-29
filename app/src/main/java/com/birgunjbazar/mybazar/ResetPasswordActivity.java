package com.birgunjbazar.mybazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText registeredEmail;
    private Button resetPasswordButton;
    private TextView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        registeredEmail=(EditText)findViewById(R.id.forgotPassword_email);
        resetPasswordButton=(Button)findViewById(R.id.forgotPassword_submitButton);
        goBack=(TextView)findViewById(R.id.forgotPassword_back);

        registeredEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput(count);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent=new Intent(ResetPasswordActivity.this,LoginActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

    }
    private void checkInput(int cnt){
        if(registeredEmail.getText().toString().equals("")){
            resetPasswordButton.setEnabled(false);
            resetPasswordButton.setTextColor(Color.argb(50,255,255,255));
        }
        else if(!registeredEmail.getText().toString().equals("")){
            resetPasswordButton.setEnabled(true);
            resetPasswordButton.setTextColor(Color.rgb(255,255,255));
        }
    }
}