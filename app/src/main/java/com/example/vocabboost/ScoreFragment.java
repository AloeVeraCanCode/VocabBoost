package com.example.vocabboost;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vocabboost.Common.VocabBoostDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreFragment extends Fragment {
    public View layout;
    public TextView t;
    public Context context;
    public String tableName;
    int score;
    public void set(int s,Context c,String t){score=s;context=c;tableName=t;}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout=inflater.inflate(R.layout.fragment_score, container, false);

        t=(TextView) layout.findViewById(R.id.score);
        Log.d("13th","Here3");
        t.setText(Integer.toString(score));
        addScore();
        Log.d("eigth","Here4");
        return(layout);
    }
    public void addScore()
    {
        VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(context);
        SQLiteDatabase mydb=db.getWritableDatabase();
        ContentValues toBeInserted = new ContentValues();
        toBeInserted.put("DISPLAY", Integer.toString(score));
        toBeInserted.put("TIME", findTime());
        Log.d("13th","HERE@@@@@");
        mydb.insert(tableName, null, toBeInserted);
        db.close();mydb.close();
    }
    public String findTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return(formatter.format(date));
    }
}