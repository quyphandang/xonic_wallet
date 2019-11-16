package com.example.xonic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.contentos.android.sdk.Wallet;
import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

import static com.example.xonic.MainAccount.wallet;

public class ImportMnemonic extends AppCompatActivity {
    EditText mnemonic,accountname;

    Button nextinfo,back;
    public static final String USERNAME = "USERNAME";
    public static final String PRIVATEKEY = "PRIVATEKEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
                //Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
                mnemonic = (EditText) findViewById(R.id.mnemonic);
                accountname = (EditText) findViewById(R.id.accountname);
                String mnemoNic = mnemonic.getText().toString();
                String userName = accountname.getText().toString();
                try {
                    String inpublicKey = WIF.fromPublicKey(Key.publicKeyOf(Key.generateFromMnemonic(mnemoNic)));
                    //WIF.fromPrivateKey(Key.publicKeyOf(Key.generateFromMnemonic(mnemoNic)))

                         String privateKey   =   WIF.fromPrivateKey(Key.generateFromMnemonic(mnemoNic));

                    try {
                        String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(userName).getInfo().getPublicKey());
                        if (inpublicKey.equals(inpublicKeyAccount)){
                            //Toast.makeText(getApplicationContext(), a , Toast.LENGTH_SHORT).show();
                            byExtras(userName,privateKey);
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
    public void byExtras(String userName, String privateKey){
        //Intent intent = new Intent(AddUseName.this, InfoAccount.class);
        Intent intent = new Intent(getApplicationContext(), MainAccount.class);
        intent.putExtra(USERNAME,userName);
        intent.putExtra(PRIVATEKEY,privateKey);
        startActivity(intent);
    }
}
