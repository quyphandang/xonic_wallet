package com.example.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

public class MainActivity extends AppCompatActivity {
    TextView privatekey;
    TextView keystore;
    Button create;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        privatekey = (TextView) findViewById(R.id.privatekey);
        keystore = (TextView) findViewById(R.id.keystore);
        create = (Button) findViewById(R.id.create);
        editText = (EditText) findViewById(R.id.editText);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create wallet
                //              Wallet wallet = Network.Main.wallet();
//                wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
                 String privateKey = WIF.fromPrivateKey(Key.generate());
                 privatekey.setText(privateKey);
                 Key.publicKeyOf(WIF.toPrivateKey(privateKey));
                 String publicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));
                 keystore.setText(publicKey);
            }
        });
    }
}
