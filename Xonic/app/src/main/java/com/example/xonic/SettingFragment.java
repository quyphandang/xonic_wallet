package com.example.xonic;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.MODE_PRIVATE;
//import static com.example.xonic.MainAccount.privateKey;
//import static com.example.xonic.MainAccount.userName;
import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;

public class SettingFragment extends ListFragment {
    ArrayList<SettingInfo> arrayListCoin;
    SettingInfoAdapter adapter;
    Button logoutid;
    SharedPreferences sharedPreferences, sharedPreferences1;
    TextView communityid;
    ClipboardManager clipboardManager;
    Switch sw;
    private long mLastClickTime = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        DespositFragment despositFragment = (DespositFragment) getActivity().getSupportFragmentManager().findFragmentByTag("DES");
        DespositWithdrawFragment despositWithdrawFragment = (DespositWithdrawFragment) getActivity().getSupportFragmentManager().findFragmentByTag("DWF");
        WithdrawFragment withdrawFragment = (WithdrawFragment) getActivity().getSupportFragmentManager().findFragmentByTag("WITH");
        WithdrawConformFragment withdrawConformFragment = (WithdrawConformFragment) getActivity().getSupportFragmentManager().findFragmentByTag("WDCF");
        PasswordFragment passwordFragment = (PasswordFragment) getActivity().getSupportFragmentManager().findFragmentByTag("SET");
        if (despositFragment != null){
            transaction.remove(despositFragment);
            transaction.commit();
        }else if (despositWithdrawFragment != null){
            transaction.remove(despositWithdrawFragment);
            transaction.commit();
        }else if (withdrawFragment != null){
            transaction.remove(withdrawFragment);
            transaction.commit();
        }else if (withdrawConformFragment != null){
            transaction.remove(withdrawConformFragment);
            transaction.commit();
        }else if (passwordFragment != null){
            transaction.remove(passwordFragment);
            transaction.commit();
        }else{

        }

        String publickey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));
        arrayListCoin = new ArrayList<>();
        arrayListCoin.add(new SettingInfo( "UserName", userName));
        arrayListCoin.add(new SettingInfo( "PrivateKey", privateKey));
        //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
        adapter = new SettingInfoAdapter(getActivity(), R.layout.setting_view, arrayListCoin);
        setListAdapter(adapter);
//        String copy = arrayListCoin.get(1).getIcon();
        TextView communityid = (TextView) view.findViewById(R.id.communityid);
        //TextView copyid = (TextView) view.findViewById(R.id.copyid);

        clipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
//        copyid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ClipData clipData = ClipData.newPlainText("PRIVATE", privateKey);
//                clipboardManager.setPrimaryClip(clipData);
//                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                    return;
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();
//                Toast.makeText( getActivity(), "Copy Succes",   Toast.LENGTH_SHORT).show();
//            }
//        });

        communityid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent();
             intent.setAction(Intent.ACTION_VIEW);
             intent.setData(Uri.parse("https://t.me/xonicwallet"));
             startActivity(intent);
            }
        });

        final Switch sw = (Switch) view.findViewById(R.id.switch1);

        //sharedPreferences = getSharedPreferences("saveAccount", MODE_PRIVATE);
        sharedPreferences1 = getActivity().getSharedPreferences("saveAccount", MODE_PRIVATE);
        Button logoutid = (Button) view.findViewById(R.id.logoutid);
        logoutid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.remove("username");
                editor.remove("privatekey");
                editor.commit();
                userName = "";
                privateKey = "";
                sw.setChecked(false);
                //sw.setChecked(false);
                Intent intent1 = new Intent(getActivity(), PrivateAndSucure.class );
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });

        //password

        sharedPreferences = getActivity().getSharedPreferences("pass", MODE_PRIVATE);
        String checkpass = sharedPreferences.getString("password","");
        int lengthcheck = checkpass.length();
        // Toast.makeText( MainActivity.this, String.valueOf(lengthcheck), Toast.LENGTH_SHORT).show();
        if (lengthcheck == 6){
            sw.setChecked(true);
        }

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    // boolean isChecKed = false;
                    sw.setChecked(false);
                    SavePass.checkPass = "";
                    SavePass.pass = "";
                    SavePass.next = 1;
                    SavePass.countPass = 0;
//                    Intent intent = new Intent(MainActivity.this, DatPass.class);
//                    startActivity(intent);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    //DespositW walletFragment = new WalletFragment();
                    //fragmentTransaction.replace(R.id.fragment_container, new PasswordFragment());
                    fragmentTransaction.add(R.id.fragment_container, new PasswordFragment(), "SET");
                    //fragmentTransaction.addToBackStack("Desposit and Withdraw");
                    fragmentTransaction.commit();
                } else {
                    //Toast.makeText( MainActivity.this, "Please enter Amount!", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("password");
                    editor.commit();
                }
            }
        });


        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        if (position == 0){
            ClipData clipData = ClipData.newPlainText("USERNAME", userName);
            clipboardManager.setPrimaryClip(clipData);
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            Toast.makeText( getActivity(), "Copy Username Succes",   Toast.LENGTH_SHORT).show();
            //Toast.makeText(getActivity(),"vi tri 0",Toast.LENGTH_SHORT).show();
        }else if(position == 1){
            ClipData clipData = ClipData.newPlainText("PRIVATE", privateKey);
            clipboardManager.setPrimaryClip(clipData);
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            Toast.makeText( getActivity(), "Copy Privatekey Succes",   Toast.LENGTH_SHORT).show();
            //Toast.makeText(getActivity(),"vi tri 1",Toast.LENGTH_SHORT).show();
        }
        super.onListItemClick(l, v, position, id);
    }

}
