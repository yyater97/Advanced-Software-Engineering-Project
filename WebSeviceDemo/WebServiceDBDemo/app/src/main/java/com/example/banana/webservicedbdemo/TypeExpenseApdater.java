package com.example.banana.webservicedbdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TypeExpenseApdater extends BaseAdapter{

    private Context context;
    private int layout;
    private List<TypeExpense> typeExpenseList;

    public TypeExpenseApdater(Context context, int layout, List<TypeExpense> typeExpenseList) {
        this.context = context;
        this.layout = layout;
        this.typeExpenseList = typeExpenseList;
    }

    @Override
    public int getCount() {
        return typeExpenseList.size();
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
        TextView txtvName, txtvDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtvName = (TextView) convertView.findViewById(R.id.txtvName);
            holder.txtvDescription = (TextView) convertView.findViewById(R.id.txtvDescription);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        TypeExpense typeExpense = typeExpenseList.get(position);
        holder.txtvName.setText(typeExpense.getName());
        holder.txtvDescription.setText(typeExpense.getDescription());
        return convertView;
    }
}
