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

import static com.example.xonic.MainAccount.privateKey;
import static com.example.xonic.MainAccount.userName;


//public class WalletFragment extends Fragment {
public class WalletFragment extends ListFragment {
    TextView balanceid;
    ArrayList<ListCoin> arrayListCoin;
    ListCoinAdapter adapter;
    //public static String balance2 =


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        balanceid = (TextView) view.findViewById(R.id.balanceid);
        String Name = userName;
        String Key = privateKey;
        Wallet wallet = new Wallet("34.195.63.116", 8888, "test");
        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
        double balance2 = (double) balance/1000000;
//        String balance2 = getArguments().getString("Balance");
//        String UserName = getArguments().getString("Username");
//        String PrivateKey = getArguments().getString("Privatekey");
        //String b = userName;
        balanceid.setText(String.valueOf(balance2));
        //xml listview: @+id/listcoinviewid
        //listcoinviewid = (ListView) view.findViewById(R.id.listcoinviewid);
            arrayListCoin = new ArrayList<>();
            arrayListCoin.add(new ListCoin("Contentos", String.valueOf(balance2), R.drawable.iconcos));
            arrayListCoin.add(new ListCoin("Vest", "Balance", R.drawable.iconcos));
            //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
            adapter = new ListCoinAdapter(getActivity(), R.layout.list_coin_view, arrayListCoin);
            setListAdapter(adapter);

        return view ;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        //Toast.makeText(getActivity() , "User Name is incorrect" , Toast.LENGTH_SHORT).show();
//        Bundle bundle_to_desposit_withdraw = new Bundle();
//        bundle_to_desposit_withdraw.putString("UserName", getArguments().getString("Username"));
//        bundle_to_desposit_withdraw.putString("PrivateKey", getArguments().getString("Privatekey"));
//        bundle_to_desposit_withdraw.putString("Balance", getArguments().getString("Balance"));
////
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DespositWithdrawFragment despositWithdrawFragment = new DespositWithdrawFragment();
//        despositWithdrawFragment.setArguments(bundle_to_desposit_withdraw);
        fragmentTransaction.replace(R.id.fragment_container, despositWithdrawFragment);
////        //fragmentTransaction.addToBackStack("Desposit and Withdraw");
        fragmentTransaction.commit();

        super.onListItemClick(l, v, position, id);
    }
}
