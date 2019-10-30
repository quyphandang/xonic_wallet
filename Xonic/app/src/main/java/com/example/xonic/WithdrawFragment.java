package com.example.xonic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import org.json.JSONException;
import org.json.JSONObject;

import io.contentos.android.sdk.encoding.WIF;

//import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.Global.userName;
import static com.example.xonic.MainAccount.wallet;

public class WithdrawFragment extends Fragment {
    EditText userreceive, amount, memoid;
    Button backid, allbalance, nextid;
    ImageView imageqr;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_withdraw, container, false);
        userreceive = (EditText) view.findViewById(R.id.userreceive);
        amount = (EditText) view.findViewById(R.id.amount);
        memoid = (EditText) view.findViewById(R.id.memoid);
        backid = (Button) view.findViewById(R.id.backid);
        allbalance = (Button) view.findViewById(R.id.allbalance);
        nextid = (Button) view.findViewById(R.id.nextid);
        imageqr = (ImageView) view.findViewById(R.id.imageqr);

        backid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //DespositW walletFragment = new WalletFragment();
                fragmentTransaction.replace(R.id.fragment_container, new DespositWithdrawFragment());
                //fragmentTransaction.addToBackStack("Desposit and Withdraw");
                fragmentTransaction.commit();
            }
        });

        //ImageView check QR
        final IntentIntegrator intenIntegrator = new IntentIntegrator(getActivity());
        imageqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intenIntegrator.initiateScan();
                //Toast.makeText(getActivity(), "Đúng ", Toast.LENGTH_SHORT).show();
            }
        });


        //Button All Balance
        allbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
                double balance2 = (double) balance/1000000;
                amount.setText(String.valueOf(balance2));
            }
        });
        nextid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userReceive = userreceive.getText().toString();
                String Amount = amount.getText().toString();
                String Memo = memoid.getText().toString();
                //amount.getText().
                int lenght = userReceive.length();
                if ( lenght != 0){
                    //Toast.makeText( getActivity(), userReceive ,   Toast.LENGTH_SHORT).show();
                    try {
                        String inpublicKeyAccount = WIF.fromPublicKey(wallet.getAccountByName(userReceive).getInfo().getPublicKey());
                        //Toast.makeText( getActivity(), "UserName !",   Toast.LENGTH_SHORT).show();
                        long balance = wallet.getAccountByName(userName).getInfo().getCoin().getValue();
                        int lenghtAmount = Amount.length();
                        if (lenghtAmount != 0) {

                        double amountCheck = Double.parseDouble(Amount) * 1000000;

                        if (amountCheck < 1) {
                            Toast.makeText(getActivity(), "The transaction amount is lower than the minimum. Please enter again!", Toast.LENGTH_SHORT).show();
                        } else {
                            double balance2 = (double) balance;
                            double balanceCheck = balance2 - amountCheck;
                            if (balanceCheck < 0) {
                                Toast.makeText(getActivity(), "Current balance is not sufficient!", Toast.LENGTH_SHORT).show();
                            } else {
                                Bundle Withdraw_Conform = new Bundle();
                                Withdraw_Conform.putString("UserReceive", userReceive);
                                Withdraw_Conform.putString("Amount", Amount);
                                Withdraw_Conform.putString("Memo", Memo);
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                WithdrawConformFragment withdrawConformFragment = new WithdrawConformFragment();
                                withdrawConformFragment.setArguments(Withdraw_Conform);
                                fragmentTransaction.replace(R.id.fragment_container, withdrawConformFragment);
                                fragmentTransaction.commit();
                                //Toast.makeText(getActivity(), String.valueOf(amountCheck), Toast.LENGTH_SHORT).show();
                            }
                        }
                      }else {
                            Toast.makeText(getActivity(), "Please enter Amount!", Toast.LENGTH_SHORT).show();
                        }
                        //if ()
                        //double checkBalence = balance - Amount*
                    }catch(Exception e){
                        Toast.makeText( getActivity(), "Account does not exist!",   Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText( getActivity(), "Please enter UserName!",   Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//               // Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//           } else {
//                //Picasso.with(getActivity()).load(result.getContents()).into();
//                Picasso.get().load(result.getContents()).into();
//                try {
//                    JSONObject jsonObject = new JSONObject(result.getContents());
//                    userreceive.setText(jsonObject.getString("fefefe"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
}
