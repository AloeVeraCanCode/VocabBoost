package com.example.vocabboost;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vocabboost.Common.VocabBoostDatabaseHelper;


public class ShowFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    public Cursor cursor;
    public Context context;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Inside","here");
        View layout= inflater.inflate(R.layout.fragment_show, container, false);
        Log.d("Inside","here1");
        ImageButton showButton = (ImageButton)layout.findViewById(R.id.button_catagory);
        Log.d("Inside","here2");
        showButton.setOnClickListener(this);
        Log.d("Inside","here3");
        VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(context);
        SQLiteDatabase mydb=db.getReadableDatabase();
        cursor=mydb.rawQuery("SELECT  * FROM CATAGORY", null);
        ListView lv=layout.findViewById(R.id.list_catagory);
        MyCursorAdapter mca=new MyCursorAdapter(context,cursor);
        lv.setAdapter(mca);
        lv.setOnItemClickListener(this);
        return(layout);
    }
    @Override
    public void onClick(View v) {
        Fragment fragment=new Fragment();
        switch (v.getId()) {
            case R.id.button_catagory:
                fragment=new CatagoryInsertFragment();
                break;
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(context);
        Log.d("MyName:","Here1");
        SQLiteDatabase mydb=db.getReadableDatabase();
        Log.d("MyName:","Here2");
        TextView t=(TextView)view.findViewById(R.id.text_catagory);
        Log.d("MyName:",Integer.toString(position));
        String tableName=t.getText().toString();
        Log.d("MyName:",tableName);
        try {
        Cursor cursor=mydb.rawQuery("SELECT  COUNT(*) FROM "+ tableName, null);
            Log.d("MyName:","Not  gandu");
            Log.d("MyName:",Integer.toString(cursor.getCount()));
            //Write Today
            ExpandCategory fragment=new ExpandCategory();
            fragment.tableName=tableName;
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
        mydb.close();
        db.close();
    }
}