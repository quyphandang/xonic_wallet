package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrivateAndSucure extends AppCompatActivity {
    Button creatwallet;
    Button importwallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_and_sucure);

        creatwallet = (Button) findViewById(R.id.creatwallet) ;

        //creatwallet.setPadding(20,20,20,20);
        creatwallet.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View View){
                                               Intent intent = new Intent(PrivateAndSucure.this, AddUseName.class );
                                               startActivity(intent);
                                           }
                                       }
        );
        importwallet = (Button) findViewById(R.id.importwallet) ;

        //creatwallet.setPadding(20,20,20,20);
        importwallet.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View View){
                                                Intent intent = new Intent(PrivateAndSucure.this, ImportPrivateMnemonic.class );
                                                startActivity(intent);
                                            }
                                        }
        );
    }
}
