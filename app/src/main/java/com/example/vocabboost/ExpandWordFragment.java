package com.example.vocabboost;

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
import android.widget.ImageButton;
import android.widget.TextView;


public class ExpandWordFragment extends Fragment implements View.OnClickListener {
    public String w,m,s,tableName;
    public int _id;
    boolean editable;
    public Cursor cursor;
    public View layout;
    public Context context;
    void set(String word,String meaning, String sentence,String t,int x)
    {
        w=word;m=meaning;s=sentence;_id=x;tableName=t;
    }
    public void onAttach(Context c) {
        super.onAttach(context);
        context=c;
    }
    @Override
    public void onResume() {
        super.onResume();
//        Log.d("!!Third:", "1");
//        VocabBoostDatabaseHelper db = new VocabBoostDatabaseHelper(context);
//        Log.d("!!Second:", "2");
//        SQLiteDatabase mydb = db.getReadableDatabase();
//        Cursor res = mydb.rawQuery("SELECT  * FROM " + tableName+" WHERE _id="+_id, null);
//        Log.d("!!Fifth:",Integer.toString(res.getCount()));
//        Log.d("****!!Fifth:","id"+_id);
//        Log.d("****!!Fifth:",tableName);
//        res.moveToFirst();
//        String str1 = res.getString(1);
//        String str2 = res.getString(2);
//        String str3 = res.getString(3);
//        Log.d("****!!Fifth:",str1);
//        Log.d("****!!Fifth:",str2);
//        Log.d("****!!Fifth:",str3);
//        Log.d("****!!Fifth:","id"+_id);
//
//        TextView wt = layout.findViewById(R.id.input_word);
//        TextView mt = layout.findViewById(R.id.input_meaning);
//        TextView st = layout.findViewById(R.id.input_sentence);
//        wt.setText(str1);
//        mt.setText(str2);
//        st.setText(str3);
//        mydb.close();
//        db.close();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_expand_word, container, false);
        TextView wt=layout.findViewById(R.id.input_word);
        TextView mt=layout.findViewById(R.id.input_meaning);
        TextView st=layout.findViewById(R.id.input_sentence);
        wt.setText(w);
        mt.setText(m);
        st.setText(s);
        ImageButton editBtn=layout.findViewById(R.id.button_edit);
        editBtn.setOnClickListener(this);
        Log.d("!!Second:","On");
        return(layout);
    }
    @Override
    public void onClick(View v) {
        Log.d("!!Second:","Click0");
        WordInsertionFromWordInsertion fragment=new WordInsertionFromWordInsertion();
        Log.d("!!Second:","Click1");
        fragment.set(w,m,s,tableName,_id);
        Log.d("!!Second:","Click2");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}