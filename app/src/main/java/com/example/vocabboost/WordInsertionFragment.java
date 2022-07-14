package com.example.vocabboost;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.vocabboost.Common.VocabBoostDatabaseHelper;


public class WordInsertionFragment extends Fragment implements View.OnClickListener {
    public Context context;
    public String tableName;
    public EditText word,meaning,sentence;
    public String w,m,s;
    public boolean editable;
    public View layout;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View l= inflater.inflate(R.layout.fragment_word_insertion, container, false);
        layout=l;
        ImageButton btn=(ImageButton)layout.findViewById(R.id.button_save);
        btn.setOnClickListener(this);
        EditText w=(EditText) layout.findViewById(R.id.input_word);
        EditText m=(EditText) layout.findViewById(R.id.input_meaning);
        EditText s=(EditText) layout.findViewById(R.id.input_sentence);
        word=w;meaning=m;sentence=s;
        return(layout);
    }

    @Override
    public void onClick(View v) {
        try {
            Log.d("!!Second:","1");
            VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(context);
            Log.d("!!Second:","2");
            SQLiteDatabase mydb=db.getWritableDatabase();
            Log.d("!!Second:","3");
            Log.d("!!Second:",meaning.getText().toString());
            String w=word.getText().toString();
            Log.d("!!Second:","4");
            String m=meaning.getText().toString();
            String s=sentence.getText().toString();
            Log.d("!!Second:",w);
            ContentValues toBeInserted = new ContentValues();
            toBeInserted.put("WORD", w);
            toBeInserted.put("MEANING", m);
            toBeInserted.put("SENTENCE", s);
            insertWord(mydb,toBeInserted);
            mydb.close();
            db.close();
            getFragmentManager().popBackStack();
        }
        catch (Exception e){Log.d("!!Second:","Failed");}
    }
    void insertWord(SQLiteDatabase mydb,ContentValues toBeInserted)
    {
       mydb.insert(tableName,null,toBeInserted);
    }
}