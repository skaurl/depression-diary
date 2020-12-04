package com.example.depression_diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText editTextNumberPassword = findViewById(R.id.editTextNumberPassword);
        editTextNumberPassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        SharedPreferences sharedPreferences = getSharedPreferences("password",MODE_PRIVATE);

        if (sharedPreferences.getBoolean("pw_tf",false) != true) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void  onClick_pw(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("password",MODE_PRIVATE);
        String pw = sharedPreferences.getString("pw_number","");

        EditText editTextNumberPassword = findViewById(R.id.editTextNumberPassword);

        if (editTextNumberPassword.getText().toString().equals(pw)) {

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}