package com.example.depression_diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ListView listView = (ListView) findViewById(R.id.listView);
        ListViewAdapter adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences_list = getSharedPreferences("total_list",MODE_PRIVATE);
        Map<String,?> totalValue = sharedPreferences_list.getAll();

        for(Map.Entry<String,?> entry : totalValue.entrySet()) {
            SharedPreferences sharedPreferences = getSharedPreferences(entry.getKey(),MODE_PRIVATE);
            String time = sharedPreferences.getString("time","");
            String title = sharedPreferences.getString("title","");
            String emotion = sharedPreferences.getString("emotion","");
            if (time!=""){
                if (emotion=="1"){
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.e1), title, time);
                }
                else if (emotion=="2"){
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.e2), title, time);
                }
                else if (emotion=="3"){
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.e3), title, time);
                }
                else if (emotion=="4"){
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.e4), title, time);
                }
                else if (emotion=="5"){
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.e5), title, time);
                }
                else if (emotion=="6"){
                    adapter.addItem(ContextCompat.getDrawable(this, R.drawable.e6), title, time);
                }
            }
        }

        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListViewItem selected_item = (ListViewItem) parent.getItemAtPosition(position);

                String descStr = selected_item.getDesc();

                SharedPreferences sharedPreferences = getSharedPreferences("input_title",MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("file_name",descStr);

                editor.commit();

                Intent intent = new Intent(MainActivity.this, MainActivity5.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onClick_add(View v){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
        finish();
    }

    public void onClick_setting(View v) {
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
        finish();
    }

}