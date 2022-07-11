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


public class CatagoryInsertFragment extends Fragment implements View.OnClickListener {
    public EditText et;
    public Context context;
   @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //et=new EditText(context);
        Log.d("Inside","here");
        View l= inflater.inflate(R.layout.fragment_catagory_insert, container, false);
        Log.d("Inside","here1");
        EditText e = (EditText) l.findViewById(R.id.text_input);
        et=e;
        ImageButton createButton = (ImageButton) l.findViewById(R.id.button_create);
        Log.d("Inside","here2");
        createButton.setOnClickListener(this);
        Log.d("Inside","here3");

        return(l);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_create:
                Log.d("First:","Here");
                VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(context);
                Log.d("First:","Here");
                SQLiteDatabase mydb=db.getWritableDatabase();
                Log.d("First:","Here4");
                String name=et.getText().toString();
                Log.d("!!First:",Integer.toString(name.length()));
                insertCatagory(mydb,name);
                Log.d("First:","Here2");
                break;
        }
        getFragmentManager().popBackStack();
    }
    public void insertCatagory(SQLiteDatabase mydb,String genre)
    {
        try {
            Log.d("First:",genre);
            mydb.execSQL("CREATE TABLE "
                    + genre
                    + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "WORD TEXT, "
                    + "MEANING TEXT, "
                    + "SENTENCE TEXT);");
            Log.d("First:", "Insertion finished!!!!");
        }
        catch (Exception e){Log.d("First:", "Fail");return;}
        try {
            Log.d("First:", "Hey");
            Log.d("First:", "my"+genre);
            ContentValues toBeInserted = new ContentValues();
            toBeInserted.put("GENRE", genre);
            mydb.insert("CATAGORY", null, toBeInserted);
            Log.d("First:", "Insertion finished");
           // insertTable(mydb,genre);
        }
        catch (Exception e){}
    }
    public void insertTable(SQLiteDatabase mydb,String genre)
    {
        try {
            Log.d("First:",genre);
            mydb.execSQL("CREATE TABLE "
                    + genre
                    + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "WORD INTEGER TEXT, "
                    + "MEANING INTEGER TEXT, "
                    + "SENTENCE TEXT);");
            Log.d("First:", "Insertion finished!!!!");
        }
        catch (Exception e){Log.d("First:", "Fail");return;}
        try {
            Log.d("First:", "Hey");
            Log.d("First:", "my"+genre);
            ContentValues toBeInserted = new ContentValues();
            toBeInserted.put("WORD", "gali");
            toBeInserted.put("MEANING", "jar ache");
            toBeInserted.put("SENTENCE", "Ei gandu");
            mydb.insert(genre, null, toBeInserted);
            Log.d("First:", "Insertion finished");
            insertTable(mydb,genre);
        }
        catch (Exception e){Log.d("First:", "UNFORTUNATE");}
    }
}