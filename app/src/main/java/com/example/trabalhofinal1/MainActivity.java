package com.example.trabalhofinal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void game1(View view){
        Intent intent = new Intent(this, GameOne.class);
        startActivity(intent);
    }

    public void game2(View view){
        Intent intent = new Intent(this, GameTwo.class);
        startActivity(intent);
    }

    public void game3(View view){
        Intent intent = new Intent(this, GameThree.class);
        startActivity(intent);
    }
}
