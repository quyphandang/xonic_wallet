package com.example.checkinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (haveNetwork()){
            Toast.makeText(MainActivity.this,"Network availabe",Toast.LENGTH_SHORT).show();
        }else if (!haveNetwork()){
            Toast.makeText(MainActivity.this,"No Internet connection",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean haveNetwork(){
        boolean have_WIFI = false;
        boolean have_MobileData = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //NetworkInfo networkinfos = connectivityManager.getAllNetworkInfo();
        NetworkInfo[] networkinfos = connectivityManager.getAllNetworkInfo();
        //ConnectivityManager.NetworkCallback networkinfos = connectivityManager.getNetworkCapabilities();
        for (NetworkInfo info: networkinfos ){
              if(info.getTypeName().equalsIgnoreCase("WIFI")){
                  if(info.isConnected()){
                      have_WIFI = true;
                  }
              }
                  if(info.getTypeName().equalsIgnoreCase("MOBILE")){
                      if(info.isConnected()){
                          have_MobileData = true;
                      }
                  }
        }
        return have_MobileData || have_WIFI;
    }
}
