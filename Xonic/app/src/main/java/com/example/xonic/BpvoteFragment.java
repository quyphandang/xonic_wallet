package com.example.xonic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.contentos.android.sdk.rpc.Grpc;

import static android.content.Context.MODE_PRIVATE;
import static com.example.xonic.Balancecos.balance2;
import static com.example.xonic.Global.balance2stake;
import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;
import static com.example.xonic.MainAccount.wallet;


public class BpvoteFragment extends Fragment {
    private static final String keyStorePassword = "my password";
    private static File getKeyStoreFile() {
        File file = null;
        try {
            file = Files.createTempDirectory("keystoreTestDir").resolve("keystoreFile").toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    ListView listviewbpvote;
    ArrayList<BpVote> arrayBpVote;
    BpVoteAdapter adapter;
    SharedPreferences sharedPreferences, sharedPreferences1;
    private long mLastClickTime = 0;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bpvote, container, false);

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
    //    long millis1 = System.currentTimeMillis();
//        int a = wallet.getBlockProducerList().getBlockProducerListCount();
//        long millis2 = System.currentTimeMillis();
//        long distance = millis2 - millis1;
        //Toast.makeText(getActivity(), "Time getBlockProducerList: " + String.valueOf(distance) + " Số lượng phần tử" + String.valueOf(a) , Toast.LENGTH_SHORT).show();
        //image swap
        listviewbpvote = (ListView) view.findViewById(R.id.listviewbpvote);
        arrayBpVote = new ArrayList<>();
        adapter = new BpVoteAdapter(getActivity(), R.layout.bpvote_view, arrayBpVote);
        listviewbpvote.setAdapter(adapter);
        sharedPreferences1 = getActivity().getSharedPreferences("checkArray", MODE_PRIVATE);
        int b = sharedPreferences1.getInt("size", 0);
        if(b == 0){
            int a = wallet.getBlockProducerList().getBlockProducerListCount();
            ShowList(a);
        }else{
            int a =b;
            ShowList(a);
        }
//        long millis2 = System.currentTimeMillis();
//        long distance = millis2 - millis1;
//        Toast.makeText(getActivity(), "Time : " + String.valueOf(distance), Toast.LENGTH_SHORT).show();
        //image swap
        return view;

    }
    public void ShowList(int a){
        arrayBpVote.clear();
        String [] one =  new String [a];
        int [] vote = new int [a];
        double [] vestvoteint = new double [a];
        sharedPreferences1 = getActivity().getSharedPreferences("checkArray", MODE_PRIVATE);
       // String check = sharedPreferences1.getString("one_1","");
        int check  = sharedPreferences1.getInt("size", 0);
        if(check == 0){
            List<Grpc.BlockProducerResponse> listBroducer = wallet.getBlockProducerList().getBlockProducerListList();
            for(int i = 0; i <= a; i++){
                try {
                    one[i] = listBroducer.get(i).getOwner().getValue();
                    vote[i] = (int) listBroducer.get(i).getVoterCount();
                    long vestvote = listBroducer.get(i).getBpVest().getVoteVest().getValue();
                    vestvoteint[i] = (double) vestvote/1000000;
                }catch(Exception e){
                    //Toast.makeText(getActivity(), String.valueOf(i), Toast.LENGTH_LONG).show();
                }
            }
            //Toast.makeText(getActivity(), "Khong", Toast.LENGTH_LONG).show();
        }else{
            for(int i=0;i<a;i++)
            {
                one[i]= sharedPreferences1.getString("one_" + i, "");
                vote[i]= sharedPreferences1.getInt("vote_" + i, 0);
                long vestvote = sharedPreferences1.getLong("vestvoteinta_" + i, 0);
                vestvoteint[i] = (double) vestvote/1000000;
                //sKey.add(mSharedPreference1.getString("Status_" + i, null));
                //Toast.makeText(getActivity(), "one_" + i, Toast.LENGTH_LONG).show();
            }
        }


        double temp = vestvoteint[0];
        String tempone = one[0];
        int tempint = vote[0];
        for(int x = 0; x < one.length - 1;x++){
            for(int y = x + 1; y < one.length; y++){
                if(vestvoteint[x] < vestvoteint[y]){
                    temp = vestvoteint[y];
                    vestvoteint[y]=vestvoteint[x];
                    vestvoteint[x]=temp;

                    tempone = one[y];
                    one[y]=one[x];
                    one[x]=tempone;

                    tempint = vote[y];
                    vote[y]=vote[x];
                    vote[x]=tempint;
                }
            }
        }

        for(int x=0; x<27;x++){
            sharedPreferences = getActivity().getSharedPreferences("CheckVote", MODE_PRIVATE);
            try{
                String text1 = sharedPreferences.getString("text","");
                String Namebp = sharedPreferences.getString("namebp","");
                if (text1.trim().equals("UnVote")){

                    if (Namebp.trim().equals(one[x])){
                        arrayBpVote.add(new BpVote(one[x],vote[x] ,vestvoteint[x], "UnVote"));
                    }else {
                        arrayBpVote.add(new BpVote(one[x],vote[x] ,vestvoteint[x], "Vote"));
                    }
//                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                        return;
//                    }
//                    mLastClickTime = SystemClock.elapsedRealtime();
                    //Toast.makeText(getActivity(), "Vao nay",Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(getActivity(), String.valueOf(x),Toast.LENGTH_SHORT).show();
                    arrayBpVote.add(new BpVote(one[x],vote[x] ,vestvoteint[x], "Vote"));
                }
                //arrayBpVote.add(new BpVote(one[x],vote[x] ,vestvoteint[x], "Vote"));
                //arraytotal[x,one[x],vote[x] ,vestvoteint[x]];
               // arrayBpVote.add(new BpVote(x,one[x],vote[x] ,vestvoteint[x]));


            }catch(Exception e){

            }
        }

//        }
        adapter.notifyDataSetChanged();
//        long millis3 = System.currentTimeMillis();
//        long distance1 = millis2 - millis1;
//        Toast.makeText(getActivity(), "Time ShowList: " + String.valueOf(distance) + " Time 2: " + String.valueOf(distance1), Toast.LENGTH_SHORT).show();
        //image swap
    }
}
