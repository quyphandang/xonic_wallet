package com.example.xonic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

import static com.example.xonic.MainAccount.wallet;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ProgressBar splashProgress;
    int SPLASH_TIME = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        setContentView(R.layout.activity_main);


        splashProgress = findViewById(R.id.splashProgress);
        playProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (haveNetwork()){
                  //  Toast.makeText(MainActivity.this,"Network availabe",Toast.LENGTH_SHORT).show();
//                }else if (!haveNetwork()){
//                    Toast.makeText(MainActivity.this,"No Internet connection",Toast.LENGTH_SHORT).show();
//                }
                    sharedPreferences = getSharedPreferences("pass", MODE_PRIVATE);
                    String checkpass = sharedPreferences.getString("password","");
                    int lengthcheck = checkpass.length();
                    // Toast.makeText( MainActivity.this, String.valueOf(lengthcheck), Toast.LENGTH_SHORT).show();
                    if (lengthcheck == 6){
                        Intent intent = new Intent(MainActivity.this, SetPassword.class);
                        startActivity(intent);
                    }else {
                        //Do any action here. Now we are moving to next page
                        sharedPreferences = getSharedPreferences("saveAccount", MODE_PRIVATE);
                        String checkacc = sharedPreferences.getString("username", "");
                        String checkkey = sharedPreferences.getString("privatekey", "");
                        int lenghcheck = checkacc.length();
                        int lenghcheckkey = checkkey.length();
                        if ((lenghcheck == 0) || (lenghcheckkey == 0)) {

                            Intent intent = new Intent(MainActivity.this, PrivateAndSucure.class);
                            startActivity(intent);
                        } else {
//                            Intent intent1 = new Intent(MainActivity.this, MainAccount.class);
//                                        startActivity(intent1);

                            try {

                                String inpublicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(checkkey)));

                                try {
                                    //long millis1 = System.currentTimeMillis();
                                    String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(checkacc).getInfo().getPublicKey());
//                                    long millis2 = System.currentTimeMillis();
//                                    long distance = millis2 - millis1;
//                                    Toast.makeText(MainActivity.this, "Time " + String.valueOf(distance)  , Toast.LENGTH_SHORT).show();
                                    if (inpublicKey.equals(inpublicKeyAccount)) {
                                        Intent intent1 = new Intent(MainActivity.this, MainAccount.class);
                                        startActivity(intent1);
                                    } else {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.remove("username");
                                        editor.remove("privatekey");
                                        editor.commit();
                                        Intent intent2 = new Intent(MainActivity.this, PrivateAndSucure.class);
                                        startActivity(intent2);
//                        //Toast.makeText(ImportInfo.this, "Account and PrivateKey did not match. Try Again!" , Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("username");
                                    editor.remove("privatekey");
                                    editor.commit();
                                    Intent intent3 = new Intent(MainActivity.this, PrivateAndSucure.class);
                                    startActivity(intent3);
                                }
                            } catch (Exception e) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("username");
                                editor.remove("privatekey");
                                editor.commit();
                                Intent intent4 = new Intent(MainActivity.this, PrivateAndSucure.class);
                                startActivity(intent4);
                            }
                        }
//                Intent intent = new Intent(MainActivity.this, PrivateAndSucure.class );
//                startActivity(intent);
//                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                        finish();
                    }
                }else if (!haveNetwork()){
                    Toast.makeText(MainActivity.this,"No Internet connection",Toast.LENGTH_SHORT).show();
                }
            }
        }, SPLASH_TIME);

    }
    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(3000)
                .start();
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
