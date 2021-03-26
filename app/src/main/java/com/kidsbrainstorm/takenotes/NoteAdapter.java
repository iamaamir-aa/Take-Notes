package com.kidsbrainstorm.takenotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<NotesClass> {


    public NoteAdapter(@NonNull Context context, ArrayList<NotesClass> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        NotesClass notesClass=getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemview, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView notes = (TextView) convertView.findViewById(R.id.notes);
        // Populate the data into the template view using the data object
        title.setText(notesClass.getTitle());
        notes.setText(notesClass.getNotes());
        // Return the completed view to render on screen
        return convertView;
    }

}
