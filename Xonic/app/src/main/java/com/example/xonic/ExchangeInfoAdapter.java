package com.example.xonic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ExchangeInfoAdapter extends BaseAdapter {
    //private Fragment fragment;
    private  Context context;
    private int layout;
    private List<ExchangeInfo> listExchangeinfo;

    public ExchangeInfoAdapter(Context context, int layout, List<ExchangeInfo> listExchangeinfo) {
        //this.fragment = fragment;
        this.context = context;
        this.layout = layout;
        this.listExchangeinfo = listExchangeinfo;
    }

    @Override
    public int getCount() {
        return listExchangeinfo.size() ;
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
        EditText Amount = (EditText) convertView.findViewById(R.id.amount1);
        TextView Balance = (TextView) convertView.findViewById(R.id.balance1);
        ImageView IconCoin = (ImageView) convertView.findViewById(R.id.imageicon);
        TextView Icon = (TextView) convertView.findViewById(R.id.icon);
        String aaa = Amount.getText().toString();
        ExchangeInfo exchangeinfo = listExchangeinfo.get(position);
        Amount.setText(exchangeinfo.getName());
        Balance.setText(exchangeinfo.getBalance());
        IconCoin.setImageResource(exchangeinfo.getIconCoin());
        Icon.setText(exchangeinfo.getIcon());


        return convertView;
    }
}
