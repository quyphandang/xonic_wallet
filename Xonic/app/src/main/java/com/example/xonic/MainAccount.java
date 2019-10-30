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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import io.contentos.android.sdk.Wallet;
    class Global{
        public static String userName = "";
        public static String privateKey = "";
    }
public class MainAccount extends AppCompatActivity {
    //public static String userName = "quyphancos" ;
    //public static String privateKey = "3uXkdUTCdMNFEDoGcqrVeuSbGCv4ZcUndTYMjFnU7SjaDN597q";
    public static Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_account);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_wallet, R.id.nav_exchange, R.id.nav_stake, R.id.nav_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNav, navController);

//        Intent intent = getIntent();
//        Global.userName = intent.getStringExtra(ImportInfo.USERNAME);
//        Global.privateKey = intent.getStringExtra(ImportInfo.PRIVATEKEY);
        sharedPreferences = getSharedPreferences("saveAccount", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String checkaccount = sharedPreferences.getString("username","");
        int lendcheckaccount = checkaccount.length();
        if (lendcheckaccount == 0) {
            //Toast.makeText( this , "chưa lưu tài khoản!",   Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            Global.userName = intent.getStringExtra(ImportInfo.USERNAME);
            Global.privateKey = intent.getStringExtra(ImportInfo.PRIVATEKEY);

            editor.putString("username", Global.userName);
            editor.putString("privatekey", Global.privateKey);

            editor.commit();
        } else{
            //Toast.makeText( this , "Đã lưu tai khoan!",   Toast.LENGTH_SHORT).show();
            Global.userName = sharedPreferences.getString("username","");
            Global.privateKey = sharedPreferences.getString("privatekey","");
            editor.putBoolean("checked", true);
            editor.commit();
        }


    }
}
