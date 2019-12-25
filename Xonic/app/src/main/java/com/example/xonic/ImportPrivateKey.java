package com.example.xonic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.contentos.android.sdk.Wallet;
import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

import static com.example.xonic.MainAccount.wallet;

public class ImportPrivateKey extends AppCompatActivity {
    EditText privatekey,accountname;
    Button nextinfo,back;
    private long mLastClickTime = 0;
    public static final String USERNAME = "USERNAME";
    public static final String PRIVATEKEY = "PRIVATEKEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_import_private_key);
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
                privatekey = (EditText) findViewById(R.id.privatekey);
                accountname = (EditText) findViewById(R.id.accountname);
                String privateKey = privatekey.getText().toString();
                String userName = accountname.getText().toString();
                try {
                    String inpublicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));
                    try {
                        String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(userName).getInfo().getPublicKey());
                        if (inpublicKey.equals(inpublicKeyAccount)){
                            Toast.makeText(getApplicationContext(), "Login Success!" , Toast.LENGTH_SHORT).show();
                            byExtras(userName,privateKey);
                        }else{
                            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                                return;
                            }
                            mLastClickTime = SystemClock.elapsedRealtime();
                            Toast.makeText(getApplicationContext(), "Account and PrivateKey did not match. Try Again!" , Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();
                        Toast.makeText(getApplicationContext(), "User Name is incorrect" , Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    Toast.makeText(getApplicationContext(), "Private Key is incorrect" , Toast.LENGTH_SHORT).show();
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
