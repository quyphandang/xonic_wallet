package com.example.xonic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class WithdrawInfoAdapter extends BaseAdapter {
    //private Fragment fragment;
    private  Context context;
    private int layout;
    private List<Withdraw_Info> listWithdrawinfo;

    public WithdrawInfoAdapter(Context context, int layout, List<Withdraw_Info> listWithdrawinfo) {
        //this.fragment = fragment;
        this.context = context;
        this.layout = layout;
        this.listWithdrawinfo = listWithdrawinfo;
    }

    @Override
    public int getCount() {
        return listWithdrawinfo.size() ;
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
        TextView Item = (TextView) convertView.findViewById(R.id.itemid);
        TextView Subitem = (TextView) convertView.findViewById(R.id.subitemid);

        Withdraw_Info withdrawinfo = listWithdrawinfo.get(position);
        Item.setText(withdrawinfo.getName());
        Subitem.setText(withdrawinfo.getBalance());


        return convertView;
    }
}
