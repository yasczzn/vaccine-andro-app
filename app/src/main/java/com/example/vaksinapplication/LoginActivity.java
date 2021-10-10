package com.example.vaksinapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.vaksinapplication.ui.home.HomeFragment;
import com.google.firebase.analytics.FirebaseAnalytics;

public class LoginActivity extends AppCompatActivity {
  private FirebaseAnalytics mFirebaseAnalytics;
  Button btnLogin;
  TextView tvSignUp;
  EditText emailId, password;
  FirebaseAuth mfirebaseAuth;
  private FirebaseAuth.AuthStateListener mauthstateListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    tvSignUp = findViewById(R.id.tvsignup);
    btnLogin = findViewById(R.id.btnLogin);
    mfirebaseAuth = FirebaseAuth.getInstance();
    emailId = findViewById(R.id.email1);
    password = findViewById(R.id.password1);
    mauthstateListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser mfirebaseUser = mfirebaseAuth.getCurrentUser();
        if(mfirebaseUser != null){
          Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
          Intent inte = new Intent(LoginActivity.this, MainActivity.class);
          startActivity(inte);
        }
        else{
          Toast.makeText(LoginActivity.this,"Please login",Toast.LENGTH_SHORT).show();
        }
      }
    };

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = emailId.getText().toString();
        String pass = password.getText().toString();
        if (email.isEmpty()) {
          emailId.setError("Please enter your email");
          emailId.requestFocus();
        } else if (pass.isEmpty()) {
          password.setError("Password cannot be empty");
          password.requestFocus();
        } else if (email.isEmpty() && pass.isEmpty()) {
          Toast.makeText(LoginActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
        } else if (!(email.isEmpty() && pass.isEmpty())) {
          mfirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this,
                  new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login failed, Please try again",
                                Toast.LENGTH_SHORT).show();
                      } else {
                        masuk();
                      }
                    }
                  });
        }
        else{
          Toast.makeText(LoginActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
        }
      }
    });


    tvSignUp.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(i);
      }
    });

  }

  private void masuk() {
    Intent masuk = new Intent(LoginActivity.this, MainActivity.class);
    startActivity(masuk);
  }

  public void masuk(View view){
    masuk();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mfirebaseAuth.addAuthStateListener(mauthstateListener);
  }

}