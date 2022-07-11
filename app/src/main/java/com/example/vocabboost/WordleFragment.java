package com.example.vocabboost;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;


public class WordleFragment extends Fragment implements View.OnClickListener{

    public Context context;
    public ImageButton hard,easy;
    public  View layout;
    public String randomWord;
    public int key;
    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        context=c;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        key=0;
        randomWord="";
        layout= inflater.inflate(R.layout.fragment_wordle, container, false);
        hard=(ImageButton)layout.findViewById(R.id.wordle_hard);
        easy=(ImageButton)layout.findViewById(R.id.wordle_easy);
        hard.setOnClickListener(this);
        easy.setOnClickListener(this);
        return(layout);
    }

    @Override
    public void onClick(View v) {
        String word="";
        if(v.getId()==R.id.wordle_hard)
        {
            if(key==1)return;
            key=1;
            callAPIAndStartActivity();
            Log.d("ninth",word);
        }
        else{
            try{
            QuizObject q=new QuizObject(1,context);
            startWordleActivity(q.question[0].get(q.answerNo[0].get(0)+1));}
            catch(Exception e){
                Toast.makeText(context,"Something went wrong :(",Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void callAPIAndStartActivity()
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://random-word-api.herokuapp.com/word?length=";
        Random rand = new Random();
        int len=5+rand.nextInt(5);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+Integer.toString(len),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        key=0;
                        randomWord=response;
                        response=response.substring(2,response.length()-2);
                        startWordleActivity(response);
                        Log.d("11th",response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("11th","FAIL");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void startWordleActivity(String word) {
        Intent intent = new Intent(context, WordleActivity.class);
        intent.putExtra("word",word);
        Log.d("ninth",word);
        try{
            startActivity(intent);}
        catch (Exception e){Log.d("Seventh", "activity fail");}
    }
}