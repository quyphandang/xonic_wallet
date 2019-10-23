package com.example.menuex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button walletid,exchangeid,stakeid,settingid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        walletid = (Button) findViewById(R.id.walletid) ;
        exchangeid = (Button) findViewById(R.id.exchangeid) ;
        stakeid = (Button) findViewById(R.id.stakeid) ;
        settingid = (Button) findViewById(R.id.settingid) ;
        walletid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){
                Intent intent = new Intent( getApplicationContext(), testmenu.class );
                startActivity(intent);
            }
        });
    }

}
