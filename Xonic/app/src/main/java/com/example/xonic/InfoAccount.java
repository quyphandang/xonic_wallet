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
    Button back, copyid, nextid;
    TextView usernameid, privatekeyid, publickey ;
    ClipboardManager clipboardManager;
    public static final String USERNAME = "USERNAME";
    public static final String PRIVATEKEY = "PRIVATEKEY";
    //public static final String BUNDLE = "BUNDLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);
        copyid = (Button) findViewById(R.id.copyid);
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
        publickey = (TextView) findViewById(R.id.publickey) ;
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
                //String UserAcc = usernameid.getText().toString();
                //Intent intent = new Intent(InfoAccount.this, ImportInfo.class );
               // intent.putExtra(USER,UserAcc);
                //startActivity(intent);

                //byExtrasUser(UserAcc);
                //Intent intent = new Intent(getApplicationContext(), ImportInfo.class);
                //startActivity(intent);
                String userName = usernameid.getText().toString();
                String privateKey = privatekeyid.getText().toString();
                byExtrasUser(userName,privateKey);
            }
        });
    }
    public void setDataByExtras(){

        Intent intent = getIntent();
        //Bundle bundle = intent.getBundleExtra(AddUseName.BUNDLE);
        //String USERNAME = bundle.getString(AddUseName.USERNAME);
        //String PRIVATEKEY = bundle.getString(AddUseName.PRIVATEKEY);
        //String PUBLICKEY = bundle.getString(AddUseName.PUBLICKEY);
        String USERNAME = intent.getStringExtra(AddUseName.USERNAME);
        String PRIVATEKEY = intent.getStringExtra(AddUseName.PRIVATEKEY);
        String PUBLICKEY = intent.getStringExtra(AddUseName.PUBLICKEY);
        /*
        Bundle bundle = getIntent().getExtras();
        //String USERNAME1 = bundle.getString(USERNAME,AddUseName.USERNAME);
        //String USERNAME = bundle.getString(AddUseName.USERNAME);
        //String PRIVATEKEY = bundle.getString(AddUseName.PRIVATEKEY);
        //String PUBLICKEY = bundle.getString(AddUseName.PUBLICKEY);
        */
        usernameid.setText(USERNAME);
        privatekeyid.setText(PRIVATEKEY);
        publickey.setText(PUBLICKEY);
    }

    public void byExtrasUser(String userName, String privateKey){
        Intent intent = new Intent(InfoAccount.this, ImportInfo.class);
        //Bundle bundle = new Bundle();
        //Intent intent = new Intent(getApplicationContext(), ImportInfo.class);
        //bundle.putString(USERNAME,USERNAME);
        //bundle.putString(PRIVATEKEY,PRIVATEKEY);
        //intent.putExtra(BUNDLE,bundle);
        intent.putExtra(USERNAME,userName);
        intent.putExtra(PRIVATEKEY,privateKey);
        startActivity(intent);
    }
}
