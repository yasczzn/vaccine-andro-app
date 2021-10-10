package com.example.vaksinapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vaksinapplication.model.AdapterPasienRecyclerView;
import com.example.vaksinapplication.model.Pasien;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity implements AdapterPasienRecyclerView.FirebaseDataListener {
    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Pasien> daftarPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        rvView = findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://keluargasehat2-00-default-rtdb.firebaseio.com/");
        database.child("keluargasehat2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daftarPasien = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pasien pasien = noteDataSnapshot.getValue(Pasien.class);
                    pasien.setKey(noteDataSnapshot.getKey());
                    daftarPasien.add(pasien);
                }
                adapter = new AdapterPasienRecyclerView(daftarPasien, ReadActivity.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ReadActivity.class);
    }

    @Override
    public void onDeleteData(Pasien pasien, final int position) {
        if(database!=null){
            database.child("keluargasehat2").child(pasien.getKey()).removeValue().addOnSuccessListener
                    (new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ReadActivity.this,"success delete",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}