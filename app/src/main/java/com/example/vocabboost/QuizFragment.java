package com.example.vocabboost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.internal.VisibilityAwareImageButton;


public class QuizFragment extends Fragment implements View.OnClickListener {
    public Context context;
    public View layout;
    public Activity activity;
    @Override
    public void onAttach(Context c) {
        super.onAttach(context);
        context=c;
    }
    public void set(Activity a)
    {
        Log.d("Seventh", "Pass1");
        activity=a;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Seventh", "Pass2");
        layout= inflater.inflate(R.layout.fragment_quiz, container, false);
        Button btn=(Button) layout.findViewById(R.id.play_quiz);
        Button btnscr=(Button) layout.findViewById(R.id.play_quiz);
        btn.setOnClickListener(this);btnscr.setOnClickListener(this);
        return(layout);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.quiz_score)
        {

            return;
        }
        else {
            QuizObject q = new QuizObject(5, context);
            Intent intent = new Intent(context, QuizActivity.class);
            intent.putExtra("QuizObject", q);
            Log.d("Seventh", "Pass");
            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.d("Seventh", "activity fail");
            }
        }
    }
}