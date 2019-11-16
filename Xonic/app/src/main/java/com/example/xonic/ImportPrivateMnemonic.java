package com.example.xonic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ImportPrivateMnemonic extends AppCompatActivity {
    Button back,importprivateid,mnemonic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_import_private_mnemonic);
        back = (Button) findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){ Intent intent = new Intent(ImportPrivateMnemonic.this, MainActivity.class );
                startActivity(intent);
            }
        });
        importprivateid = (Button) findViewById(R.id.importprivateid) ;
        importprivateid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImportPrivateMnemonic.this, ImportPrivateKey.class );
                startActivity(intent);
            }
        });

        mnemonic = (Button) findViewById(R.id.mnemonic) ;
        mnemonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImportPrivateMnemonic.this, ImportMnemonic.class );
                startActivity(intent);
            }
        });
    }
}
