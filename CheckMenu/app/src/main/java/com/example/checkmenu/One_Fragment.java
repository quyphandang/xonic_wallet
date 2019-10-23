package com.example.checkmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class One_Fragment extends Fragment {
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_1, container, false);


        button = (Button) v.findViewById(R.id.button);
       // button.setOnClickListener(new View.OnClickListener());
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("So1", "Cộng Hoà Xã Hội Chủ Nghĩa Việt Nam!");
                bundle.putString("So2", "Thông tin thông báo số 2");

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Five_Fragment five_fragment = new Five_Fragment();
                //five_fragment.getArguments(bundle);
                five_fragment.setArguments(bundle);

                //fragmentTransaction.replace(R.id.fragment_container, five_fragment);
                fragmentTransaction.replace(R.id.fragment_container, five_fragment);
                //fragmentTransaction.remove(new One_Fragment());
                fragmentTransaction.commit();
            }
        });
        return v ;
    }
}
