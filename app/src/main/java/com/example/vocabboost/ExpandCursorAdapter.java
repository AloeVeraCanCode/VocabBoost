package com.example.vocabboost;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ExpandCursorAdapter extends CursorAdapter{
    String tableName;
    public ExpandCursorAdapter(Context context, Cursor cursor, String t) {
        super(context, cursor,0);
        tableName=t;
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_word_list, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView word= (TextView) view.findViewById(R.id.text_word);
        TextView meaning= (TextView) view.findViewById(R.id.text_meaning);
        TextView sentence= (TextView) view.findViewById(R.id.text_sentence);
        String body = cursor.getString(cursor.getColumnIndexOrThrow("WORD"));
        word.setText(body);
         body = cursor.getString(cursor.getColumnIndexOrThrow("MEANING"));
        meaning.setText(body);
         body = cursor.getString(cursor.getColumnIndexOrThrow("SENTENCE"));
        sentence.setText(body);
    }
}
