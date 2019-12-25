package com.example.xonic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import static com.example.xonic.MainAccount.wallet;
import static com.example.xonic.Global.privateKey;
import static com.example.xonic.Global.userName;

import static android.content.Context.MODE_PRIVATE;

public class BpVoteAdapter extends BaseAdapter {
    private long mLastClickTime = 0;
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
    //private Fragment fragment;
    private  Context context;
    private int layout;
    private List<BpVote> listBpvote;
    int xyz = 1;
    SharedPreferences sharedPreferences;
    public BpVoteAdapter(Context context, int layout, List<BpVote> listBpvote) {
        //this.fragment = fragment;
        this.context = context;
        this.layout = layout;
        this.listBpvote = listBpvote;
    }

    @Override
    public int getCount() {
        return listBpvote.size() ;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView name, votes, vestvote;
        Button buttonvote;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.votes = (TextView) convertView.findViewById(R.id.votes);
            holder.vestvote = (TextView) convertView.findViewById(R.id.vestvote);
            holder.buttonvote = (Button) convertView.findViewById(R.id.buttonvote);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final BpVote bpvote = listBpvote.get(position);
        sharedPreferences = context.getSharedPreferences("CheckVote", MODE_PRIVATE);
        holder.name.setText(bpvote.getName());
        holder.votes.setText("" + bpvote.getVotes());
        holder.vestvote.setText("" + bpvote.getVestvote());
      //  holder.buttonvote.setText(bpvote.getTextbutton());
        final String text1 = sharedPreferences.getString("text","");
        //final int number2 = sharedPreferences.getInt("number",0);
        final String Namebp = sharedPreferences.getString("namebp","");
        String a = bpvote.getName();
        if(text1.equals("UnVote")){
            if (Namebp.equals(a)){
                holder.buttonvote.setText("UnVote");
                //Toast.makeText(context,"VIP", Toast.LENGTH_LONG).show();
               // holder.votes.setText("" + Votes.b);
            } else{
                holder.buttonvote.setText("Vote");
               // holder.votes.setText("" + bpvote.getVotes());
            }
        }else{
            holder.buttonvote.setText("Vote");
           // holder.votes.setText("" + bpvote.getVotes());
        }

       // Toast.makeText(context, String.valueOf(number), Toast.LENGTH_LONG).show();
        wallet.openKeyStore(getKeyStoreFile(), keyStorePassword);
        wallet.addKey(userName,privateKey);

        holder.buttonvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.buttonvote.getText();
                final String text2 = sharedPreferences.getString("text","");
                //final int number1 = sharedPreferences.getInt("number",0);
                final String Namebp1 = sharedPreferences.getString("namebp","");
                if(text2.trim().isEmpty()){
                    wallet.account(userName).bpVote(userName,bpvote.getName(),false);
                    holder.buttonvote.setText("UnVote");
                    String a = holder.buttonvote.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("text", a);
                    editor.putString("namebp",bpvote.getName());
                    //editor.putInt("number",bpvote.getId());
                    editor.commit();

                }else if (text2.trim().equals("UnVote")){
                    if(Namebp1.equals(bpvote.getName())){
                    // if (number1 == bpvote.getId()){
                        wallet.account(userName).bpVote(userName,bpvote.getName(),true);
                        holder.buttonvote.setText("Vote");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("text");
                        editor.remove("namebp");
                        editor.commit();
                    }else{
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                            return;
                        }
                        mLastClickTime = SystemClock.elapsedRealtime();
                        Toast.makeText(context,"You voted for " + Namebp1 ,Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        return convertView;
    }

}
