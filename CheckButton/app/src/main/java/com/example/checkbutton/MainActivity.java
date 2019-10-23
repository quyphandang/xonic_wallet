package com.example.checkbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String USERNAME_PATTERN = "^[a-z0-9]{6,16}$";
                        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
                        String UserName = editText.getText().toString();
                        Matcher matcher = pattern.matcher(UserName);
                        boolean match = matcher.matches();
                        if (match == true) {
                            editText.setTextColor(0xFF2D0775);
                            // open info account
                            button.setTextColor(0xFFFFFFFF);
                            Toast.makeText(MainActivity.this, "Đúng !", Toast.LENGTH_SHORT).show();
                        } else{
                            editText.setTextColor(0xFFF10303);
                            button.setTextColor(0xFF2D0775);
                            //button.setBackgroundResource(R.drawable.bordered);
                            Toast.makeText(MainActivity.this, "Sai mẹ rồi!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
    }
}
