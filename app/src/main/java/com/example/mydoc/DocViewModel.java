package com.example.mydoc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DocViewModel extends AndroidViewModel {

    private DocRepository repo;
    private LiveData<List<Docs>> allData;

    public DocViewModel(@NonNull Application application) {

        super(application);

        DocRoomDatabase database = DocRoomDatabase.getINSTANCE(application);
        repo = new DocRepository(database);
        allData = repo.getData();

    }

    public LiveData<List<Docs>> getAllData(){
        return allData;
    }

    public void insert(Docs docs){
        repo.insert(docs);
    }

    public void delete(Docs docs){
        repo.delete(docs);
    }

    public void update(Docs docs){
        repo.update(docs);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

}
