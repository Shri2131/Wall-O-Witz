package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class wallPaperAdapter extends BaseAdapter {
    private ArrayList<String> imgLocList;
    private String[] names;
    private Activity activity;

    public wallPaperAdapter(Activity activity, String[] names, ArrayList<String> imgLocList){
        super();
        this.names=names;
        this.activity=activity;
        this.imgLocList=imgLocList;
    }

    public String getNames(int position) {
        return names[position];
    }

    public String getLink(int position){return imgLocList.get(position);}

    @Override
    public int getCount() {
        return imgLocList.size();
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imageView1;
        TextView textView1;
    }

    public void updateList(ArrayList<String> newList){
        imgLocList.clear();
        this.imgLocList = newList;
        this.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater inflater = activity.getLayoutInflater();
        if(view==null){
            view=inflater.inflate(R.layout.wall_paper_adapter,null);
            holder=new ViewHolder();
            holder.imageView1 = view.findViewById(R.id.wallPaperImage1);
            holder.textView1 =view.findViewById(R.id.wallPaperText1);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }
        Picasso.get().load(getLink(i)).into(holder.imageView1);
        holder.textView1.setText("Uploaded by "+getItem(i));
        return view;
    }

}
