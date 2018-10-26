package com.example.aluno.googlemap.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "bus")
public class Bus {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idBus", typeAffinity = ColumnInfo.INTEGER)
    private byte idBus;

    @NonNull
    @ColumnInfo(name = "placaBus", typeAffinity = ColumnInfo.TEXT)
    private String placaBus;

    public Bus() {
    }

    @Ignore
    public Bus(String placaBus) {
        this.placaBus = placaBus;
    }

    public byte getIdBus() {
        return idBus;
    }

    public void setIdBus(byte idBus) {
        this.idBus = idBus;
    }

    public String getPlacaBus() {
        return placaBus;
    }

    public void setPlacaBus(String placaBus) {
        this.placaBus = placaBus;
    }
}