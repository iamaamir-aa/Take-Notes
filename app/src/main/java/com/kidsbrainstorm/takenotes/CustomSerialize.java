package com.kidsbrainstorm.takenotes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomSerialize  {


    public void Write(ArrayList<?> list){
        try {
            FileOutputStream fileOutputStream= new FileOutputStream("notes");

            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(list);

            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public NotesClass Load(){
        try {
            FileInputStream fileInputStream=new FileInputStream("notes");

            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);


            NotesClass notesClass=(NotesClass)objectInputStream.readObject();

            objectInputStream.close();
            return notesClass;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        }
    }


}
