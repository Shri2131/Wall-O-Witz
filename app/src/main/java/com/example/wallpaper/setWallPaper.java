package com.example.wallpaper;

import static android.provider.MediaStore.Images.Media.getBitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class setWallPaper extends AppCompatActivity {

    Button setHomeScreenWallpaperButton;
    Button setLockScreenWallpaperButton;
    ImageView wallPaperImage;
    int imgLocation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wall_paper);

        Intent intent = getIntent();
        imgLocation = intent.getIntExtra("Location",0);

        setHomeScreenWallpaperButton=findViewById(R.id.setHomeScreenWallPaperButton);
        setLockScreenWallpaperButton=findViewById(R.id.setLockScreenWallPaperButton);

        wallPaperImage=findViewById(R.id.wallPaperImageView);

        wallPaperImage.setImageResource(imgLocation);

        setHomeScreenWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeWallPaper(1);
            }
        });

        setLockScreenWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeWallPaper(2);
            }
        });
    }

    @SuppressLint("ResourceType")
    public void changeWallPaper(int i){
        //Special Code
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        final int screenWidth  = metrics.widthPixels;
        final int screenHeight = metrics.heightPixels;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imgLocation);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        //Special Code 2
        wallpaperManager.suggestDesiredDimensions(screenWidth, screenHeight);
        final int width = wallpaperManager.getDesiredMinimumWidth();
        final int height = wallpaperManager.getDesiredMinimumHeight();
        Bitmap wallpaper = Bitmap.createScaledBitmap(bitmap, width, height, true);
//        Bitmap wallpaper = Bitmap.createBitmap(bitmap,((height/2)-(width/2)),((height/2)-(width/2)),width,height);

        try {
            //Set WallPaper
//            wallpaperManager.setResource(imgLocation);
//
//            //Set LockScreen Wallpaper
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                wallpaperManager.setResource(imgLocation,WallpaperManager.FLAG_LOCK);
//            }

            //Special Code
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setBitmap(bitmap,null,true,i);
            }
//            findViewById(R.id.progressBar).setVisibility(View.GONE);


//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                wallpaperManager.setBitmap(bitmap,null,true,WallpaperManager.FLAG_LOCK);
//            }
//            wallpaperManager.setBitmap(bitmap);
            Toast.makeText(this, "Wallpaper changed Successfully", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "Error : "+String.valueOf(e), Toast.LENGTH_SHORT).show();
        }
    }
}