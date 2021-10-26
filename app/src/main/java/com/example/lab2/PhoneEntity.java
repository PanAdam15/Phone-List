package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Phone")
public class PhoneEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    public long id;

    @NonNull
    @ColumnInfo(name = "Producent")
    public String producent;

    @NonNull
    @ColumnInfo(name = "Model")
    public String model;


    @NonNull
    @ColumnInfo(name = "Wersja")
    public int version;

    @NonNull
    @ColumnInfo(name = "Strona_www")
    public String site;
@Ignore
    public PhoneEntity(long id, @NonNull String producent, @NonNull String model, int version, @NonNull String site) {
        this.id = id;
        this.producent = producent;
        this.model = model;
        this.version = version;
        this.site = site;
    }

    public PhoneEntity(@NonNull String producent, @NonNull String model, int version, @NonNull String site) {
        this.producent = producent;
        this.model = model;
        this.version = version;
        this.site = site;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getProducent() {
        return producent;
    }

    public void setProducent(@NonNull String producent) {
        this.producent = producent;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @NonNull
    public String getSite() {
        return site;
    }

    public void setSite(@NonNull String site) {
        this.site = site;
    }
}
