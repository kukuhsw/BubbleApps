package com.nanobubble.android;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.nanobubble.android.utils.Prefs;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mPhoneNumber;
    private EditText mPassword;

    private Button mBtnLogin;

    private TextView mRegister;
    private TextView mForgotPassword;

    private CheckBox mSavePhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPhoneNumber = findViewById(R.id.phone_number);
        mPassword = findViewById(R.id.password);

        mBtnLogin = findViewById(R.id.login_button);

        mRegister = findViewById(R.id.register);
        mForgotPassword = findViewById(R.id.forgot_password);

        mSavePhoneNumber = findViewById(R.id.save_phone);

        initListener();
        mPhoneNumber.setText(Prefs.getInstance(this).getLastPhone());

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Prefs.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else{
            mPhoneNumber.setText(Prefs.getInstance(this).getLastPhone());
        }
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSavePhoneNumber.isChecked()) {
                    Prefs.getInstance(LoginActivity.this).saveLogin(mPhoneNumber.getText().toString(), true);
                } else {
                    Prefs.getInstance(LoginActivity.this).setLogin(true);
                }

                if(TextUtils.isEmpty(mPhoneNumber.getText())){
                    mPhoneNumber.setError("Required");
                    return;
                }

                if(TextUtils.isEmpty(mPassword.getText())){
                    mPassword.setError("Required");
                    return;
                }

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
