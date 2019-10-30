package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.xonic.AddUseName.USERNAME;

public class InfoAccount extends AppCompatActivity {
    Button back,nextid;
    TextView usernameid, privatekeyid, copyid ;
    ClipboardManager clipboardManager;
    public static final String USERNAME = "USERNAME";
    public static final String PRIVATEKEY = "PRIVATEKEY";
    //public static final String BUNDLE = "BUNDLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);
        copyid = (TextView) findViewById(R.id.copyid);
        nextid = (Button) findViewById(R.id.nextid);
        //back button.
        back = (Button) findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){ Intent intent = new Intent(InfoAccount.this, AddUseName.class );
            startActivity(intent);
            }
        });
        // end back
        //show info
        usernameid = (TextView) findViewById(R.id.usernameid) ;
        privatekeyid = (TextView) findViewById(R.id.privatekeyid) ;
        setDataByExtras();
        //byExtrasUser(USERNAME);
        //end info
        //copy
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        copyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            String getstring = privatekeyid.getText().toString();
            ClipData clipData = ClipData.newPlainText("PRIVATE",getstring);
           // Toast.makeText(getApplicationContext(), "Clipboard data has been cleared.", Toast.LENGTH_LONG).show();
           //Toast toast=Toast.makeText(InfoAccount.this, "Copy Succes",   Toast.LENGTH_SHORT).show();
           clipboardManager.setPrimaryClip(clipData);
           Toast.makeText(InfoAccount.this, "Copy Succes",   Toast.LENGTH_SHORT).show();
            }
        });
        //next

        nextid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = usernameid.getText().toString();
                String privateKey = privatekeyid.getText().toString();
                byExtrasUser(userName,privateKey);
            }
        });
    }
    public void setDataByExtras(){

        Intent intent = getIntent();
        String USERNAME = intent.getStringExtra(AddUseName.USERNAME);
        String PRIVATEKEY = intent.getStringExtra(AddUseName.PRIVATEKEY);
        usernameid.setText(USERNAME);
        privatekeyid.setText(PRIVATEKEY);
    }

    public void byExtrasUser(String userName, String privateKey){
        Intent intent = new Intent(InfoAccount.this, ImportInfo.class);
        intent.putExtra(USERNAME,userName);
        intent.putExtra(PRIVATEKEY,privateKey);
        startActivity(intent);
    }
}
