package com.example.setpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class SavePass {
    public static int countPass = 0;
    public static String pass = "";
    public static int next = 1;
    public static String checkPass = "";
}

public class DatPass extends AppCompatActivity {
    TextView  number0, number1, number2, number3, number4, number5, number6, number7, number8, number9;
    ImageView removenumber, no1, no2, no3, no4, no5, no6;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_pass);

        no1 = (ImageView) findViewById(R.id.no1);
        no2 = (ImageView) findViewById(R.id.no2);
        no3 = (ImageView) findViewById(R.id.no3);
        no4 = (ImageView) findViewById(R.id.no4);
        no5 = (ImageView) findViewById(R.id.no5);
        no6 = (ImageView) findViewById(R.id.no6);

        number0 = (TextView) findViewById(R.id.number0);
        number1 = (TextView) findViewById(R.id.number1);
        number2 = (TextView) findViewById(R.id.number2);
        number3 = (TextView) findViewById(R.id.number3);
        number4 = (TextView) findViewById(R.id.number4);
        number5 = (TextView) findViewById(R.id.number5);
        number6 = (TextView) findViewById(R.id.number6);
        number7 = (TextView) findViewById(R.id.number7);
        number8 = (TextView) findViewById(R.id.number8);
        number9 = (TextView) findViewById(R.id.number9);
        removenumber = (ImageView) findViewById(R.id.removenumber);

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "0";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "1";
                check(SavePass.countPass);

              //  no1.setImageResource(R.drawable.ic_full_24dp);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "2";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "3";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "4";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "5";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "6";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "7";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "8";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "9";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        removenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SavePass.countPass = SavePass.countPass - 1;
                    SavePass.pass = SavePass.pass.substring(0, SavePass.countPass);
                    check(SavePass.countPass);
                } catch(Exception e) {
                    Toast.makeText( DatPass.this, "Điền pass vào! " + SavePass.pass, Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void check(int count){
        if (count == 1 ){
            //Toast.makeText( DatPass.this, "1", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_no_24dp);
            no3.setImageResource(R.drawable.ic_no_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 2) {
            //Toast.makeText( DatPass.this, "2", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_no_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 3){
            //Toast.makeText( DatPass.this, "3", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 4){
            //Toast.makeText( DatPass.this, "4", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_full_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 5){
            //Toast.makeText( DatPass.this, "5", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_full_24dp);
            no5.setImageResource(R.drawable.ic_full_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 6){
            //Toast.makeText( DatPass.this, "6", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_full_24dp);
            no5.setImageResource(R.drawable.ic_full_24dp);
            no6.setImageResource(R.drawable.ic_full_24dp);

            if (SavePass.next == 1){
                SavePass.checkPass = SavePass.pass;
                SavePass.pass = "";
                SavePass.next = 2;
                SavePass.countPass = 0;
                Toast.makeText( DatPass.this, "Xác nhận lại passwork", Toast.LENGTH_SHORT).show();
                no1.setImageResource(R.drawable.ic_no_24dp);
                no2.setImageResource(R.drawable.ic_no_24dp);
                no3.setImageResource(R.drawable.ic_no_24dp);
                no4.setImageResource(R.drawable.ic_no_24dp);
                no5.setImageResource(R.drawable.ic_no_24dp);
                no6.setImageResource(R.drawable.ic_no_24dp);
            }else{
                if (SavePass.checkPass.equals(SavePass.pass)){
                    Toast.makeText( DatPass.this, "Đúng rồi", Toast.LENGTH_SHORT).show();
                    SavePass.pass = "";
                    SavePass.countPass = 0;
                    sharedPreferences = getSharedPreferences("pass", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("password", SavePass.checkPass);
                    editor.commit();
                    Intent intent = new Intent(DatPass.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText( DatPass.this, "Sai rồi", Toast.LENGTH_SHORT).show();
                    SavePass.pass = "";
                    SavePass.countPass = 0;
                    no1.setImageResource(R.drawable.ic_no_24dp);
                    no2.setImageResource(R.drawable.ic_no_24dp);
                    no3.setImageResource(R.drawable.ic_no_24dp);
                    no4.setImageResource(R.drawable.ic_no_24dp);
                    no5.setImageResource(R.drawable.ic_no_24dp);
                    no6.setImageResource(R.drawable.ic_no_24dp);
                }
            }

        } else if(count == 0){
            Toast.makeText( DatPass.this, "0", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_no_24dp);
            no2.setImageResource(R.drawable.ic_no_24dp);
            no3.setImageResource(R.drawable.ic_no_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else {
            Toast.makeText( DatPass.this, "Quá nhiều số", Toast.LENGTH_SHORT).show();
        }
    }
}
