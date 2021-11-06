package com.example.vaksinapplication.ui.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.vaksinapplication.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class SettingsFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;
    Activity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        context = getActivity();
        Toolbar toolbar = context.findViewById(R.id.toolbar);

        return root;
    }

    private void goToWeb() {
        goToUrl ( "https://github.com/yasmin1812");
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onStart(){
        super.onStart();
        context.setContentView(R.layout.fragment_settings);
        TextView aboutUs = (TextView) context.findViewById(R.id.tvAboutUs);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWeb();
            }
        });

        SwitchCompat tvChangeTheme = (SwitchCompat) context.findViewById(R.id.scChangeTheme);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            tvChangeTheme.setChecked(true);
        }

        tvChangeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvChangeTheme.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    tvChangeTheme.setChecked(true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    tvChangeTheme.setChecked(false);
                }
            }
        });
    }

}