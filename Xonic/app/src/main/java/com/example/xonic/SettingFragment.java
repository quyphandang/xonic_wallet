package com.example.xonic;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
    SharedPreferences sharedPreferences;
    TextView communityid, copyid;
    ClipboardManager clipboardManager;
    Switch sw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        String publickey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));
        arrayListCoin = new ArrayList<>();
        arrayListCoin.add(new SettingInfo( "UserName", userName));
        arrayListCoin.add(new SettingInfo( "PrivateKey", privateKey));
        //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
        adapter = new SettingInfoAdapter(getActivity(), R.layout.setting_view, arrayListCoin);
        setListAdapter(adapter);

        TextView communityid = (TextView) view.findViewById(R.id.communityid);
        TextView copyid = (TextView) view.findViewById(R.id.copyid);
        clipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        copyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("PRIVATE", privateKey);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText( getActivity(), "Copy Succes",   Toast.LENGTH_SHORT).show();
            }
        });

        communityid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent();
             intent.setAction(Intent.ACTION_VIEW);
             intent.setData(Uri.parse("https://t.me/xonicwallet"));
             startActivity(intent);
            }
        });

        //sharedPreferences = getSharedPreferences("saveAccount", MODE_PRIVATE);
        sharedPreferences = getActivity().getSharedPreferences("saveAccount", MODE_PRIVATE);
        Button logoutid = (Button) view.findViewById(R.id.logoutid);
        logoutid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.remove("privatekey");
                editor.commit();
                userName = "";
                privateKey = "";
                Intent intent1 = new Intent(getActivity(), PrivateAndSucure.class );
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });

        //password
        final Switch sw = (Switch) view.findViewById(R.id.switch1);
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
                    fragmentTransaction.replace(R.id.fragment_container, new PasswordFragment());
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

}
