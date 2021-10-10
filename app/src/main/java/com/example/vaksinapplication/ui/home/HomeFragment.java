package com.example.vaksinapplication.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vaksinapplication.CreateActivity;
import com.example.vaksinapplication.R;
import com.example.vaksinapplication.ReadActivity;
import com.example.vaksinapplication.ui.register.RegisterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;

public class HomeFragment extends Fragment {
    private FirebaseAnalytics mFirebaseAnalytics;
    Activity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        context = getActivity();

        return root;
    }

    private void goToWeb() {
        goToUrl ( "https://pedulilindungi.id/");
    }
    private void goToWeb2() {
        goToUrl ( "https://covid19.go.id/peta-sebaran");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void onStart(){
        super.onStart();
        Button lanjut = context.findViewById(R.id.menu_lanjut);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWeb();
            }
        });

        Button btn = context.findViewById(R.id.menu_regis);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreateActivity.class);
                startActivity(intent);
            }
        });

        Button peta = context.findViewById(R.id.menu_peta);
        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWeb2();
            }
        });
    }

}