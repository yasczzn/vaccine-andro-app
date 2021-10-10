package com.example.vaksinapplication.ui.register;

import android.app.Activity;
import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;

public class RegisterFragment extends Fragment {
    private FirebaseAnalytics mFirebaseAnalytics;
    Activity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        context = getActivity();

        return root;
    }


    public void onStart(){
        super.onStart();
        FloatingActionButton fab = context.findViewById(R.id.add_register);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CreateActivity.getActIntent(context));
            }
        });

        Button btn = context.findViewById(R.id.see_register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ReadActivity.getActIntent(context));
            }
        });

    }


}