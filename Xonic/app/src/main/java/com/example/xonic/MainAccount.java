package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import io.contentos.android.sdk.Wallet;

public class MainAccount extends AppCompatActivity {

    public static String userName = "quyphancos" ;
    public static String privateKey = "3uXkdUTCdMNFEDoGcqrVeuSbGCv4ZcUndTYMjFnU7SjaDN597q";
    public static Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_account);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_wallet, R.id.nav_exchange, R.id.nav_stake, R.id.nav_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNav, navController);

        //bottomNav.setOnNavigationItemSelectedListener(navListener);
//        Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
        Intent intent = getIntent();
        String userName = intent.getStringExtra(ImportInfo.USERNAME);
        String privateKey = intent.getStringExtra(ImportInfo.PRIVATEKEY);
//        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
//        double balance2 = (double) balance/1000000;
//
//        Bundle bundle = new Bundle();
//        bundle.putString("Balance", String.valueOf(balance2));
//        bundle.putString("Username", userName);
//        bundle.putString("Privatekey", privateKey);
//        Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.nav_wallet, bundle);

    }

}
