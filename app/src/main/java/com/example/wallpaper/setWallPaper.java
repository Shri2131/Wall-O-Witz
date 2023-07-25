package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class setWallPaper extends AppCompatActivity {

    Button setHomeScreenWallpaperButton;
    Button setLockScreenWallpaperButton;
    ImageButton downloadWallPaperButton;
    ImageView wallPaperImage;
    String imgLink;
    StorageReference imgLinkRef;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    boolean downloadCheck =false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wall_paper);

        Intent intent = getIntent();
        imgLink = intent.getStringExtra("imgURL");
        imgLinkRef = storage.getReferenceFromUrl(imgLink);

        File file = null;
        try {
            file = File.createTempFile("img",".jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File finalFile = file;

        setHomeScreenWallpaperButton=findViewById(R.id.setHomeScreenWallPaperButton);
        setLockScreenWallpaperButton=findViewById(R.id.setLockScreenWallPaperButton);
        downloadWallPaperButton=findViewById(R.id.downloadButton);
        wallPaperImage=findViewById(R.id.wallPaperImageView);

        //Load Image
        Picasso.get().load(imgLink).into(wallPaperImage);

        downloadWallPaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgLinkRef.getFile(finalFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        downloadCheck=true;
                        Toast.makeText(setWallPaper.this, "Download Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(setWallPaper.this, "Failed to Download", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        setHomeScreenWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(downloadCheck){
                    changeWallPaper(1, finalFile);
                }else{
                    Toast.makeText(setWallPaper.this, "Please download the wallpaper first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setLockScreenWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(downloadCheck){
                    changeWallPaper(2, finalFile);
                }else{
                    Toast.makeText(setWallPaper.this, "Please download the wallpaper first!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("ResourceType")
    public void changeWallPaper(int i, File fileN){
        //Special Code
//        DisplayMetrics metrics = new DisplayMetrics();
//        Display display = getWindowManager().getDefaultDisplay();
//        display.getMetrics(metrics);
//        final int screenWidth  = metrics.widthPixels;
//        final int screenHeight = metrics.heightPixels;

        Bitmap bitmap = BitmapFactory.decodeFile(fileN.getAbsolutePath());
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        //Special Code 2
//        wallpaperManager.suggestDesiredDimensions(screenWidth, screenHeight);
//        final int width = wallpaperManager.getDesiredMinimumWidth();
//        final int height = wallpaperManager.getDesiredMinimumHeight();

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setBitmap(bitmap,null,true,i);
            }

            Toast.makeText(this, "Wallpaper changed Successfully", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "Error : "+ e, Toast.LENGTH_SHORT).show();
        }
    }
}