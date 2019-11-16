package com.example.xonic;

import androidx.appcompat.app.ActionBar;
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
import android.view.Window;
import android.view.WindowManager;
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

import io.contentos.android.sdk.Network;
import io.contentos.android.sdk.Wallet;

import static com.example.xonic.Global.userName;

class Global{
       public static String userName = "";
        public static String privateKey = "";
      //  public static double balance2 = 0;
        public static double balance2vest = 0;
        public static double balance2stake = 0;
    }
public class MainAccount extends AppCompatActivity {
//    public static String userName = "quyphancos" ;
//    public static String privateKey = "3uXkdUTCdMNFEDoGcqrVeuSbGCv4ZcUndTYMjFnU7SjaDN597q";
    //public static Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
      public static Wallet wallet = Network.Main.wallet();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main_account);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_wallet, R.id.nav_exchange, R.id.nav_stake, R.id.nav_setting)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNav, navController);

        sharedPreferences = getSharedPreferences("saveAccount", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String checkaccount = sharedPreferences.getString("username","");
        String checkkey = sharedPreferences.getString("privatekey","");
        int lendcheckaccount = checkaccount.length();
        int lendcheckkey = checkkey.length();
        if ((lendcheckaccount == 0) || (lendcheckkey == 0)) {
            Intent intent = getIntent();
            Global.userName = intent.getStringExtra(ImportInfo.USERNAME);
            Global.privateKey = intent.getStringExtra(ImportInfo.PRIVATEKEY);
            editor.putString("username", Global.userName);
            editor.putString("privatekey", Global.privateKey);
            editor.commit();
        } else{
            Global.userName = sharedPreferences.getString("username","");
            Global.privateKey = sharedPreferences.getString("privatekey","");
            editor.putBoolean("checked", true);
            editor.commit();
        }

        //transfer data
//        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
//        Global.balance2 = (double) balance/1000000;

        long balancevest = wallet.getAccountByName(userName).getInfo().getVest().getValue();
        Global.balance2vest = (double) balancevest/1000000;

        long balancestake = wallet.getAccountByName(userName).getInfo().getStakeVestFromMe().getValue();
        Global.balance2stake = (double) balancestake/1000000;


    }
}
