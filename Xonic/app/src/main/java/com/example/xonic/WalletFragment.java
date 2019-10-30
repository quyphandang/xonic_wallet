package com.example.xonic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import io.contentos.android.sdk.Wallet;

import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;
//import static com.example.xonic.MainAccount.privateKey;
//import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.MainAccount.wallet;
import com.example.xonic.MainAccount;

//public class WalletFragment extends Fragment {
public class WalletFragment extends ListFragment {
    TextView balanceid, usernameid;
    ArrayList<ListCoin> arrayListCoin;
    ListCoinAdapter adapter;
    //public static String balance2 =


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        balanceid = (TextView) view.findViewById(R.id.balanceid);
        String Name = userName;
        //String
        //String Name1 = user;
        //MainAccount
        String Key = privateKey;
        Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
        double balance2 = (double) balance/1000000;

        long balancevest = wallet.getAccountByName(userName).getInfo().getVest().getValue();
        double balance2vest = (double) balancevest/1000000;

        long balancestake = wallet.getAccountByName(userName).getInfo().getStakeVestFromMe().getValue();
        double balance2stake = (double) balancestake/1000000;
//        String balance2 = getArguments().getString("Balance");
//        String UserName = getArguments().getString("Username");
//        String PrivateKey = getArguments().getString("Privatekey");
        //String b = userName;
        balanceid.setText(String.valueOf(balance2) + " COS");

        usernameid = (TextView) view.findViewById(R.id.usernameid);
        usernameid.setText(userName);
        //xml listview: @+id/listcoinviewid
        //listcoinviewid = (ListView) view.findViewById(R.id.listcoinviewid);
            arrayListCoin = new ArrayList<>();
            arrayListCoin.add(new ListCoin("Contentos", String.valueOf(balance2), R.drawable.icon_cos2));
            arrayListCoin.add(new ListCoin("Vest", String.valueOf(balance2vest), R.drawable.icon_vest));
            arrayListCoin.add(new ListCoin("Chicken", String.valueOf(balance2stake), R.drawable.icon_stake));
            //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
            adapter = new ListCoinAdapter(getActivity(), R.layout.list_coin_view, arrayListCoin);
            setListAdapter(adapter);

        return view ;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        if (position == 0){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DespositWithdrawFragment despositWithdrawFragment = new DespositWithdrawFragment();
        fragmentTransaction.replace(R.id.fragment_container, despositWithdrawFragment);
        fragmentTransaction.commit();
        }else if(position == 1){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
           // DespositWithdrawFragment despositWithdrawFragment = new DespositWithdrawFragment();
            fragmentTransaction.replace(R.id.fragment_container, new ExchangeFragment());
            fragmentTransaction.commit();
        }else {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // DespositWithdrawFragment despositWithdrawFragment = new DespositWithdrawFragment();
            fragmentTransaction.replace(R.id.fragment_container, new StakeFragment());
            fragmentTransaction.commit();
        }

        super.onListItemClick(l, v, position, id);
    }
}
