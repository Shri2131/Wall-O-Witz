package com.example.wallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> imgLinkList = new ArrayList<>();
    ArrayList<String> nameLinkList = new ArrayList<>();

    GridView gridView;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        mDatabase = FirebaseDatabase.getInstance().getReference();

//        String[] names = new String[]{"Shrimohan", "Soham", "Aman", "Sanskriti", "Divyansh", "Kshitij", "Rambabu", "Chinmay"};

//        nameLinkList.addAll(Arrays.asList(names));


        wallPaperAdapter wallPaperAdapter = new wallPaperAdapter(this, nameLinkList, imgLinkList);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, setWallPaper.class);
                intent.putExtra("imgURL",imgLinkList.get(i));
                startActivity(intent);
            }
        });


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    String link = (String) snap.getValue();
                    imgLinkList.add(String.valueOf(link));
                    nameLinkList.add("Shrimohan");
                }
                gridView.setAdapter(wallPaperAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error in Connectivity", Toast.LENGTH_SHORT).show();
            }
        });

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String link = (String) snapshot.getValue();
                imgLinkList.add(String.valueOf(link));
//                nameLinkList.add("Rajesh");
                wallPaperAdapter.updateList(imgLinkList);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                wallPaperAdapter.updateList(imgLinkList);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                imgLinkList.remove(snapshot.getValue(String.class));
                nameLinkList.remove(0);
                wallPaperAdapter.updateList(imgLinkList);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}