package com.learnadroid.myfirstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by QUANPN on 5/28/2018.
 */

public class customlistviewBaocao extends BaseAdapter {
    private List<lisviewBaocao> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public customlistviewBaocao(Context aContext,  List<lisviewBaocao> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        customlistviewBaocao.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_baocao, null);
            holder = new customlistviewBaocao.ViewHolder();
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.countryNameView = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.populationView = (TextView) convertView.findViewById(R.id.textViewMagiaodich);
            holder.Cohieu = (TextView) convertView.findViewById(R.id.textView7);
            convertView.setTag(holder);
        } else {
            holder = (customlistviewBaocao.ViewHolder) convertView.getTag();
        }

        lisviewBaocao country = this.listData.get(position);
        holder.countryNameView.setText(country.getCountryName());
        holder.populationView.setText(country.getPopulation());
        holder.Cohieu.setText(country.getCohieu());
        int imageId = this.getMipmapResIdByName(country.getFlagName());
        holder.flagView.setImageResource(imageId);

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView flagView;
        TextView countryNameView;
        TextView populationView;
        TextView Cohieu;
    }
}
