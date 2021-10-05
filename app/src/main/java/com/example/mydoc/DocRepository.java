package com.example.mydoc;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DocRepository {

    private IDocDAO dao;
    private LiveData<List<Docs>> data;

    public DocRepository(DocRoomDatabase database){

        dao = database.iDocDAO();
        data = dao.getAllDocs();

    }

    LiveData<List<Docs>> getData(){
        return data;
    }

    void insert(Docs docs){
        DocRoomDatabase.databaseWriteExecutor.execute(()-> {
            dao.insert(docs);
        });
    }

    void delete(Docs docs){
        DocRoomDatabase.databaseWriteExecutor.execute(()-> {
            dao.delete(docs);
        });
    }

    void update(Docs docs){
        DocRoomDatabase.databaseWriteExecutor.execute(()-> {
            dao.update(docs);
        });
    }

    void deleteAll(){
        DocRoomDatabase.databaseWriteExecutor.execute(()-> {
            dao.deleteAll();
        });
    }

}
