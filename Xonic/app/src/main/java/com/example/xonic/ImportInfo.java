package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.List;

import io.contentos.android.sdk.Wallet;

public class ImportInfo extends AppCompatActivity {
    Button back, nextinfo;
    TextView importprivate, showbalanceid;
    EditText editText;
    private static final String keyStorePassword = "my password";
    private static File getKeyStoreFile() {
        File file = null;
        try {
            file = Files.createTempDirectory("keystoreTestDir").resolve("keystoreFile").toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_info);
        back = (Button) findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){ Intent intent = new Intent( ImportInfo.this, InfoAccount.class );
                startActivity(intent);
            }
        });
        showbalanceid = (TextView) findViewById(R.id.showbalanceid) ;
        editText = (EditText) findViewById(R.id.editText);
        nextinfo = (Button) findViewById(R.id.nextinfo);
        nextinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String USERNAME = intent.getStringExtra(InfoAccount.USER);
                String privatekey = editText.getText().toString();
                Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
                wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
                //wallet.addKey(USERNAME,privateKey);
                // Intent intent1 = new Intent( ImportInfo.this, MainAccount.class );
                // startActivity(intent1);
                /*
                String privateco = wallet.getKey(USERNAME);
                if(privateco == null){
                    Toast.makeText(ImportInfo.this, "TIÊU RÔI!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ImportInfo.this, "CÁI GÌ ĐÂY!", Toast.LENGTH_SHORT).show();
                }
                 */
               // wallet.addKey(USERNAME,privatekey);
                List<String> accounts = wallet.getAccounts();
                for (String name: accounts) {
                    String privateKey = wallet.getKey(name);
                    showbalanceid.setText(privateKey);
                }
                //long balance = wallet.getAccountByName("quyphankute").getInfo().getCoin().getValue();
                //double balance2 = (double) balance/1000000;
                /*
                double balance1 = (double) balance; //0.212221
                double a = 0.000001;
                double balance3 = balance1 - a*1000000;
                double balance4 = balance3/1000000;
                 */
                //showbalanceid.setText(String.valueOf(balance2));

            }
        });
        //setByUser();

    }
    /*
   public void setByUser(){
        Intent intent = getIntent();
        String USERNAME = intent.getStringExtra(InfoAccount.USER);
        editText.setText(USERNAME);
    }
     */
}
