package com.example.depression_diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sharedPreferences_tf = getSharedPreferences("password",MODE_PRIVATE);

        Switch switch1 = findViewById(R.id.switch1);

        if (sharedPreferences_tf.getBoolean("pw_tf",false)){
            switch1.setChecked(true);
        }

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setTitle("비밀번호 설정");
                    builder.setMessage("비밀번호를 설정해주세요.");

                    final EditText password = new EditText(MainActivity3.this);
                    password.setInputType(0x00000002);
                    password.setGravity(Gravity.CENTER);
                    password.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
                    builder.setView(password);

                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sharedPreferences_change = getSharedPreferences("password",MODE_PRIVATE);
                            SharedPreferences.Editor editor_change = sharedPreferences_change.edit();

                            editor_change.putString("pw_number",password.getText().toString());

                            editor_change.commit();

                            SharedPreferences sharedPreferences_tf = getSharedPreferences("password",MODE_PRIVATE);
                            SharedPreferences.Editor editor_tf = sharedPreferences_tf.edit();

                            editor_tf.putBoolean("pw_tf",isChecked);

                            editor_tf.commit();
                        }
                    });

                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch1.setChecked(false);
                        }
                    });
                    builder.show();
                }

                else {
                    SharedPreferences sharedPreferences_tf = getSharedPreferences("password",MODE_PRIVATE);
                    SharedPreferences.Editor editor_tf = sharedPreferences_tf.edit();

                    editor_tf.putBoolean("pw_tf",isChecked);

                    editor_tf.commit();
                }
            }
        });
    }

    public void onClick_menu(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}