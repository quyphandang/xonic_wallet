package com.example.xonic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static com.example.xonic.MainAccount.privateKey;
import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.MainAccount.wallet;

public class ExchangeFragment extends ListFragment {
    ImageView imageswap;
    Button exchangeid;
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
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);
        ImageView imageswap = (ImageView) view.findViewById(R.id.imageswap);
        Button exchangeid = (Button) view.findViewById(R.id.exchangeid);
        arrayListCoin = new ArrayList<>();
        arrayListCoin.add(new ExchangeInfo("0", "BalanceCos", R.drawable.iconcos, "COS"));
        arrayListCoin.add(new ExchangeInfo("0", "BalanceVet", R.drawable.ex_violet, "VET"));
        //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
        adapter = new ExchangeInfoAdapter(getActivity(), R.layout.exchange_view, arrayListCoin);
        setListAdapter(adapter);

        imageswap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String balance0 = arrayListCoin.get(0).getBalance();
                String balance1 = arrayListCoin.get(1).getBalance();
                int iconcoin0 = arrayListCoin.get(0).getIconCoin();
                int iconcoin1 = arrayListCoin.get(1).getIconCoin();
                String icon0 = arrayListCoin.get(0).getIcon();
                String icon1 = arrayListCoin.get(1).getIcon();
                arrayListCoin.set(0,new ExchangeInfo("0", balance1,iconcoin1,icon1));
                arrayListCoin.set(1,new ExchangeInfo("0", balance0,iconcoin0,icon0));
                adapter.notifyDataSetChanged();
            }
        });
        wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
        wallet.addKey(userName,privateKey);
        exchangeid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String icon0 = arrayListCoin.get(0).getIcon();
                if (icon0 == "COS"){
                    //adapter.notifyDataSetChanged();
                    String amountcos = arrayListCoin.get(0).getName();
                    //String amon = arrayListCoin.get(0).
                    //arrayListCoin.get(0)

                    double amountcheck = Double.parseDouble(amountcos) * 1000000;
                    long amountlong = (long) amountcheck ;
                    int lenghtAmount = amountcos.length();
                    Toast.makeText(getActivity(), "Giá trị long: " + amountcos , Toast.LENGTH_SHORT).show();
                    if (lenghtAmount != 0){
                        if (amountlong < 1){
                            Toast.makeText(getActivity(), "The transaction amount is lower than the minimum. Please enter again!", Toast.LENGTH_SHORT).show();
                        }else{
                            long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
                            long balancecheck = balance - amountlong ;
                            if (balancecheck < 0){
                                Toast.makeText(getActivity(), "Current balance is not sufficient!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), "Đúng rồi!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Toast.makeText( getActivity(), "Please enter amount Cos",   Toast.LENGTH_SHORT).show();
                    }
                    //convert cos to vet
                     //wallet.account(userName).transferToVest(userName,userName,1,"test");
                    Toast.makeText( getActivity(), "COS to VET",   Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText( getActivity(), "VET to COS",   Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
