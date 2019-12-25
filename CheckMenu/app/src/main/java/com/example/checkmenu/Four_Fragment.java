package com.example.checkmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Four_Fragment extends Fragment {
    EditText editText4;
    Button button4;
    TextView textView4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        editText4 = (EditText) view.findViewById(R.id.editText);
        textView4 = (TextView) view.findViewById(R.id.textView4);

        Bundle truyen = this.getArguments();
        if (truyen != null ){
            textView4.setText("đúng rồi");
        }else{
            textView4.setText("trật rồi");
        }

        button4 = (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = editText4.getText().toString();
                Bundle bundle1 = new Bundle();
                bundle1.putString("Name", "Kutake");
                Six_Fragment six_fragment = new Six_Fragment();
                //five_fragment.getArguments(bundle);
                six_fragment.setArguments(bundle1);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Six_Fragment());
                //fragmentTransaction.remove(new One_Fragment());
                fragmentTransaction.commit();

            }
        });
        return view ;
    }
}
