package com.example.lab2;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ElementViewModel extends AndroidViewModel {
    private final ElementRepository mRepository;
    private final LiveData<List<PhoneEntity>> mAllElements;
    private PhoneDao phoneDao;
    private PhoneRoomDatabase db;
    public ElementViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ElementRepository(application);
        mAllElements = mRepository.getAllElements();
        db = PhoneRoomDatabase.getDatabase(application);
        phoneDao = db.phoneDao();

        }

    LiveData<List<PhoneEntity>> getAllPhones(){
        return mAllElements;
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }
    public void deleteSelected(PhoneEntity phone) { mRepository.deleteSelected(phone);}
    public void deleteById(long id) { mRepository.deleteById(id);}
    public void insert(PhoneEntity phone){
        mRepository.insert(phone);
    }
    public void update(PhoneEntity phone){
        mRepository.update(phone);
    }
    public LiveData<PhoneEntity> getPhone(long id){
        return phoneDao.getPhone(id);
    }
    }


