package com.example.xonic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

import static com.example.xonic.MainAccount.wallet;

public class SetPassword extends AppCompatActivity {
    TextView number0, number1, number2, number3, number4, number5, number6, number7, number8, number9;
    ImageView removenumber, no1, no2, no3, no4, no5, no6;
    SharedPreferences sharedPreferences;
    private long mLastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        no1 = (ImageView) findViewById(R.id.no1);
        no2 = (ImageView) findViewById(R.id.no2);
        no3 = (ImageView) findViewById(R.id.no3);
        no4 = (ImageView) findViewById(R.id.no4);
        no5 = (ImageView) findViewById(R.id.no5);
        no6 = (ImageView) findViewById(R.id.no6);

        number0 = (TextView) findViewById(R.id.number0);
        number1 = (TextView) findViewById(R.id.number1);
        number2 = (TextView) findViewById(R.id.number2);
        number3 = (TextView) findViewById(R.id.number3);
        number4 = (TextView) findViewById(R.id.number4);
        number5 = (TextView) findViewById(R.id.number5);
        number6 = (TextView) findViewById(R.id.number6);
        number7 = (TextView) findViewById(R.id.number7);
        number8 = (TextView) findViewById(R.id.number8);
        number9 = (TextView) findViewById(R.id.number9);
        removenumber = (ImageView) findViewById(R.id.removenumber);

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "0";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "1";
                check(SavePass.countPass);

                //  no1.setImageResource(R.drawable.ic_full_24dp);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "2";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "3";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "4";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "5";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "6";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "7";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "8";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePass.countPass = SavePass.countPass + 1;
                SavePass.pass = SavePass.pass + "9";
                check(SavePass.countPass);
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        removenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SavePass.countPass = SavePass.countPass - 1;
                    SavePass.pass = SavePass.pass.substring(0, SavePass.countPass);
                    check(SavePass.countPass);
                } catch(Exception e) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    Toast.makeText( SetPassword.this, "Set your passcode! " + SavePass.pass, Toast.LENGTH_SHORT).show();
                    Toast.makeText( SetPassword.this, "Set Password! " + SavePass.pass, Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void check(int count){
        if (count == 1 ){
            //Toast.makeText( DatPass.this, "1", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_no_24dp);
            no3.setImageResource(R.drawable.ic_no_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 2) {
            //Toast.makeText( DatPass.this, "2", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_no_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 3){
            //Toast.makeText( DatPass.this, "3", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 4){
            //Toast.makeText( DatPass.this, "4", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_full_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 5){
            //Toast.makeText( DatPass.this, "5", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_full_24dp);
            no5.setImageResource(R.drawable.ic_full_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else if (count == 6){
            //Toast.makeText( DatPass.this, "6", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_full_24dp);
            no2.setImageResource(R.drawable.ic_full_24dp);
            no3.setImageResource(R.drawable.ic_full_24dp);
            no4.setImageResource(R.drawable.ic_full_24dp);
            no5.setImageResource(R.drawable.ic_full_24dp);
            no6.setImageResource(R.drawable.ic_full_24dp);

            sharedPreferences = getSharedPreferences("pass", MODE_PRIVATE);
            String checkpass = sharedPreferences.getString("password","");
            if (checkpass.equals(SavePass.pass)){
                Toast.makeText( SetPassword.this, "Success!", Toast.LENGTH_SHORT).show();
                sharedPreferences = getSharedPreferences("saveAccount", MODE_PRIVATE);
                String checkacc = sharedPreferences.getString("username", "");
                String checkkey = sharedPreferences.getString("privatekey", "");
                int lenghcheck = checkacc.length();
                int lenghcheckkey = checkkey.length();
                if ((lenghcheck == 0) || (lenghcheckkey == 0)) {

                    Intent intent = new Intent(SetPassword.this, PrivateAndSucure.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    try {
                        String inpublicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(checkkey)));
                        try {
                            String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(checkacc).getInfo().getPublicKey());
                            if (inpublicKey.equals(inpublicKeyAccount)) {
                                Intent intent1 = new Intent(SetPassword.this, MainAccount.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent1);
                            } else {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("username");
                                editor.remove("privatekey");
                                editor.commit();
                                Intent intent2 = new Intent(SetPassword.this, PrivateAndSucure.class);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent2);
//                        //Toast.makeText(ImportInfo.this, "Account and PrivateKey did not match. Try Again!" , Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("username");
                            editor.remove("privatekey");
                            editor.commit();
                            Intent intent3 = new Intent(SetPassword.this, PrivateAndSucure.class);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent3);
                        }
                    } catch (Exception e) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.remove("privatekey");
                        editor.commit();
                        Intent intent4 = new Intent(SetPassword.this, PrivateAndSucure.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent4);
                    }
                }
//                Intent intent = new Intent(MainActivity.this, PrivateAndSucure.class );
//                startActivity(intent);
//                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();
            }else{

                Toast.makeText( SetPassword.this, "No match", Toast.LENGTH_SHORT).show();
                SavePass.pass = "";
                SavePass.countPass = 0;
                no1.setImageResource(R.drawable.ic_no_24dp);
                no2.setImageResource(R.drawable.ic_no_24dp);
                no3.setImageResource(R.drawable.ic_no_24dp);
                no4.setImageResource(R.drawable.ic_no_24dp);
                no5.setImageResource(R.drawable.ic_no_24dp);
                no6.setImageResource(R.drawable.ic_no_24dp);
            }
            SavePass.pass = "";
            SavePass.countPass = 0;

        } else if(count == 0){
            //Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_no_24dp);
            no2.setImageResource(R.drawable.ic_no_24dp);
            no3.setImageResource(R.drawable.ic_no_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else {
            SavePass.pass = "";
            SavePass.countPass = 0;
            Toast.makeText( SetPassword.this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        }

    }
}
