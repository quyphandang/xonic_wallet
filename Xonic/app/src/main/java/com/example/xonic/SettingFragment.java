package com.example.xonic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

import io.contentos.android.sdk.crypto.Key;
import io.contentos.android.sdk.encoding.WIF;

import static android.content.Context.MODE_PRIVATE;
import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;

public class SettingFragment extends ListFragment {
    ArrayList<SettingInfo> arrayListCoin;
    SettingInfoAdapter adapter;
    Button logoutid;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        String publickey = WIF.fromPublicKey(Key.publicKeyOf(WIF.toPrivateKey(privateKey)));
        arrayListCoin = new ArrayList<>();
        arrayListCoin.add(new SettingInfo( "UserName", userName));
        arrayListCoin.add(new SettingInfo( "PrivateKey", privateKey));
        arrayListCoin.add(new SettingInfo( "PublicKey", publickey));
        //adapter = new ListCoinAdapter(getActivity(),android.R.layout.simple_list_item_1, arrayListCoin);
        adapter = new SettingInfoAdapter(getActivity(), R.layout.setting_view, arrayListCoin);
        setListAdapter(adapter);

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
                startActivity(intent1);
            }
        });

        return view;
    }

}
