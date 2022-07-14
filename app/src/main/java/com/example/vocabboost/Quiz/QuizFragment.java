package com.example.vocabboost.Quiz;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.vocabboost.QuizActivity;
import com.example.vocabboost.QuizObject;
import com.example.vocabboost.R;
import com.example.vocabboost.ResultFragment;
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
        Button btnscr=(Button) layout.findViewById(R.id.quiz_score);
        btn.setOnClickListener(this);btnscr.setOnClickListener(this);
        return(layout);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.quiz_score)
        {
            ResultFragment fragment=new ResultFragment();
            fragment.set("_WordleAttempts_");
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
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