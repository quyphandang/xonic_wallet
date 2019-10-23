package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.contentos.android.sdk.Wallet;
import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

public class ImportMnemonic extends AppCompatActivity {
    EditText mnemonic,accountname;

    Button nextinfo,back;
    public static final String USERNAME = "USERNAME";
    public static final String MNEMONIC = "MNEMONIC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_mnemonic);
        back = (Button) findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){ Intent intent = new Intent( getApplicationContext(), ImportPrivateMnemonic.class );
                startActivity(intent);
            }
        });
        nextinfo = (Button) findViewById(R.id.nextinfo);
        nextinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
                mnemonic = (EditText) findViewById(R.id.mnemonic);
                accountname = (EditText) findViewById(R.id.accountname);
                String mnemoNic = mnemonic.getText().toString();
                String userName = accountname.getText().toString();
                try {
                    String inpublicKey = WIF.fromPublicKey(Key.publicKeyOf(Key.generateFromMnemonic(mnemoNic)));
                    try {
                        String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(userName).getInfo().getPublicKey());
                        if (inpublicKey.equals(inpublicKeyAccount)){
                            Toast.makeText(getApplicationContext(), "Login Success!" , Toast.LENGTH_SHORT).show();
                            //byExtras(userName,mnemoNic);
                        }else{
                            Toast.makeText(getApplicationContext(), "Account and Mnemonic did not match. Try Again!" , Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "User Name is incorrect" , Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Mnemonic is incorrect" , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void byExtras(String userName, String mnemoNic){
        //Intent intent = new Intent(AddUseName.this, InfoAccount.class);
        Intent intent = new Intent(getApplicationContext(), MainAccount.class);
        intent.putExtra(USERNAME,userName);
        intent.putExtra(MNEMONIC,mnemoNic);
        startActivity(intent);
    }
}
