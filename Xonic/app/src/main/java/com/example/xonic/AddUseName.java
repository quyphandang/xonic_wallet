package com.example.xonic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
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

import static com.example.xonic.MainAccount.wallet;

class valuei {
    public static int i = 0;
    public static String prikeypre = "";
    public static String publicpre ="";
}
public class AddUseName extends AppCompatActivity {
    Button back;
    EditText username;
    Button infoacc;
    private long mLastClickTime = 0;
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

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_use_name);
        //back
        setDataByExtras();
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
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    Toast.makeText(AddUseName.this, "Incorrectly. Please again!", Toast.LENGTH_SHORT).show();

                }else{

                    VerifyAccount("http://accountcreator.contentos.io/v1/create_account_confirm");
//                String userName = username.getText().toString();
//                String privateKey = WIF.fromPrivateKey(Key.generate());
//                String publicKey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));

                //wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
                //wallet.addKey("xonicwallet", "4JpfK2Pwqd877d4LzYF5LRhLx9X8ywbxR7fpvwLFtDnjWRk1V3");
//                long accountCreationFee = wallet.getChainState().getState().getDgpo().getAccountCreateFee().getValue();
//                Grpc.BroadcastTrxResponse resp = wallet.account("xonicwallet").accountCreate(
//                        "xonicwallet",
//                        userName,
//                        accountCreationFee,
//                        Key.publicKeyOf(WIF.toPrivateKey(privateKey)),
//                        "");
//
//                if(resp.getInvoice().getStatus() == 200) {
//                    //Toasty.success(AddUseName.this, "Success!", Toast.LENGTH_SHORT, true).show();
//                    byExtras(userName, privateKey, publicKey);
//                    //byExtrasUser(userName,privateKey);
//                } else {
//                    //Toasty.warning(AddUseName.this, "Account already exists!", Toast.LENGTH_SHORT, true).show();
//                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                        return;
//                    }
//                    mLastClickTime = SystemClock.elapsedRealtime();
//                    Toast.makeText(AddUseName.this, "Account already exists!", Toast.LENGTH_SHORT).show();
//                }

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

    public void setDataByExtras(){

        Intent intent = getIntent();
        String phone = intent.getStringExtra(AddPhone.PHONE);
        String token = intent.getStringExtra(AddPhone.TOKEN);
        //Toast.makeText(AddUseName.this,"Phone " + phone + " Token: " + token,Toast.LENGTH_LONG).show();
    }

    private void VerifyAccount(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            // String a = jsonobject.get("success");
                            String a  = jsonobject.getString("success");
                            if(a=="true"){
                                String username1 = username.getText().toString();
                                byExtras(username1,valuei.prikeypre, valuei.publicpre);
                            }else{
                                Toast.makeText(AddUseName.this, "Account already exists!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddUseName.this, "The system is busy",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                valuei.prikeypre  = WIF.fromPrivateKey(Key.generate());
                valuei.publicpre = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(valuei.prikeypre)));
                Intent intent = getIntent();
                String phone = intent.getStringExtra(AddPhone.PHONE);
                String token = intent.getStringExtra(AddPhone.TOKEN);
                String username1 = username.getText().toString();
                params.put("mobile", phone);
                params.put("token", token);
                params.put("account", username1);
                params.put("pubkey", valuei.publicpre);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
