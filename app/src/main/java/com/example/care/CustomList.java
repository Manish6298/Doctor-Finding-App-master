package com.example.care;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {
    private Activity context;
    private String[] name;
    private String[] desc;
    private String[] rat;
    private String[] exp;

    public CustomList(Activity context,String[] name,String[] desc,String []rat,String exp[])
    {
        super(context,R.layout.care_c,name);
        this.name=name;
        this.context=context;
        this.desc=desc;
        this.rat=rat;
        this.exp=exp;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflate=context.getLayoutInflater();
        View listViewItem=inflate.inflate(R.layout.care_c,null,true);
        TextView t1=  (TextView) listViewItem.findViewById(R.id.textView1);
        TextView t2=(TextView) listViewItem.findViewById(R.id.textView2);
        TextView t3=(TextView) listViewItem.findViewById(R.id.textView3);
        RatingBar r1=(RatingBar) listViewItem.findViewById(R.id.ratingBar1);
        t1.setText(name[position]);
        t2.setText(desc[position]);
        r1.setRating(Float.parseFloat(rat[position]));
        t3.setText(exp[position]);
        return listViewItem;
    }
}
