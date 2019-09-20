package com.example.xonicwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button importwallet;
    private Button creatwallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Moved on Activity
            importwallet = (Button) findViewById(R.id.importwallet) ;
            creatwallet = (Button) findViewById(R.id.creatwallet) ;

            importwallet.setOnClickListener(new View.OnClickListener(){
                                                @Override
                                                public void onClick(View View){
                                                    Intent intent = new Intent(MainActivity.this, ImportWallet.class );
                                                    startActivity(intent);
                                                }
                                            }
            );

        creatwallet.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View View){
                                                 Intent intent = new Intent(MainActivity.this, CreateWallet.class );
                                                 startActivity(intent);
                                            }
                                        }
        );
    }
}
