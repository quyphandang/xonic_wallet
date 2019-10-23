package com.example.checkmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Six_Fragment extends Fragment {
    TextView textView6;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_6, container, false);

        textView6 = (TextView) view.findViewById(R.id.textView6);

        Bundle bundle1 = getArguments();
        if(bundle1 != null){
            textView6.setText(bundle1.getString("Name"));
        }
        return view ;
    }
}
