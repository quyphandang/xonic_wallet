package com.example.xonicwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ImportWallet extends AppCompatActivity {
    private TextView text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_wallet);

        text4 = (TextView) findViewById(R.id.text4) ;
        text4.setOnClickListener(new View.OnClickListener(){
                                     @Override
                                     public void onClick(View View){
                                         Intent intent = new Intent(ImportWallet.this, MainActivity.class );
                                         startActivity(intent);
                                     }
                                 }
        );
    }
}
