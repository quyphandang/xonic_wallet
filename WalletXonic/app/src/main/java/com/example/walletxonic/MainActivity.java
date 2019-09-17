package com.example.walletxonic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button importwallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//Moved on Activity
        importwallet = (Button) findViewById(R.id.importwallet) ;
        importwallet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){
                Intent intent = new Intent(MainActivity.this, ImportWallet.class );
                startActivity();
            }
                                        }
        );
    }
}
