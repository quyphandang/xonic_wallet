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
import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;
import io.contentos.android.sdk.prototype.Type;
import io.contentos.android.sdk.rpc.Grpc;

public class ImportInfo extends AppCompatActivity {
    Button back, nextinfo;
    EditText editText;
    public static final String USERNAME = "USERNAME";
    public static final String PRIVATEKEY = "PRIVATEKEY";
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

        editText = (EditText) findViewById(R.id.editText);
        nextinfo = (Button) findViewById(R.id.nextinfo);
        nextinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = getIntent();
                //String userName = intent.getStringExtra(InfoAccount.USER);
                Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
                String privateKey = editText.getText().toString();
                Intent intent = getIntent();
                String userName = intent.getStringExtra(InfoAccount.USERNAME);
                try {
                    String inpublicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));
                    try {
                        String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(userName).getInfo().getPublicKey());
                        if (inpublicKey.equals(inpublicKeyAccount)){
                            Toast.makeText(ImportInfo.this, "Login Success!" , Toast.LENGTH_SHORT).show();
                            //String Name = userName;
                            //String Priva = privateKey;
                            byExtras(userName,privateKey);
                        }else{
                            Toast.makeText(ImportInfo.this, "Account and PrivateKey did not match. Try Again!" , Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(ImportInfo.this, "User Name is incorrect" , Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(ImportInfo.this, "Private Key is incorrect" , Toast.LENGTH_SHORT).show();
                }

                /*
                long balance = wallet.getAccountByName("quyphankute").getInfo().getCoin().getValue();
                double balance2 = (double) balance/1000000;

                double balance1 = (double) balance; //0.212221
                double a = 0.000001;
                double balance3 = balance1 - a*1000000;
                double balance4 = balance3/1000000;

                showbalanceid.setText(String.valueOf(balance2));
                wallet.addKey("quyphancos","3uXkdUTCdMNFEDoGcqrVeuSbGCv4ZcUndTYMjFnU7SjaDN597q");
                wallet.account("quyphancos").transfer(
                        "quyphancos",          // token sender
                        "quyphankute",        // token receiver
                        5,                  // token amount
                        "transfer test"     // memo
                );
                showbalanceid2.setText(String.valueOf(balance2));
                */
            }
        });
        //setByUser();

    }
    public void byExtras(String userName, String privateKey){
        //Intent intent = new Intent(AddUseName.this, InfoAccount.class);
        Intent intent = new Intent(getApplicationContext(), MainAccount.class);
        //Intent intent = new Intent(getApplicationContext(), WalletFragment.class);
        intent.putExtra(USERNAME,userName);
        intent.putExtra(PRIVATEKEY,privateKey);
        startActivity(intent);
    }
}
