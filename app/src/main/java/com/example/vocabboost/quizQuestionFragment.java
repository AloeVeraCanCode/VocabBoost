package com.example.vocabboost;

import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;


public class quizQuestionFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    public View layout;
    public  QuizObject q;
    public int questionNumber;
    public  TextView question;
    public  RadioButton o1,o2,o3,o4;
    public RadioGroup radioGroup;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_quiz_question, container, false);
        radioGroup = (RadioGroup) layout.findViewById(R.id.options);
        radioGroup.setOnCheckedChangeListener(this);
        populateQuizFragment();
        return(layout);
    }
    public void set(QuizObject quiz,int qno)
    {
        q=quiz;questionNumber=qno;
    }
    public void populateQuizFragment()
    {
        question=(TextView)layout.findViewById(R.id.question);
        o1= (RadioButton)layout.findViewById(R.id.q1);
        o2=(RadioButton) layout.findViewById(R.id.q2);
        o3=(RadioButton)layout.findViewById(R.id.q3);
        o4=(RadioButton)layout.findViewById(R.id.q4);
        question.setText(q.question[questionNumber].get(0));
        o1.setText(q.question[questionNumber].get(1));
        o2.setText(q.question[questionNumber].get(2));
        o3.setText(q.question[questionNumber].get(3));
        o4.setText(q.question[questionNumber].get(4));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d("Seventh","Atleast");
        int option=-1;
        switch (checkedId) {
            case R.id.q1:
                option=1;
                break;
            case R.id.q2:
                option=2;
                break;
            case R.id.q3:
                option=3;
                break;
            case R.id.q4:
                option=4;
        }
        Log.d("Seventh","my"+option+questionNumber);
        Log.d("Seventh","Akash"+q.response[questionNumber].get(0));
        q.response[questionNumber].set(0,option);
        Log.d("Seventh","There");
    }
}