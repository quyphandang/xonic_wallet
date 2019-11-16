package com.example.xonic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.xonic.Balancecos.balance2;
import static com.example.xonic.Global.balance2stake;
import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;
import static com.example.xonic.MainAccount.wallet;

//import static com.example.xonic.MainAccount.privateKey;
//import static com.example.xonic.MainAccount.userName;
//import static com.example.xonic.Global.balance2;
class SavePass {
    public static int countPass = 0;
    public static String pass = "";
    public static int next = 1;
    public static String checkPass = "";
}

public class PasswordFragment extends Fragment {
    TextView  number0, number1, number2, number3, number4, number5, number6, number7, number8, number9;
    ImageView removenumber, no1, no2, no3, no4, no5, no6;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        no1 = (ImageView) view.findViewById(R.id.no1);
        no2 = (ImageView) view.findViewById(R.id.no2);
        no3 = (ImageView) view.findViewById(R.id.no3);
        no4 = (ImageView) view.findViewById(R.id.no4);
        no5 = (ImageView) view.findViewById(R.id.no5);
        no6 = (ImageView) view.findViewById(R.id.no6);

        number0 = (TextView) view.findViewById(R.id.number0);
        number1 = (TextView) view.findViewById(R.id.number1);
        number2 = (TextView) view.findViewById(R.id.number2);
        number3 = (TextView) view.findViewById(R.id.number3);
        number4 = (TextView) view.findViewById(R.id.number4);
        number5 = (TextView) view.findViewById(R.id.number5);
        number6 = (TextView) view.findViewById(R.id.number6);
        number7 = (TextView) view.findViewById(R.id.number7);
        number8 = (TextView) view.findViewById(R.id.number8);
        number9 = (TextView) view.findViewById(R.id.number9);
        removenumber = (ImageView) view.findViewById(R.id.removenumber);

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
                    Toast.makeText( getActivity(), "Set Password! " + SavePass.pass, Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText( DatPass.this, "Số: " + String.valueOf(SavePass.countPass) + " Pass: " + SavePass.pass, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
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

            if (SavePass.next == 1){
                SavePass.checkPass = SavePass.pass;
                SavePass.pass = "";
                SavePass.next = 2;
                SavePass.countPass = 0;
                Toast.makeText( getActivity(), "Confirm passwork", Toast.LENGTH_SHORT).show();
                no1.setImageResource(R.drawable.ic_no_24dp);
                no2.setImageResource(R.drawable.ic_no_24dp);
                no3.setImageResource(R.drawable.ic_no_24dp);
                no4.setImageResource(R.drawable.ic_no_24dp);
                no5.setImageResource(R.drawable.ic_no_24dp);
                no6.setImageResource(R.drawable.ic_no_24dp);
            }else{
                if (SavePass.checkPass.equals(SavePass.pass)){
                    Toast.makeText( getActivity(), "Success!", Toast.LENGTH_SHORT).show();
                    SavePass.pass = "";
                    SavePass.countPass = 0;
                    sharedPreferences = getActivity().getSharedPreferences("pass", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("password", SavePass.checkPass);
                    editor.commit();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    //DespositW walletFragment = new WalletFragment();
                    fragmentTransaction.replace(R.id.fragment_container, new SettingFragment());
                    //fragmentTransaction.addToBackStack("Desposit and Withdraw");
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText( getActivity(), "No match", Toast.LENGTH_SHORT).show();
                    SavePass.pass = "";
                    SavePass.countPass = 0;
                    no1.setImageResource(R.drawable.ic_no_24dp);
                    no2.setImageResource(R.drawable.ic_no_24dp);
                    no3.setImageResource(R.drawable.ic_no_24dp);
                    no4.setImageResource(R.drawable.ic_no_24dp);
                    no5.setImageResource(R.drawable.ic_no_24dp);
                    no6.setImageResource(R.drawable.ic_no_24dp);
                }
            }

        } else if(count == 0){
            //Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
            no1.setImageResource(R.drawable.ic_no_24dp);
            no2.setImageResource(R.drawable.ic_no_24dp);
            no3.setImageResource(R.drawable.ic_no_24dp);
            no4.setImageResource(R.drawable.ic_no_24dp);
            no5.setImageResource(R.drawable.ic_no_24dp);
            no6.setImageResource(R.drawable.ic_no_24dp);
        } else {
            Toast.makeText( getActivity(), " ", Toast.LENGTH_SHORT).show();
        }
    }
}
