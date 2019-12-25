package com.example.xonic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.contentos.android.sdk.prototype.Operation;
import io.contentos.android.sdk.prototype.Transaction;
import io.contentos.android.sdk.prototype.Type;
import io.contentos.android.sdk.rpc.Grpc;
import io.contentos.android.sdk.rpc.RpcResultPages;

import static com.example.xonic.Global.userName;
import static com.example.xonic.MainAccount.wallet;

public class DespositWithdrawFragment extends ListFragment {
    Button backid, despositid, withdrawid;
    TextView balance_desposit_withdraw;
    ArrayList<Transtion> arrayTranstion;
    TranstionAdapter adapter;
<<<<<<< HEAD
    LinearLayout maindeswith;
=======
>>>>>>> c68ef5f1fcf79d836162ea73097f8dd015eb4071
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desposit_withdraw, container, false);
        maindeswith = (LinearLayout) view.findViewById(R.id.maindeswith);
        maindeswith.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // balance_desposit_withdraw = (TextView) view.findViewById(R.id.balance_desposit_withdraw);
//        backid = (Button) view.findViewById(R.id.backid);
//        backid.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //getActivity().getSupportFragmentManager().popBackStack();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                WalletFragment walletFragment = new WalletFragment();
//                fragmentTransaction.replace(R.id.fragment_container, walletFragment);
////                //fragmentTransaction.addToBackStack("Desposit and Withdraw");
//                fragmentTransaction.commit();
////                Toast.makeText(getActivity() , "User Name is incorrect" , Toast.LENGTH_SHORT).show();
//            }
//        });
        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
        double balance2 = (double) balance/1000000;
        balance_desposit_withdraw = (TextView) view.findViewById(R.id.balance_desposit_withdraw);
        balance_desposit_withdraw.setText(String.valueOf(balance2) + " COS");

        despositid = (Button) view.findViewById(R.id.despositid);
        despositid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.replace(R.id.fragment_container, new DespositFragment());
                fragmentTransaction.add(R.id.fragment_container, new DespositFragment(), "DES");
                fragmentTransaction.commit();
            }
        });
        withdrawid = (Button) view.findViewById(R.id.withdrawid);
        withdrawid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.replace(R.id.fragment_container, new WithdrawFragment());
                fragmentTransaction.add(R.id.fragment_container, new WithdrawFragment(), "WITH");
                fragmentTransaction.commit();
            }
        });

        //Transtion
        arrayTranstion = new ArrayList<>();
        //arrayTranstion.add(new Transtion("dưe", R.drawable.icon_cos2,"ffrffr","3uXkdUTCdMNFEDoGcqrVeuSbGCv4ZcUndTYMjFnU7SjaDN597q","dđ"));
        adapter = new TranstionAdapter(getActivity(), R.layout.transtion_view, arrayTranstion);
        setListAdapter(adapter);
        RpcResultPages<Grpc.GetUserTrxListByTimeResponse, Type.time_point_sec, Grpc.TrxInfo> pages;
        Grpc.GetUserTrxListByTimeResponse page;
        pages = wallet.getUserTrxListByTime(
                userName,
                0,
                Integer.MAX_VALUE,
                10);
        // query the result page by page
        while ((page = pages.nextPage()) != null) {
            // go over each transaction in current page
            for (Grpc.TrxInfo trxInfo :page.getTrxListList()) {
                // do something with current transaction, e.g. output its block height and status
                //System.out.printf("block_height:%d, status:%d\n", trxInfo.getBlockHeight(), trxInfo.getTrxWrap().getReceipt().getStatus());
                // get the raw transaction
                Transaction.transaction trx = trxInfo.getTrxWrap().getSigTrx().getTrx();
               // trxInfo.getBlockTime().getUtcSeconds();
                // go over operations inside the transaction
                for (Transaction.operation op : trx.getOperationsList()) {
                    // we care about transfer operations only, which has a type value of 2.
                    if (op.getOpCase().getNumber() == 2) {
                        // convert the generic operation instance to a transfer operation.
                        Operation.transfer_operation transfer = op.getOp2();
                        // output details of the transfer
                        String from = transfer.getFrom().getValue();
                        String to = transfer.getTo().getValue();
                        long Amount = transfer.getAmount().getValue();
                        double amount = (double) Amount;
                        double amount1 = amount/1000000;
                        arrayTranstion.add(new Transtion("Day", R.drawable.ic_transfer_24dp,"Send","To " + to,"Amount " + String.valueOf(amount1)));
                        adapter.notifyDataSetChanged();
                        //System.out.printf("transfer: from %s to %s, amount %d\n", transfer.getFrom().getValue(), transfer.getTo().getValue(), transfer.getAmount().getValue());
                    }
                }
            }
        }
        return view;
    }

}
