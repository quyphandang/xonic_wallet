package com.example.xonic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.MainAccount.wallet;

public class DespositWithdrawFragment extends Fragment {
    Button backid, despositid, withdrawid;
    TextView balance_desposit_withdraw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desposit_withdraw, container, false);
       // balance_desposit_withdraw = (TextView) view.findViewById(R.id.balance_desposit_withdraw);
        backid = (Button) view.findViewById(R.id.backid);
        backid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().getSupportFragmentManager().popBackStack();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                WalletFragment walletFragment = new WalletFragment();
                fragmentTransaction.replace(R.id.fragment_container, walletFragment);
//                //fragmentTransaction.addToBackStack("Desposit and Withdraw");
                fragmentTransaction.commit();
//                Toast.makeText(getActivity() , "User Name is incorrect" , Toast.LENGTH_SHORT).show();
            }
        });
        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
        double balance2 = (double) balance/1000000;
        balance_desposit_withdraw = (TextView) view.findViewById(R.id.balance_desposit_withdraw);
        balance_desposit_withdraw.setText(String.valueOf(balance2));

        despositid = (Button) view.findViewById(R.id.despositid);
        despositid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new DespositFragment());
                fragmentTransaction.commit();
            }
        });
        withdrawid = (Button) view.findViewById(R.id.withdrawid);
        withdrawid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new WithdrawFragment());
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}
