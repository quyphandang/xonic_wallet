package com.example.xonic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import io.contentos.android.sdk.Wallet;

import static com.example.xonic.MainAccount.privateKey;
import static com.example.xonic.MainAccount.userName;

public class StakeFragment extends ListFragment {
    TextView balanceid;
    ArrayList<ListCoin> arrayListCoin;
    ListCoinAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stake, container, false);

        balanceid = (TextView) view.findViewById(R.id.balanceid);
        String Name = userName;
        String Key = privateKey;
        Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
        double balance2 = (double) balance/1000000;
        //String b = userName;
        balanceid.setText(String.valueOf(balance2));
        arrayListCoin = new ArrayList<>();
        arrayListCoin.add(new ListCoin("Contentos", String.valueOf(balance2), R.drawable.iconcos));
        arrayListCoin.add(new ListCoin("Vest", "Balance", R.drawable.iconcos));
        //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
        adapter = new ListCoinAdapter(getActivity(), R.layout.list_coin_view, arrayListCoin);
        setListAdapter(adapter);

        return view;
    }

}
