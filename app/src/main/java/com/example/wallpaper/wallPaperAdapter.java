package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.net.URI;
import java.util.ArrayList;

public class wallPaperAdapter extends BaseAdapter {
    private URI[] imageUri;
    private int[] imageLoc;
    private String[] names;
//    private final Context context;
    private Activity activity;
    LinearLayout list;

//    public wallPaperAdapter(@NonNull Context context, int resource, @NonNull URI[] imageUri, @NonNull String[] names){
//        super();
//        this.context=context;
//        this.imageUri=imageUri;
//        this.names=names;
//    }

    public wallPaperAdapter(Activity activity, String[] names, int[] imgLoc){
            super();
            this.names=names;
            this.activity=activity;
            this.imageLoc=imgLoc;
    }

//    public wallPaperAdapter(@NonNull Context context, int resource, @NonNull String[] names){
//        super();
//        this.context=context;
////        this.imageUri=imageUri;
//        this.names=names;
//    }

    public URI getImageUri(int position) {
        return imageUri[position];
    }

    public int getImageLoc(int position) {
        return imageLoc[position];
    }

    public String getNames(int position) {
        return names[position];
    }

    @Override
    public int getCount() {
        return names.length;
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater inflater = activity.getLayoutInflater();
        if(view==null){
            view=inflater.inflate(R.layout.wall_paper_adapter,null);
            holder=new ViewHolder();
            holder.imageView1 = (ImageView) view.findViewById(R.id.wallPaperImage1);
            holder.textView1 =(TextView)view.findViewById(R.id.wallPaperText1);
//            ImageView wallPaperImage = (ImageView) view.findViewById(R.id.wallPaperImage);
//            TextView wallPaperText =(TextView)view.findViewById(R.id.wallPaperText);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }
        holder.imageView1.setImageResource(getImageLoc(i));
        holder.textView1.setText("Uploaded by "+getItem(i));
        return view;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
////        convertView = LayoutInflater.from(getContext()).inflate(R.layout.wall_paper_adapter,parent,false);
//        ViewHolder holder
//
////        ImageView wallPaperImage = convertView.findViewById(R.id.wallPaperImage);
//        TextView wallPaperText = convertView.findViewById(R.id.wallPaperText);
//
////        wallPaperImage.setImageURI(imageUri[position]);
//        wallPaperText.setText(names[position]);
//
//        return convertView;
//    }

}
