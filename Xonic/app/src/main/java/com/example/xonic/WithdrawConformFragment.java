package com.example.xonic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import static com.example.xonic.MainAccount.privateKey;
import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.MainAccount.wallet;

public class WithdrawConformFragment extends ListFragment {
    //TextView userreceive, amount, memoid;
    Button conformid, backid;
    ArrayList<Withdraw_Info> arrayListCoin;
    WithdrawInfoAdapter adapter;
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
        View view = inflater.inflate(R.layout.fragment_withdraw_conform, container, false);
//        userreceive = (TextView) view.findViewById(R.id.userreceive);
//        amount = (TextView) view.findViewById(R.id.amount);
//        memoid = (TextView) view.findViewById(R.id.memoid);
        conformid = (Button) view.findViewById(R.id.conformid);
        backid = (Button) view.findViewById(R.id.backid);
        final String Userreceive = getArguments().getString("UserReceive");
        final String Amount = getArguments().getString("Amount");
        final String Memo = getArguments().getString("Memo");

        arrayListCoin = new ArrayList<>();
        arrayListCoin.add(new Withdraw_Info("User Name Receive", Userreceive));
        arrayListCoin.add(new Withdraw_Info("Amount", Amount));
        arrayListCoin.add(new Withdraw_Info("Memo", Memo));
        //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
        adapter = new WithdrawInfoAdapter(getActivity(), R.layout.withdraw_view, arrayListCoin);
        setListAdapter(adapter);
    //        userreceive.setText(Userreceive);
    //        amount.setText(Amount);
    //        memoid.setText(Memo);

        backid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new WithdrawFragment());
                fragmentTransaction.commit();
            }
        });

        conformid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String USER = Userreceive;
                String AMOUNT = Amount;
                double AMOUNTTRANSFER1 = Double.parseDouble(AMOUNT)*1000000;
                long AMOUNTTRANSFER = (long) AMOUNTTRANSFER1;
                String MEMO = Memo;
                wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
                wallet.addKey(userName,privateKey);
                //convert cos to vet
               // wallet.account("quyphancos").transferToVest("quyphancos","quyphancos",1000000,"test");
 //               try {
 //                wallet.account("quyphancos").convertVest("quyphancos",AMOUNTTRANSFER);
//                } catch (Exception e) {
//                    Toast.makeText( getActivity(), String.valueOf(AMOUNTTRANSFER),   Toast.LENGTH_SHORT).show();
//                }

                wallet.account(userName).transfer(
                        userName,
                        USER,
                        AMOUNTTRANSFER,
                        MEMO
                );
                Toast.makeText(getActivity(), "Transfer Success!", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new DespositWithdrawFragment());
                fragmentTransaction.commit();


            }
        });
        return view;
    }

}
