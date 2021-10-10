package com.example.vaksinapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.vaksinapplication.model.Pasien;


public class DetailActivity extends AppCompatActivity {
    private Button btSubmit;
    private EditText etNama;
    private EditText etNIK;
    private EditText etJK;
    private EditText etNohp;
    private EditText etEmail;
    private EditText etAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        etNama = (EditText) findViewById(R.id.et_namapasien);
        etNIK = (EditText) findViewById(R.id.et_nikpasien);
        etJK = (EditText) findViewById(R.id.et_jkpasien);
        etNohp = (EditText) findViewById(R.id.et_nohppasien);
        etEmail = (EditText) findViewById(R.id.et_emailpasien);
        etAlamat = (EditText) findViewById(R.id.et_alamatpasien);
        btSubmit = (Button) findViewById(R.id.btsave);
        etNama.setEnabled(false);
        etNIK.setEnabled(false);
        etJK.setEnabled(false);
        etNohp.setEnabled(false);
        etEmail.setEnabled(false);
        etAlamat.setEnabled(false);
        Pasien pasien = (Pasien) getIntent().getSerializableExtra("data");
        if(pasien!=null){
            etNama.setText(pasien.getNama());
            etNIK.setText(pasien.getNik());
            etJK.setText(pasien.getJenisKelamin());
            etNohp.setText(pasien.getNo());
            etEmail.setText(pasien.getEmail());
            etAlamat.setText(pasien.getAlamat());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DetailActivity.class);
    }

}