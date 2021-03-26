package com.kidsbrainstorm.takenotes;

import java.io.Serializable;

public class NotesClass implements Serializable {
    private String title;
    private String notes;

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NotesClass(String title, String notes) {
        this.title = title;
        this.notes=notes;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }
}
