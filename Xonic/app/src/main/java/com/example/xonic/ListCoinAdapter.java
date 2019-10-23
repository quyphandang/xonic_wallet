package com.example.xonic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;


public class ListCoinAdapter extends BaseAdapter {
    //private Fragment fragment;
    private  Context context;
    private int layout;
    private List<ListCoin> listCoinList;

    public ListCoinAdapter(Context context, int layout, List<ListCoin> listCoinList) {
        //this.fragment = fragment;
        this.context = context;
        this.layout = layout;
        this.listCoinList = listCoinList;
    }

    @Override
    public int getCount() {
        return listCoinList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater = (LayoutInflater) FragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater = (LayoutInflater)
        //LayoutInflater inflater = (LayoutInflater) fragment.
        //getActivity().
        convertView = inflater1.inflate(layout, null);
        //TextView Name = (TextView) convertView.findViewById(R.id.nameid);
        TextView Name = (TextView) convertView.findViewById(R.id.nameid);
        TextView Balance = (TextView) convertView.findViewById(R.id.balance);
        ImageView IconCoin = (ImageView) convertView.findViewById(R.id.imageviewid);

        ListCoin listCoin = listCoinList.get(position);
        Name.setText(listCoin.getName());
        Balance.setText(listCoin.getBalance());
        IconCoin.setImageResource(listCoin.getIconCoin());


        return convertView;
    }
}
