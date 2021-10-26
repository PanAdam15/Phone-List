package com.example.lab2;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ElementRepository {
    private final PhoneDao mElementDao;
    private final LiveData<List<PhoneEntity>> mAllElements;

    ElementRepository(Application application) {
        PhoneRoomDatabase phoneRoomDatabase =
                PhoneRoomDatabase.getDatabase(application);
        mElementDao = phoneRoomDatabase.phoneDao();
        mAllElements = mElementDao.getAlphabetizedElements();
    }
    LiveData<List<PhoneEntity>> getAllElements(){
        return mAllElements;
    }

    void deleteAll(){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
                mElementDao.deleteAll();
        });
    }
    void deleteSelected(PhoneEntity phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.deleteSelected(phone);
        });
    }
    void deleteById(long id){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.deleteById(id);
        });
    }

    public void insert(PhoneEntity phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.insert(phone);
        });
    }
    public void update(PhoneEntity phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mElementDao.update(phone);
        });
    }
    LiveData<PhoneEntity> getPhone(long id){
        return mElementDao.getPhone(id);
    }

}
