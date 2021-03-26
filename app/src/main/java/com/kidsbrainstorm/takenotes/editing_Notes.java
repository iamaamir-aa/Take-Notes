package com.kidsbrainstorm.takenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class editing_Notes extends AppCompatActivity {
    EditText editText,titleEditText;
    Intent intent;
    int position;
    NotesClass notesClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing__notes);
        editText=(EditText) findViewById(R.id.editText);
        titleEditText=(EditText) findViewById(R.id.titleEditText);
        intent=new Intent(getApplicationContext(),MainActivity.class);
        notesClass=new NotesClass("","");

        position=getIntent().getIntExtra("position",-1);

        if(position!=-1){

            editText.setText(MainActivity.arrayList.get(position).getNotes());
            titleEditText.setText(MainActivity.arrayList.get(position).getTitle());

        }else{
            editText.setText("");
            titleEditText.setText("Title");
            MainActivity.arrayList.add(new NotesClass("",""));
            position=MainActivity.arrayList.size()-1;
        }

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.arrayList.get(position).setTitle(String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivity.arrayList.get(position).setNotes(String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}