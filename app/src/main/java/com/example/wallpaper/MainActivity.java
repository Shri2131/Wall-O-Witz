package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ListView listView1;
    ListView listView2;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=findViewById(R.id.gridView);


        String[] names = new String[]{"Shrimohan","Soham","Aman","Sanskriti","Divyansh","Kshitij","Rambabu","Chinmay"};
        int[] imgLocations = new int[]{R.drawable.spiderman_3,R.drawable.romantic,R.drawable.spiderman_2,R.drawable.panda,R.drawable.spiderman_1,R.drawable.avengers,R.drawable.groot,R.drawable.candle};

        wallPaperAdapter wallPaperAdapterW = new wallPaperAdapter(this,names,imgLocations);
        gridView.setAdapter(wallPaperAdapterW);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, setWallPaper.class);
                intent.putExtra("Location",imgLocations[i]);
                startActivity(intent);
            }
        });
    }
}