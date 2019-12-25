package com.example.xonic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class AddPhone extends AppCompatActivity {
    Button button, back, button_text;
    EditText phoneid, codeid;
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
        button = (Button) findViewById(R.id.button);
        button_text = (Button) findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PhoneNumber = phoneid.getText().toString().trim();
                if(PhoneNumber.isEmpty()){
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    Toast.makeText(AddPhone.this, "Please enter your phone number", Toast.LENGTH_LONG).show();
                }else{
                    int Num0 = PhoneNumber.charAt(0);
                    String Number0 = String.valueOf((char) Num0);
                    //Toast.makeText(AddPhone.this, Number0, Toast.LENGTH_LONG).show();
                    if(Number0.equals("+")){
                        SendCode("http://accountcreator.contentos.io/v1/create_account_request");
                    }else{
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();
                        Toast.makeText(AddPhone.this, "Incorrect syntax", Toast.LENGTH_LONG).show();
                    }
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            // String a = jsonobject.get("success");
                            String a  = jsonobject.getString("success");
                           // Toast.makeText(AddPhone.this,response,Toast.LENGTH_LONG).show();
                            if(a=="true"){
                                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                                    return;
                                }
                                mLastClickTime = SystemClock.elapsedRealtime();
                                Toast.makeText(AddPhone.this, "Please check the message!", Toast.LENGTH_LONG).show();
                            }else{
                                String b = jsonobject.getString("message");
                                //Toast.makeText(AddPhone.this,b ,Toast.LENGTH_LONG).show();
                                if(b.equals("mobile phone existed.")){
                                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                                        return;
                                    }
                                    mLastClickTime = SystemClock.elapsedRealtime();
                                    Toast.makeText(AddPhone.this, "Mobile Phone Existed", Toast.LENGTH_LONG).show();
                                }else{
                                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                                        return;
                                    }
                                    mLastClickTime = SystemClock.elapsedRealtime();
                                    Toast.makeText(AddPhone.this, "Incorrect Phone Number. Please try again!", Toast.LENGTH_LONG).show();
                                }

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
                        Toast.makeText(AddPhone.this, "The system is busy",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("mobile", phoneid.getText().toString().trim());
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
                            String a  = jsonobject.getString("success");
                            if(a=="true"){
                                //Toast.makeText(AddPhone.this,response , Toast.LENGTH_LONG).show();
                               // String b = jsonobject.getString("data");
                                String b = jsonobject.getJSONObject("data").getString("token");
                                //Toast.makeText(AddPhone.this,b, Toast.LENGTH_LONG).show();
                                String phone = phoneid.getText().toString().trim();
                                byExtras(phone,b);
                                //Toast.makeText(AddPhone.this, "Verify successful!", Toast.LENGTH_LONG).show();
                            }else{
                                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                                    return;
                                }
                                mLastClickTime = SystemClock.elapsedRealtime();
                                Toast.makeText(AddPhone.this, "Code Mismatch", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(AddPhone.this, "The system is busy",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("mobile", phoneid.getText().toString().trim());
                params.put("code", codeid.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void byExtras(String phone, String token){
        //Intent intent = new Intent(AddUseName.this, InfoAccount.class);
        Intent intent = new Intent(getApplicationContext(), AddUseName.class);
        intent.putExtra(PHONE,phone);
        intent.putExtra(TOKEN,token);
        startActivity(intent);
    }
}
