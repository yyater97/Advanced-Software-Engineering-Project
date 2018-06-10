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
 * Created by QUANPN on 5/23/2018.
 */

public class CustomDanhmucmuaApdater extends BaseAdapter {
    private List<Danhmucthumua> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomDanhmucmuaApdater(Context aContext,  List<Danhmucthumua> listData) {
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
        CustomDanhmucmuaApdater.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_view_danhmucthu, null);
            holder = new CustomDanhmucmuaApdater.ViewHolder();
            holder.flagView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.countryNameView = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.populationView = (TextView) convertView.findViewById(R.id.textView_population);
            holder.Cohieu = (TextView) convertView.findViewById(R.id.textViewCohieu);
            convertView.setTag(holder);
        } else {
            holder = (CustomDanhmucmuaApdater.ViewHolder) convertView.getTag();
        }

        Danhmucthumua country = this.listData.get(position);
        holder.countryNameView.setText("Hạng mục: "+country.getCountryName());
        holder.populationView.setText("Diễn giải: " + country.getPopulation());
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
