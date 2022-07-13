package com.example.vocabboost;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;


public class ResultFragment extends Fragment  {
    public String tableName;
    public Context context;
    public Cursor cursor;
    //    public String w,m,s;
//    bool editable;
    public void set(String t)
    {
        tableName=t;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_result, container, false);
        ListView lv=layout.findViewById(R.id.list_history);
        VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(context);
        SQLiteDatabase mydb=db.getReadableDatabase();
        cursor=mydb.rawQuery("SELECT  * FROM "+tableName, null);
        Log.d("14th:",Integer.toString(cursor.getCount())+tableName);
        Log.d("!!!!!!!Second:",tableName);
        ResultCursorAdapter eca=new ResultCursorAdapter(context,cursor,tableName);
        lv.setAdapter(eca);
        return(layout);
    }
}