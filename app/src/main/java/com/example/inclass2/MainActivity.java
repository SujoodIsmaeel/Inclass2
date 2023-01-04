package com.example.inclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private EditText ed1,ed2,ed3;
    private Button  button,button2;
    private  boolean save= false;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String title="title";
    public static  final String NAME="NAME";
    public static final String page="page";
    private boolean FLAG=true;
    private Switch switch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPrefs();
        checkData();

    }

    private void setupViews() {
        ed2= findViewById(R.id.ed2);
        ed3= findViewById(R.id.ed3);
        button2=findViewById(R.id.button2);
        switch1=findViewById(R.id.switch1);
       switch1.setTextColor(Color.GREEN);
        switch1.setChecked(true);
        button=findViewById(R.id.button);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save= true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

            }
        });


    }

    private void checkData() {
        boolean f= prefs.getBoolean("FLAG",false);
        if(f) {
            String Title = prefs.getString(title, "");
            String name = prefs.getString(NAME, "");
            String Page = prefs.getString(page, "");
            switch1.setChecked(true);
            ed1.setText(title);
            ed2.setText(name);
            ed3.setText(page);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!save){
            String Title=ed1.getText().toString().trim();
            String name=ed2.getText().toString().trim();
            String Page=ed3.getText().toString().trim();

            editor.putString(Title,title);
            editor.putString(NAME,name);
            editor.putString(Page,page);
            editor.putBoolean("FLAG",FLAG);
            switch1.setChecked(true);
            editor.commit();

        }
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor= prefs.edit();

    }
}