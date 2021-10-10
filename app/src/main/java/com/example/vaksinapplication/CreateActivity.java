package com.example.vaksinapplication;

import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vaksinapplication.model.Pasien;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class CreateActivity extends AppCompatActivity {
    private DatabaseReference database;
    private EditText nama;
    private EditText nik;
    private EditText jk;
    private EditText nohp;
    private EditText email;
    private EditText alamat;
    private Button btSave;
    private FirebaseAnalytics mFirebaseAnalytics;
    private Pasien pasien = null;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        nama = findViewById(R.id.editText1);
        nik = findViewById(R.id.editText2);
        jk = findViewById(R.id.editText3);
        nohp = findViewById(R.id.editText4);
        email = findViewById(R.id.editText5);
        alamat = findViewById(R.id.editText6);
        btSave = findViewById(R.id.btsave);
        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://keluargasehat2-00-default-rtdb.firebaseio.com/");
        pasien = (Pasien) getIntent().getSerializableExtra("data");
        if (pasien != null) {
            nama.setText(pasien.getNama());
            nik.setText(pasien.getNik());
            jk.setText(pasien.getJenisKelamin());
            nohp.setText(pasien.getNo());
            email.setText(pasien.getEmail());
            alamat.setText(pasien.getAlamat());
        }
    }

    public void save(View v) {
        if (pasien != null) {
            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pasien.setNama(nama.getText().toString());
                    pasien.setNik(nik.getText().toString());
                    pasien.setJenisKelamin(jk.getText().toString());
                    pasien.setNo(nohp.getText().toString());
                    pasien.setEmail(email.getText().toString());
                    pasien.setAlamat(alamat.getText().toString());
                    updatePasien(pasien);
                }
            });
        } else {
            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if(!isEmpty(nama.getText().toString()) && !isEmpty(nik.getText().toString()) && !isEmpty(jk.getText().toString())
                        && !isEmpty(nohp.getText().toString()) && !isEmpty(email.getText().toString()) && !isEmpty(alamat.getText().toString()))
                    submitPasien(new Pasien(nama.getText().toString(), nik.getText().toString(), jk.getText().toString(),
                            nohp.getText().toString(), email.getText().toString(), alamat.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.btsave), "Data pasien tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                            InputMethodManager imm = (InputMethodManager)
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        nama.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    private void updatePasien(Pasien pasien) {
        database.child("keluargasehat2")
                .child(pasien.getKey())
                .setValue(pasien)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nama.setText("");
                        nik.setText("");
                        jk.setText("");
                        nohp.setText("");
                        email.setText("");
                        alamat.setText("");
                        Snackbar.make(findViewById(R.id.btsave), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG)
                        .setAction("Oke", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                        }).show();
                    }
                });
    }

    private void submitPasien(Pasien pasien) {
        database.child("keluargasehat2").push().setValue(pasien).addOnSuccessListener(this, new
                OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nama.setText("");
                        nik.setText("");
                        jk.setText("");
                        nohp.setText("");
                        email.setText("");
                        alamat.setText("");
                        Snackbar.make(findViewById(R.id.btsave), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public static Intent getActIntent(Activity activity) {
// kode untuk pengambilan Intent
        return new Intent(activity, CreateActivity.class);
    }

}