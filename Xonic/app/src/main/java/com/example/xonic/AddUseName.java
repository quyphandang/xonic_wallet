package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.junit.*;

//import es.dmoral.toasty.Toasty;
import io.contentos.android.sdk.Network;
import io.contentos.android.sdk.Wallet;
import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;
import io.contentos.android.sdk.prototype.Type;
import io.contentos.android.sdk.rpc.Grpc;
import io.contentos.android.sdk.rpc.RpcClient;

class valuei {
    public static int i = 0;
}
public class AddUseName extends AppCompatActivity {
    Button back;
    EditText username;
    Button infoacc;
    public static final String USERNAME = "USERNAME";
    public static final String PRIVATEKEY = "PRIVATEKEY";
    public static final String PUBLICKEY = "PUBLICKEY";
    //public static final String BUNDLE = "BUNDLE";
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
        setContentView(R.layout.activity_add_use_name);
        //back
        back = (Button) findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View View){ Intent intent = new Intent(AddUseName.this, MainActivity.class );
                                               startActivity(intent);
                                           }
                                       });
        infoacc = (Button) findViewById(R.id.infoacc) ;
        username = (EditText) findViewById(R.id.username);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String USERNAME_PATTERN = "^[a-z0-9]{6,16}$";
                Pattern pattern = Pattern.compile(USERNAME_PATTERN);
                String UserName = username.getText().toString();
                Matcher matcher = pattern.matcher(UserName);
                boolean match = matcher.matches();
                if (match == true){
                    valuei.i = 1;
                    username.setTextColor(0xFF00BA47);
                    infoacc.setTextColor(0xFFFFFFFF);
                    infoacc.setBackgroundResource(R.drawable.boder_30);
                } else{
                    valuei.i = 0;
                    username.setTextColor(0xFFF10303);
                    infoacc.setTextColor(0xFF00BA47);
                    infoacc.setBackgroundResource(R.drawable.bordered);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        infoacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (valuei.i == 0){
                    //Toasty.error(AddUseName.this, "Incorrectly. Please again!", Toast.LENGTH_SHORT, true).show();
                    Toast.makeText(AddUseName.this, "Incorrectly. Please again!", Toast.LENGTH_SHORT).show();
                    //Toast toast = Toast.makeText(AddUseName.this, "Incorrectly. Please again!", Toast.LENGTH_SHORT);
                    //toast.setGravity();
                }else{

                Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
                String userName = username.getText().toString();
                String privateKey = WIF.fromPrivateKey(Key.generate());
                String publicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));
                wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
                wallet.addKey("quyphancos", "3uXkdUTCdMNFEDoGcqrVeuSbGCv4ZcUndTYMjFnU7SjaDN597q");
                //wallet.addKey("quyphancos", "3uXkdUTCdMNFEDoGcqrVeuSbGCv4ZcUndTYMjFnU7SjaD59ef"); //failse private
                //wallet.addKey("quyphancos","4ZSzaybvskVimm1WoHmipE4XFpMYz4pHSHhxGt6w5mLKC7xyS1"); //acc mainnet
                long accountCreationFee = wallet.getChainState().getState().getDgpo().getAccountCreateFee().getValue();
                Grpc.BroadcastTrxResponse resp = wallet.account("quyphancos").accountCreate(
                        "quyphancos",
                        userName,
                        accountCreationFee,
                        Key.publicKeyOf(WIF.toPrivateKey(privateKey)),
                        "");

                if(resp.getInvoice().getStatus() == 200) {
                    //Toasty.success(AddUseName.this, "Success!", Toast.LENGTH_SHORT, true).show();
                    byExtras(userName, privateKey, publicKey);
                    //byExtrasUser(userName,privateKey);
                } else {
                    //Toasty.warning(AddUseName.this, "Account already exists!", Toast.LENGTH_SHORT, true).show();
                    Toast.makeText(AddUseName.this, "Account already exists!", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });

    }
    public void byExtras(String userName, String privateKey, String publicKey){
        //Intent intent = new Intent(AddUseName.this, InfoAccount.class);
        Intent intent = new Intent(getApplicationContext(), InfoAccount.class);
        intent.putExtra(USERNAME,userName);
        intent.putExtra(PUBLICKEY,publicKey);
        intent.putExtra(PRIVATEKEY,privateKey);
        startActivity(intent);
    }
}
