package com.example.checkmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Five_Fragment extends Fragment {
    TextView textView5;
    Button button2, button3, button5;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_5, container, false);

        Bundle bundle = getArguments();
        String a = bundle.getString("So1");
        String b = bundle.getString("So2");

        textView5 = (TextView) view.findViewById(R.id.textView5);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button5 = (Button) view.findViewById(R.id.button5);
        textView5.setText(a);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Six_Fragment());
                fragmentTransaction.commit();
            }
        });

        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Seven_Fragment());
                fragmentTransaction.commit();
            }
        });

        button5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new One_Fragment());
                fragmentTransaction.commit();
            }
        });

//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        Five_Fragment five_fragment = new Five_Fragment();
//        //five_fragment.getArguments(bundle);
//
//        fragmentTransaction.replace(R.id.fragment_container, five_fragment);
//        fragmentTransaction.commit();
        return view ;
    }

}
