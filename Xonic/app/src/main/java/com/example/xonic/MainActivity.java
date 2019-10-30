package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

import static com.example.xonic.MainAccount.wallet;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("saveAccount", MODE_PRIVATE);
        String checkacc = sharedPreferences.getString("username","");
        String checkkey = sharedPreferences.getString("privatekey","");
        int lenghcheck = checkacc.length();
        int lenghcheckkey = checkkey.length();
        if ((lenghcheck == 0) || (lenghcheckkey == 0)){

            Intent intent = new Intent(MainActivity.this, PrivateAndSucure.class );
            startActivity(intent);
        }else {
            try{
                String inpublicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(checkkey)));
                try{
                    String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(checkacc).getInfo().getPublicKey());
                    if (inpublicKey.equals(inpublicKeyAccount)){
                        //Toast.makeText(ImportInfo.this, "Login Success!" , Toast.LENGTH_SHORT).show();
                        //String Name = userName;
//                        //String Priva = privateKey;
//                        //byExtras(userName,privateKey);
                        Intent intent1 = new Intent(MainActivity.this, MainAccount.class );
                        startActivity(intent1);
                    }else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.remove("privatekey");
                        editor.commit();
                        Intent intent2 = new Intent(MainActivity.this, PrivateAndSucure.class );
                        startActivity(intent2);
//                        //Toast.makeText(ImportInfo.this, "Account and PrivateKey did not match. Try Again!" , Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("username");
                    editor.remove("privatekey");
                    editor.commit();
                    Intent intent3 = new Intent(MainActivity.this, PrivateAndSucure.class );
                    startActivity(intent3);
                }
            }catch(Exception e){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.remove("privatekey");
                editor.commit();
                Intent intent4 = new Intent(MainActivity.this, PrivateAndSucure.class );
                startActivity(intent4);
            }
        }

    }
}
