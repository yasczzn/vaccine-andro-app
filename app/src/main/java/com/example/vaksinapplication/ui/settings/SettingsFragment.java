package com.example.vaksinapplication.ui.settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.vaksinapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;

public class SettingsFragment extends Fragment {
    private FirebaseAnalytics mFirebaseAnalytics;
    Activity context;
    static Boolean isTouched = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        context = getActivity();

        return root;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onStart(){
        super.onStart();

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            context.setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar);
        } else {
            context.setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar);
        }

        context.setContentView(R.layout.fragment_settings);
        SwitchCompat tvChangeTheme = (SwitchCompat) context.findViewById(R.id.tvChangeTheme);
        tvChangeTheme.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched = true;
                return false;
            }
        });
        tvChangeTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isTouched) {
                    isTouched = false;
                    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                }
            }
        });

    }

}