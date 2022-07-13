package com.example.vocabboost;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.util.SizeF;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;


public class QuizActivity extends Activity implements View.OnClickListener {
    public int pageNo = 1;
    public QuizObject q;
    public ImageButton prev, next,exitTop,exitBot,finish;

    public Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=this;
        Intent intent = getIntent();
        q = (QuizObject) intent.getSerializableExtra("QuizObject");
        setContentView(R.layout.activity_quiz);
        prev = findViewById(R.id.prev);next = findViewById(R.id.next);exitTop=findViewById(R.id.exitTop);finish=findViewById(R.id.finish);exitBot=findViewById(R.id.exitBot);
        prev.setOnClickListener(this);next.setOnClickListener(this);exitTop.setOnClickListener(this);finish.setOnClickListener(this);exitBot.setOnClickListener(this);
        createFragment(pageNo);
    }

    public void createFragment(int pageNo) {
        visibility();
        quizQuestionFragment fragment = new quizQuestionFragment();
        fragment.set(q, pageNo - 1);
        Log.d("Seventh", "Fail1");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.quiz_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("Seventh", "Fail3");
        getMenuInflater().inflate(R.menu.quiz_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prev:
                pageNo--;
                visibility();
                getFragmentManager().popBackStack();
                break;
            case R.id.next:
                pageNo++;
                visibility();
                createFragment(pageNo);
                break;
            case R.id.exitTop:
                messageBox("Do you want to exit?");
                break;
            case R.id.exitBot:
                finishAndRemoveTask();
                break;
            case R.id.finish:
                prev.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                finish.setVisibility(View.INVISIBLE);
                exitTop.setVisibility(View.INVISIBLE);
                exitBot.setVisibility(View.VISIBLE);
                ScoreFragment fragment = new ScoreFragment();
                Log.d("Seventh", "Got");
                fragment.set(checkScore(),context,"_quizscore_");
                Log.d("Seventh", "Fail1");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.quiz_frame, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                break;
        }
    }

    private int checkScore() {
        Log.d("Seventh", "Got Here");
        int score=0,no=q.totalPage,j;
        for(j=0;j<no;j++)
        {
           int a= q.response[j].get(0);
           int b= q.answerNo[j].get(0);
           if(a==b) {Log.d("Seventh","Inside!!");score++;}
        }
        Log.d("Seventh", "my"+score);
        return(score);
    }

    public void visibility() {
        exitBot.setVisibility(View.INVISIBLE);
        if (pageNo == 1) {
            prev.setVisibility(View.INVISIBLE);
        } else {
            prev.setVisibility(View.VISIBLE);
        }
        if (pageNo == q.totalPage) {
            next.setVisibility(View.INVISIBLE);
        } else {
            next.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        //Disable BackButton
        Toast toast= Toast. makeText(getApplicationContext(),"Back Button is disabled for quiz",Toast. LENGTH_SHORT);
        toast. setMargin(50,50);
        toast. show();
    }
    public void messageBox(String s)
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        finishAndRemoveTask();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(s).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}