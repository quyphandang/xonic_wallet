package com.example.xonic;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class SettingInfoAdapter extends BaseAdapter {
    //private Fragment fragment;
    private  Context context;
    private int layout;
    private List<SettingInfo> listSettinginfo;

    public SettingInfoAdapter(Context context, int layout, List<SettingInfo> listSettinginfo) {
        //this.fragment = fragment;
        this.context = context;
        this.layout = layout;
        this.listSettinginfo = listSettinginfo;
    }

    @Override
    public int getCount() {
        return listSettinginfo.size() ;
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
        TextView Infoacc = (TextView) convertView.findViewById(R.id.infoid);
        //String aaa = Amount.getText().toString();
        SettingInfo settinginfo = listSettinginfo.get(position);
        Name.setText(settinginfo.getBalance());
        Infoacc.setText(settinginfo.getIcon());
        return convertView;
    }
}
