package com.example.checkfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    EditText editText;
    Button button2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //balanceid = (TextView) view.findViewById(R.id.balanceid);
        //balanceid.setText("Dev Contentos!");
        editText = (EditText) view.findViewById(R.id.editText);
        button2 = (Button) view.findViewById(R.id.button2);


        return view ;


    }
}
