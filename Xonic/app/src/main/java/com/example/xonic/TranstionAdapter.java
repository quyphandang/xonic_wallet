package com.example.xonic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class TranstionAdapter extends BaseAdapter {
    //private Fragment fragment;
    private  Context context;
    private int layout;
    private List<Transtion> listTranstion;

    public TranstionAdapter(Context context, int layout, List<Transtion> listTranstion) {
        //this.fragment = fragment;
        this.context = context;
        this.layout = layout;
        this.listTranstion = listTranstion;
    }

    @Override
    public int getCount() {
        return listTranstion.size() ;
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
        TextView Day = (TextView) convertView.findViewById(R.id.dayid);
        TextView Status = (TextView) convertView.findViewById(R.id.status);
        TextView Transtion = (TextView) convertView.findViewById(R.id.transtion);
        TextView Amount = (TextView) convertView.findViewById(R.id.amount);
        ImageView Imageicon = (ImageView) convertView.findViewById(R.id.imageicon);
        Transtion transtion = listTranstion.get(position);
        Day.setText(transtion.getDay());
        Status.setText(transtion.getStatus());
        Transtion.setText(transtion.getTranstion());
        Amount.setText(transtion.getAmount());
        Imageicon.setImageResource(transtion.getIconTranstion());
        return convertView;
    }
}
