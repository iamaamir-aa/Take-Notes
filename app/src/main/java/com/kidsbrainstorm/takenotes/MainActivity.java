package com.kidsbrainstorm.takenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MainActivity extends AppCompatActivity {


    static ArrayList<NotesClass> arrayList;
    static NoteAdapter arrayAdapter;

    Gson gson;
    String json;


    public void saveData() {
        try {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = this.getSharedPreferences("com.kidsbrainstorm.takenotes", MODE_PRIVATE);
            json = gson.toJson(arrayList);
            sharedPreferences.edit().putString("data", json).apply();
        } catch (NullPointerException e) {

        }
    }


    public void loadData() {
        try {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = this.getSharedPreferences("com.kidsbrainstorm.takenotes", MODE_PRIVATE);
            Type type = new TypeToken<ArrayList<NotesClass>>() {
            }.getType();
            arrayList = gson.fromJson(sharedPreferences.getString("data", null), type);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                arrayList.add(new NotesClass("Title", "Example"));
            }
        } catch (NullPointerException nullPointerException) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_new_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onDestroy() {
        saveData();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addNew) {
            saveData();
            Intent intent = new Intent(getApplicationContext(), editing_Notes.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), editing_Notes.class);

        loadData();
        if (arrayList.isEmpty()) {
            arrayList.add(new NotesClass("Title", "Example Note"));
        }

        ListView listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new NoteAdapter(getApplicationContext(), arrayList);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveData();
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete it?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();


                return true;
            }
        });


    }
}