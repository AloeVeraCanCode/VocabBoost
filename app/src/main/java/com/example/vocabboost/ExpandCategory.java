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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.internal.VisibilityAwareImageButton;

public class ExpandCategory extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener {
    public String tableName;
    public Context context;
    public Cursor cursor;
//    public String w,m,s;
//    bool editable;
//    void set(Strig word,String meaning, String sentence,boolean v)
//    {
//        w=word;m=meaning;s=sentence;editable=v;
//    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=inflater.inflate(R.layout.fragment_expand_catagory, container, false);
        ImageButton ib=(ImageButton)layout.findViewById(R.id.button_word);
        ib.setOnClickListener(this);
        ListView lv=layout.findViewById(R.id.list_word);
        VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(context);
        SQLiteDatabase mydb=db.getReadableDatabase();
        cursor=mydb.rawQuery("SELECT  * FROM "+tableName, null);
        Log.d("****!!Second:",Integer.toString(cursor.getCount()));
        Log.d("!!!!!!!Second:",tableName);
        ExpandCursorAdapter eca=new ExpandCursorAdapter(context,cursor,tableName);
        lv.setAdapter(eca);
        lv.setOnItemClickListener(this);
        return(layout);
    }

    @Override
    public void onClick(View v) {
        WordInsertionFragment f=new WordInsertionFragment();
        f.tableName=tableName;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, f);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try {
            TextView w=view.findViewById(R.id.text_word);
            TextView m=view.findViewById(R.id.text_meaning);
            TextView s=view.findViewById(R.id.text_sentence);
            ExpandWordFragment fragment=new ExpandWordFragment();
            Log.d("****!!Firth:",w.getText().toString());
            Log.d("****!!Firth:",m.getText().toString());
            Log.d("****!!Firth:",s.getText().toString());
            Log.d("****!!Firth:",Integer.toString((position)));
            fragment.set(w.getText().toString(),m.getText().toString(),s.getText().toString(),tableName,position);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        catch(Exception e)
        {
            Log.d("MyName:","gandu");
        }
    }
}