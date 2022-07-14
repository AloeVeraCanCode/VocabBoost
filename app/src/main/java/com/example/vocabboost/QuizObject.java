package com.example.vocabboost;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vocabboost.Common.VocabBoostDatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class QuizObject implements Serializable {
    int totalPage;
    public ArrayList<String>[] question;
    public ArrayList<Integer>[] answerNo,response;
    public QuizObject(int a,Context c)
    {
        totalPage=a;
        ArrayList<String>[] q=new ArrayList[a];
        answerNo=new ArrayList[a];
        response=new ArrayList[a];
        question=q;
        for (int i = 0; i < a; i++)
        {
            question[i] = new ArrayList<String>();
            answerNo[i]=new ArrayList<Integer>();
            response[i]=new ArrayList<Integer>();
        }
        makeQuestions(c);
    }

    private void makeQuestions(Context context) {
        Log.d("Seventh", "HEY");
        try {
            VocabBoostDatabaseHelper db = new VocabBoostDatabaseHelper(context);
            SQLiteDatabase mydb = db.getReadableDatabase();
            Cursor cursor = mydb.rawQuery("SELECT  * FROM CATAGORY", null);
            ArrayList<String> totalQuestions = new ArrayList<String>();
            ArrayList<String> answer = new ArrayList<String>();
            int n = 0;
            while (cursor.moveToNext()) {
                Cursor table = mydb.rawQuery("SELECT  * FROM " + cursor.getString(1), null);
                while (table.moveToNext()) {
                    totalQuestions.add(table.getString(2));
                    n++;
                    answer.add(table.getString(1));
                }
            }
            for (String s : answer) {

                Log.d("Seventh", s);
            }
            mydb.close();db.close();
            Set<Integer> set=new LinkedHashSet<Integer>();
            Random rand = new Random();
            for(int i=0;i<totalPage;i++)
            {
                response[i].add(-1);
               int x= rand.nextInt(n);
               if(set.contains(x)){i--;continue;}
               else set.add(x);
                Set<Integer> optionSet=new LinkedHashSet<Integer>();
               int y=1+rand.nextInt(4);
               answerNo[i].add(y);
               add(totalQuestions.get(x),i);
               int z;
               for(z=1;z<=4;z++)
               {

                   if(z==y){
                       add(answer.get(x),i);
                   }
                   else{
                       int r=rand.nextInt(n);
                       if(answer.get(x)==answer.get(r)){z--;continue;}
                       add(answer.get(r),i);
                   }
               }
                Log.d("Seventh", "my"+n);
            }
        }
        catch (Exception e){ Log.d("!!!Seventh", "Fail");}
 }

    public void add(String s,int no)
    {
        question[no].add(s);
    }
}
