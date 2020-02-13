package com.example.xonic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.ConfigurationCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
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
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
class valuecode {
    public static String code = "";
    public static String fullphone = "";
    public static String verycode = "";
}

public class AddPhone extends AppCompatActivity {
    Button  back, button_text;
    EditText phoneid, codeid;
    TextView button;
    CountryCodePicker ccp;
    Locale locale;
    public static final String PHONE = "PHONE";
    public static final String TOKEN = "TOKEN";
    private long mLastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_phone);
        phoneid = (EditText) findViewById(R.id.phoneid);
        codeid = (EditText) findViewById(R.id.codeid);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){ Intent intent = new Intent(AddPhone.this, PrivateAndSucure.class );
                startActivity(intent);
            }
        });
        button = (TextView) findViewById(R.id.button);
        button_text = (Button) findViewById(R.id.button_next);
        locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.setCountryForNameCode(locale.getCountry());
        ccp.setDefaultCountryUsingNameCode(locale.getCountry());
        valuecode.code = ccp.getDefaultCountryCode();
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                //Toast.makeText(AddPhone.this, selectedCountry.getPhoneCode() , Toast.LENGTH_SHORT).show();
                valuecode.code = selectedCountry.getPhoneCode();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (valuecode.code.equals("")){
//                    valuecode.code = ccp.getDefaultCountryCode();
//                }
                String phone = phoneid.getText().toString().trim();;
                if (phone.equals("")){
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    Toast.makeText(AddPhone.this, "Please enter mobile!" , Toast.LENGTH_SHORT).show();
                }else{
                    valuecode.fullphone = "+"+valuecode.code+phoneid.getText().toString().trim().replaceAll("\\s+","");
                    //Toast.makeText(AddPhone.this, valuecode.fullphone, Toast.LENGTH_SHORT).show();
                    SendCode("http://accountcreator.contentos.io/v1/create_account_request");
                }

            }
        });

        button_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PhoneNumber = phoneid.getText().toString().trim();
                String CodeNumber = codeid.getText().toString().trim();
                if(PhoneNumber.isEmpty() || CodeNumber.isEmpty()){
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    Toast.makeText(AddPhone.this, "Please fill out the information", Toast.LENGTH_LONG).show();
                }else{
                    VerifyCode("http://accountcreator.contentos.io/v1/create_account_verify");
                }

            }
        });

    }
    private void SendCode(String url){
        //Toast.makeText(AddPhone.this,"Pass Sendcode ",Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            String a  = jsonobject.getString("code");
                            //Toast.makeText(AddPhone.this,a,Toast.LENGTH_SHORT).show();
                            if(a.equals("200")){
                                new CountDownTimer(60000, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                        button.setText("" + millisUntilFinished / 1000);
                                        button.setEnabled(false);
                                    }
                                    public void onFinish() {
                                        button.setText("Send");
                                        button.setEnabled(true);
                                    }
                                }.start();
                                //Toast.makeText(AddPhone.this, "Please check the message!", Toast.LENGTH_LONG).show();
                            }else if(a.equals("400")){
                                Toast.makeText(AddPhone.this, "Please try again!",Toast.LENGTH_SHORT).show();
                            }else if(a.equals("401")){
                                Toast.makeText(AddPhone.this, "Request too frequent", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("402")){
                                Toast.makeText(AddPhone.this, "Phone number has been registered", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("405")){
                                Toast.makeText(AddPhone.this, "Ip has been restricted", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("406")){
                                Toast.makeText(AddPhone.this, "Incorrect phone nunmber", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AddPhone.this, "Please try again!", Toast.LENGTH_SHORT).show();
                            }
                            //Toast.makeText(AddPhone.this, a, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(AddPhone.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();
                        Toast.makeText(AddPhone.this, "Please try again!",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("mobile", valuecode.fullphone);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void VerifyCode(String urlc){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlc,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            // String a = jsonobject.get("success");
                            String a  = jsonobject.getString("code");
                            if(a.equals("200")){
//                                String b = jsonobject.getJSONObject("data").getString("token");
//                                String phone = valuecode.fullphone;
                                valuecode.verycode = jsonobject.getJSONObject("data").getString("token");
                                byExtras();
                                //Toast.makeText(AddPhone.this, "Verify successful!", Toast.LENGTH_LONG).show();
                            }else if(a.equals("400")){
                                Toast.makeText(AddPhone.this, "Please try again!",Toast.LENGTH_SHORT).show();
                            }else if(a.equals("401")){
                                Toast.makeText(AddPhone.this, "Request too frequent", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("402")){
                                Toast.makeText(AddPhone.this, "Phone number has been registered", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("403")){
                                Toast.makeText(AddPhone.this, "Verify code doesn't match", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("405")){
                                Toast.makeText(AddPhone.this, "Ip has been restricted", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("406")){
                                Toast.makeText(AddPhone.this, "Incorrect phone nunmber", Toast.LENGTH_SHORT).show();
                            }else if(a.equals("407")){
                                Toast.makeText(AddPhone.this, "Invalid verify code", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AddPhone.this, "Please try again!", Toast.LENGTH_SHORT).show();
                            }
                            //Toast.makeText(AddPhone.this, a, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();
                        Toast.makeText(AddPhone.this, "Please try again!",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("mobile", valuecode.fullphone);
                params.put("code", codeid.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void byExtras(){
        //Intent intent = new Intent(AddUseName.this, InfoAccount.class);
        Intent intent = new Intent(getApplicationContext(), AddUseName.class);
//        intent.putExtra(PHONE,phone);
//        intent.putExtra(TOKEN,token);
        startActivity(intent);
    }
}
