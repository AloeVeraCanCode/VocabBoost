package com.example.vocabboost;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultCursorAdapter  extends CursorAdapter {
    String tableName;
    public ResultCursorAdapter(Context context, Cursor cursor, String t) {
        super(context, cursor,0);
        tableName=t;
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View layout=LayoutInflater.from(context).inflate(R.layout.list_result, parent, false);

        return(layout);
    }
    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if(tableName.equals("_WordleAttempts_"))wordle(cursor,view);
        else quiz(cursor,view);
    }

    private void quiz(Cursor cursor,View view) {
        String s="Your score was: ";
        TextView text= (TextView) view.findViewById(R.id.score);
        TextView date= (TextView) view.findViewById(R.id.date);
        String body = cursor.getString(cursor.getColumnIndexOrThrow("DISPLAY"));
        text.setText(s+body);
        body = cursor.getString(cursor.getColumnIndexOrThrow("TIME"));
        date.setText(body);
    }

    private void wordle(Cursor cursor,View view) {
        String s="Your word was: ";
        TextView text= (TextView) view.findViewById(R.id.score);
        TextView date= (TextView) view.findViewById(R.id.date);
        LinearLayout l=(LinearLayout) view.findViewById(R.id.scoreframe) ;
        String body = cursor.getString(cursor.getColumnIndexOrThrow("WORD"));
        String color=cursor.getString(cursor.getColumnIndexOrThrow("COLOR"));
        color=color.replaceAll("\\s","");
        if(color.equals("G")){
            l.setBackgroundColor(Color.parseColor("#49FF33"));}
        else if(color.equals("R"))
            l.setBackgroundColor(Color.parseColor("#FF8A8A"));
        text.setText(s+body);
        body = cursor.getString(cursor.getColumnIndexOrThrow("TIME"));
        date.setText(body);
    }
}

