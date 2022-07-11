package com.example.vocabboost;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ScoreFragment extends Fragment {
    public View layout;
    public TextView t;
    int score;
    public void set(int s){score=s;}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.fragment_score, container, false);

        t=(TextView) layout.findViewById(R.id.score);
        Log.d("eigth","Here3");
        t.setText(Integer.toString(score));
        Log.d("eigth","Here4");
        return(layout);
    }


}