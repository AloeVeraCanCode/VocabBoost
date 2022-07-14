package com.example.vocabboost;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.vocabboost.Quiz.QuizFragment;
import com.example.vocabboost.Wordle.WordleFragment;


public class HomeFragment extends Fragment implements  View.OnClickListener{
    public Activity activity;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton s=(ImageButton)layout.findViewById(R.id.button_show);
        ImageButton w=(ImageButton)layout.findViewById(R.id.button_wordle);
        ImageButton q=(ImageButton)layout.findViewById(R.id.button_quiz);
        s.setOnClickListener(this);w.setOnClickListener(this);q.setOnClickListener(this);
        return(layout);
    }

    @Override
    public void onClick(View v) {
        selectItem(v);
    }
    private void selectItem(View v) {
        Fragment fragment=new Fragment();
        switch(v.getId()) {
            case R.id.button_show:
                fragment = new ShowFragment();
                break;
            case R.id.button_quiz:
                QuizFragment frag= new QuizFragment();
                fragment=frag;
                frag.set(activity);
                break;
            case R.id.button_wordle:
                fragment  = new WordleFragment();
                break;
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void set(Activity a) {
        activity=a;
    }
}