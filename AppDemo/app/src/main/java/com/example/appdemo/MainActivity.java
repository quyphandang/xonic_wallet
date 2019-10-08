package com.example.appdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import io.contentos.android.sdk.Network;
import io.contentos.android.sdk.Wallet;

public class MainActivity extends AppCompatActivity {
    TextView privatekey;
    TextView keystore;
    Button   create;

    //
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
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        privatekey = (TextView) findViewById(R.id.privatekey);
        keystore = (TextView) findViewById(R.id.keystore);
        create = (Button) findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code create wallet

                Wallet wallet = Network.Main.wallet();
                wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
                //
            }
        });
    }
}
