package com.example.vocabboost;

import android.app.Fragment;
import android.app.FragmentTransaction;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vocabboost.Common.VocabBoostDatabaseHelper;
import com.example.vocabboost.Quiz.QuizFragment;
import com.example.vocabboost.Wordle.WordleFragment;

public class MainActivity extends Activity {

    private String[] drawer_elements;
    private ListView drawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_elements = getResources().getStringArray(R.array.drawer_elements);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, drawer_elements));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
//        createTables("_WordleAttempts_");
//                createTables("_quizscore_");
        //When we need to delete the dataBase////////////////////////////
         //deleteDatabase("vocabboost1");///////////////////////////////
        // /////////////////////////////////////////////////////////////
    }
    public void createTables(String tableName)
    {
        VocabBoostDatabaseHelper db=new VocabBoostDatabaseHelper(this);
        SQLiteDatabase mydb=db.getWritableDatabase();
            try {
                mydb.execSQL("CREATE TABLE "
                        + tableName
                        + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "DISPLAY TEXT, "
                        + "WORD TEXT, "
                        + "COLOR TEXT, "
                        + "TIME TEXT); ");
                Log.d("First:", "Successfully created "+tableName);
            }
            catch (Exception e){Log.d("First:", "Fail");return;}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert:
                Log.d("success","Clicked successfully!");
        }
        return(true);
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    };
    private void selectItem(int position) {
        Fragment fragment=new Fragment();
        switch(position) {
            case 0:
                HomeFragment f= new HomeFragment();
                f.set(this);
                fragment=f;
                break;
            case 1:
                fragment = new ShowFragment();
                break;
            case 2:
                QuizFragment frag= new QuizFragment();
                fragment=frag;
                frag.set(this);
                break;
            case 3:
                fragment  = new WordleFragment();
                break;
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}