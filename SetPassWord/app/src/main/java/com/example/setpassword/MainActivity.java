package com.example.setpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch sw;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch sw = (Switch) findViewById(R.id.switch1);
        sharedPreferences = getSharedPreferences("pass", MODE_PRIVATE);
        String checkpass = sharedPreferences.getString("password","");
        int lengthcheck = checkpass.length();
       // Toast.makeText( MainActivity.this, String.valueOf(lengthcheck), Toast.LENGTH_SHORT).show();
        if (lengthcheck == 6){
            sw.setChecked(true);
        }
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                   // boolean isChecKed = false;
                    sw.setChecked(false);
                    SavePass.checkPass = "";
                    SavePass.pass = "";
                    SavePass.next = 1;
                    SavePass.countPass = 0;
                    Intent intent = new Intent(MainActivity.this, DatPass.class);
                    startActivity(intent);
                } else {
                    //Toast.makeText( MainActivity.this, "Please enter Amount!", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("password");
                    editor.commit();
                }
            }
        });

    }
}
