package com.example.mydoc;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "All_Docs")
public class Docs {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "Doc")
    private String doc;

    public Docs(@NonNull String doc){
        this.doc = doc;
    }

    public String getDoc() {
        return doc;
    }

    public int getId(){
        return id;
    }

    public void setId(int i){
        id = i;
    }
    
}
