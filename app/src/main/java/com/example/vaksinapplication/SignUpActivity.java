package com.example.vaksinapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.widget.Toast;

import com.example.vaksinapplication.MainActivity;
import com.example.vaksinapplication.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.analytics.FirebaseAnalytics;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    Button btnSignUp;
    TextView tvLogin;
    EditText emailId, password;
    FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tvLogin = findViewById(R.id.tvlogin);
        mfirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();
                }
                else if(pass.isEmpty()){
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pass.isEmpty())){
                    mfirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignUpActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this,"Sign up unsuccessful, Please try again",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        masuk();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });


        tvLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void masuk() {
        Intent masuk = new Intent(SignUpActivity.this, HomeFragment.class);
        startActivity(masuk);
    }

    public void masuk(View view){
        masuk();
    }

}