package com.example.vocabboost;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


public class WordInsertionFromWordInsertion extends Fragment implements  View.OnClickListener{
    public Context context;
    public String tableName;
    public EditText word,meaning,sentence;
    public String w,m,s;
    public int _id;
    public boolean editable;
    public View layout;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("!!Second:","Aj1");
        View l= inflater.inflate(R.layout.fragment_word_insertion, container, false);
        layout=l;
        ImageButton btn=(ImageButton)layout.findViewById(R.id.button_save);
        btn.setOnClickListener(this);
        EditText ew=(EditText) layout.findViewById(R.id.input_word);
        EditText em=(EditText) layout.findViewById(R.id.input_meaning);
        EditText es=(EditText) layout.findViewById(R.id.input_sentence);
        word=ew;meaning=em;sentence=es;
        word.setText(w);meaning.setText(m);sentence.setText(s);
        word.setEnabled(false);
         Log.d("!!Second:","Aj");
        return(layout);
    }
    public void set(String a,String b,String c,String t,int x)
    {
        w=a;m=b;s=c;_id=x;tableName=t;
        if(word!=null){
        Log.d("!!Second:",a);
        Log.d("!!Second:",b);
        Log.d("!!Second:",c);}
        //word.setText(a);meaning.setText(b);sentence.setText(c);
        Log.d("!!Second:","Aj2");
        //word.setEnabled(false);
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
            Log.d("!!Second:","4");
            String m=meaning.getText().toString();
            String s=sentence.getText().toString();
            Log.d("!!Second:",s);
            Log.d("!!Second:","mytable:"+tableName);
            ContentValues toBeInserted = new ContentValues();
            toBeInserted.put("MEANING", m);
            toBeInserted.put("SENTENCE", s);
            Log.d("!!Second:",Integer.toString(_id));

            mydb.update(tableName, toBeInserted, "WORD = ?", new String[] {w});

            Log.d("!!Second:","UPDATE");
            mydb.close();db.close();
            db=new VocabBoostDatabaseHelper(context);
            Log.d("!!Second:","2");
            mydb=db.getReadableDatabase();
            Cursor res = mydb.rawQuery("SELECT  * FROM "+tableName, null);
            Log.d("!!Second:","3");

            Log.d("!!Second:",Integer.toString(res.getCount()));
            if(res!=null) {
                res.moveToFirst();
                String str = res.getString(3);
                Log.d("!!Second:", "5");
                Log.d("!!Second:", str);

                //Log.d("!!Second:","Success"+x.getInt(Integer.parseInt("_id")));
                mydb.close();
                db.close();
            }
            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack();
            ExpandWordFragment fragment=new ExpandWordFragment();
            fragment.set(w,m,s,tableName,_id);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        catch (Exception e){Log.d("!!Second:","Failed");}
    }
    void insertWord(SQLiteDatabase mydb,ContentValues toBeInserted)
    {
        mydb.insert(tableName,null,toBeInserted);
    }
}