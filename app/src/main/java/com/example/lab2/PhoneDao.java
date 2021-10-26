package com.example.lab2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.w3c.dom.Element;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(PhoneEntity element);

    @Query("DELETE FROM Phone")
    void deleteAll();

    @Delete
    void deleteSelected(PhoneEntity phone);

    @Query("DELETE from Phone WHERE id = :id")
    void deleteById(long id);

    @Query("SELECT * FROM Phone")
    LiveData<List<PhoneEntity>> getAlphabetizedElements();

    @Query("SELECT * FROM Phone WHERE id=:id")
    LiveData<PhoneEntity> getPhone(long id);

    @Update
    void update(PhoneEntity phone);
}
