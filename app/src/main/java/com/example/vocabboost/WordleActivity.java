package com.example.vocabboost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WordleActivity extends Activity  implements View.OnClickListener{

    public RelativeLayout relativeLayout,wordle;
    public  int height,width,l;
    public  TextView showWord;
    int row,col;
    Button text[][];
    String trial,actualWord;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        row=col=0;trial="";actualWord="";
        actualWord=getIntent().getStringExtra("word");
        Log.d("12th",actualWord);
        actualWord=actualWord.replaceAll("\\s","");
        l=actualWord.length();
        Log.d("12th","my"+l);
        actualWord=actualWord.toUpperCase();
        setContentView(R.layout.activity_wordle);
        relativeLayout = (RelativeLayout)findViewById(R.id.keyboard);
        wordle = (RelativeLayout)findViewById(R.id.wordle);
        showWord=(TextView)findViewById(R.id.wordle_text);
        relativeLayout.setBackgroundColor(R.color.purple_200);
        createKeyboard();
        createWordle(l);
    }
    public void createKeyboard()
    {
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<7;j++)
            {
                if(7*i+j>=26)continue;
                char c='A';
                Button key = new Button(this);
                key.setOnClickListener(this);
                key.setText(Character.toString((char)(c+7*i+j)));
                key.setId(13*i+j);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
                params.topMargin=140*i+10;
                params.leftMargin=150*j+10;
                //params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                relativeLayout.addView(key, params);
            }
        }

        Button key = new Button(this);
        key.setText("Enter");
        key.setOnClickListener(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin=580;
        params.leftMargin=10;
        Log.d("ninth","There3");
        relativeLayout.addView(key, params);
        key = new Button(this);
        key.setText("<BackSpace");
        Log.d("ninth","There2");
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.topMargin=580;
        param.rightMargin=10;
        key.setOnClickListener(this);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        relativeLayout.addView(key, param);
    }
    void createWordle(int len)
    {
        text=new Button[5][len];
        int w=400/len*len;
        int d,l;
        d=w/len;
        l=1;
        d-=2*l;
        wordle.getLayoutParams().height = dpToPx(5*(d+2*l));
        wordle.getLayoutParams().width = dpToPx(w);
        int i,j;
        for(i=0;i<5;i++)
        {
            for(j=0;j<len;j++)
            {
                char c='A';
                Button key = new Button(this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dpToPx(d),dpToPx(d));
                params.topMargin=dpToPx((d+2*l)*i+l);
                params.leftMargin=dpToPx((d+2*l)*j+l);
                wordle.addView(key, params);
                text[i][j]=key;
            }
        }
    }
    @Override
    public void onClick(View v) {
        Button b =(Button)v;
        String cur= b.getText().toString();
        if(cur.equals("Enter"))
        {
            if(l==col){check();row++;col=0;trial="";}
        }
        else if(cur.equals("<BackSpace"))
        {
            if(col==0){return;}
            else{ text[row][--col].setText("");trial=trial.substring(0,trial.length()-1);}
        }
        else
        {
            if(col<l&&row<5) {
                text[row][col++].setText(cur);
                trial=trial+cur;
            }
        }
    }
    public int dpToPx(int dp) {
        float density = getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) (dp * density+0.5));
    }
    @SuppressLint("ResourceAsColor")
    public void check(){
        Log.d("12th",actualWord);
        Log.d("12th",trial);
        int j,matched=0;
        for(j=0;j<l;j++)
        {
            if(trial.charAt(j)==actualWord.charAt(j))
            {   matched++;
                text[row][j].setBackgroundColor(Color.parseColor( "#24B413"));
            }
            else if(actualWord.indexOf(trial.charAt(j))!=-1)
            {
                text[row][j].setBackgroundColor(Color.parseColor( "#DEF54D"));
            }
        }
        if(matched==l)
        {
            showWord.setText("You guessed it correct!!");row=100;
        }
        else if(row==4)
        {
            showWord.setText("Your word was "+actualWord);row=100;
        }
    }
}