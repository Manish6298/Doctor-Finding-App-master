package com.example.care;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ListSec extends ArrayAdapter<String> {
    private Activity context;
    private String name[];
    private String info[];

    public ListSec(Activity context, String name[],String info[]) {
        super(context,R.layout.drlist,name);
        this.context = context;
        this.name=name;
        this.info=info;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate=context.getLayoutInflater();
        View listViewItem=inflate.inflate(R.layout.drlist,null,true);
        TextView t1=  (TextView) listViewItem.findViewById(R.id.textView1);
        TextView t2=(TextView) listViewItem.findViewById(R.id.textView2);
        t1.setText(name[position]);
        t2.setText(info[position]);
        return listViewItem;
    }
}
