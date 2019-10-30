package com.example.xonic;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.fragment.app.ListFragment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import io.contentos.android.sdk.Wallet;

//import static com.example.xonic.MainAccount.privateKey;
//import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;
import static com.example.xonic.MainAccount.wallet;

public class StakeFragment extends ListFragment {
    ImageView imageswap;
    Button exchangeid;
    EditText amount1;
    TextView amount2;
    ArrayList<ExchangeInfo> arrayListCoin;
    ExchangeInfoAdapter adapter;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stake, container, false);

        ImageView imageswap = (ImageView) view.findViewById(R.id.imageswap);
        Button stakeid = (Button) view.findViewById(R.id.stakeid);
        final EditText amount1 = (EditText) view.findViewById(R.id.amount1);
        final TextView amount2 = (TextView) view.findViewById(R.id.amount2);
        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
        double balance2 = (double) balance/1000000;

        //long balancevest = wallet.getAccountByName(userName).getInfo().getVest().getValue();
        long balancestake = wallet.getAccountByName(userName).getInfo().getStakeVestFromMe().getValue();
        double balance2stake  = (double) balancestake/1000000;
        arrayListCoin = new ArrayList<>();
        arrayListCoin.add(new ExchangeInfo( "Balance Cos: " + String.valueOf(balance2), R.drawable.icon_cos2, "COS"));
        arrayListCoin.add(new ExchangeInfo( "Balance Chicken: " + String.valueOf(balance2stake), R.drawable.icon_stake, "Chicken"));
        //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
        adapter = new ExchangeInfoAdapter(getActivity(), R.layout.exchange_view, arrayListCoin);
        setListAdapter(adapter);
        //
        amount1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String Amount1 = amount1.getText().toString();
                amount2.setText(Amount1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //image swap
        imageswap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String balance0 = arrayListCoin.get(0).getBalance();
                String balance1 = arrayListCoin.get(1).getBalance();
                int iconcoin0 = arrayListCoin.get(0).getIconCoin();
                int iconcoin1 = arrayListCoin.get(1).getIconCoin();
                String icon0 = arrayListCoin.get(0).getIcon();
                String icon1 = arrayListCoin.get(1).getIcon();
                arrayListCoin.set(0,new ExchangeInfo( balance1,iconcoin1,icon1));
                arrayListCoin.set(1,new ExchangeInfo( balance0,iconcoin0,icon0));
                adapter.notifyDataSetChanged();
            }
        });


        wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
        wallet.addKey(userName,privateKey);
        stakeid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String icon0 = arrayListCoin.get(0).getIcon();
                if (icon0 == "COS"){
                    String amountcos = amount1.getText().toString();

                    int lenghtAmount = amountcos.length();
                    //Toast.makeText(getActivity(), "Giá trị long: " + amountcos , Toast.LENGTH_SHORT).show();
                    if (lenghtAmount != 0){
                        double amountcheck = Double.parseDouble(amountcos) * 1000000;
                        long amountlong = (long) amountcheck ;
                        if (amountlong < 1){
                            Toast.makeText(getActivity(), "The transaction amount is lower than the minimum. Please enter again!", Toast.LENGTH_SHORT).show();
                        }else{
                            long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
                            long balancecheck = balance - amountlong ;
                            if (balancecheck < 0){
                                Toast.makeText(getActivity(), "Current balance is not sufficient!", Toast.LENGTH_SHORT).show();
                            }else{
                                dialog_convert(amountcos);
                                //Toast.makeText(getActivity(), "Đúng rồi!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Toast.makeText( getActivity(), "Please enter amount Cos!",   Toast.LENGTH_SHORT).show();
                    }
                }else {
                    String amountstake = amount1.getText().toString();
                    int lenghtAmountstake = amountstake.length();
                    if (lenghtAmountstake != 0){
                        double amountcheckstake = Double.parseDouble(amountstake)*1000000;
                        long amountstakelong = (long) amountcheckstake;
                        if (amountstakelong < 1){
                            Toast.makeText(getActivity(), "The transaction amount is lower than the minimum. Please enter again!", Toast.LENGTH_SHORT).show();
                        }else{
                            long balancestake = wallet.getAccountByName(userName).getInfo().getStakeVestFromMe().getValue();
                            //long balancevest = wallet.getAccountByName(userName).getInfo().getVest().getValue();
                            //long balancevestcheck = balancevest - amountvestlong*100000;
                            long balancestakecheck = balancestake - amountstakelong;
                            if (balancestakecheck < 0){
                                Toast.makeText(getActivity(), "Current balance is not sufficient!", Toast.LENGTH_SHORT).show();

                                //Toast.makeText( getActivity(), String.valueOf(amountvestlong),   Toast.LENGTH_SHORT).show();
                            }else{
                                dialog_convert_stake(amountstake);
                            }
                        }
                    }else{
                        Toast.makeText( getActivity(), "Please enter amount Chicken!",   Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText( getActivity(), "VEST to COS",   Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void dialog_convert(String amountshow){
        //Toast.makeText(getActivity(), "Đúng rồi!", Toast.LENGTH_SHORT).show();
        // String amountshow = amountcos;
        //String amountshow = amount1.getText().toString();
        double amountdouble = Double.parseDouble(amountshow) * 1000000;
        final long amountlong = (long) amountdouble ;
        //final long amountlong = amountlong1*100000;

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Notice!");
        alertDialog.setMessage("Are you sure you will convert " + amountshow + " COS to Chicken?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // wallet.account(userName).transferToVest(userName,userName, amountlong,"test");
                wallet.account(userName).stake(userName,userName,amountlong);
                //wallet.account(userName).
            }
        });
        alertDialog.setNegativeButton ("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }
    private void dialog_convert_stake(String amountstakeshow){
        double amountdoublestake = Double.parseDouble(amountstakeshow)*1000000;
        final long amountlongstake = (long) amountdoublestake;
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        //double amountshow = (double) amountvestshow/10 ;
        alertDialog.setTitle("Notice!");
        alertDialog.setMessage("Are you sure you will convert " + amountstakeshow + " Chicken to COS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //wallet.account(userName).convertVest(userName ,amountlongvest);
                wallet.account(userName).unStake(userName,userName,amountlongstake);
                //wallet.account(userName).transferToVest(userName,userName, amountlongvest,"test");
            }
        });
        alertDialog.setNegativeButton ("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();

    }
}
