package com.example.aluno.googlemap.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "pontoDeParada")
public class PontosDeParada
{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idPontoParada", typeAffinity = ColumnInfo.INTEGER)
    private int id;

    @NonNull
    @ColumnInfo(name = "turnoParada", typeAffinity = ColumnInfo.TEXT)
    private String turno;

    @NonNull
    @ColumnInfo(name = "horaParada", typeAffinity = ColumnInfo.TEXT)
    private String hora;

    @NonNull
    @ColumnInfo(name = "localParada", typeAffinity = ColumnInfo.TEXT)
    private String parada;

    public PontosDeParada() {
    }

    @Ignore
    public PontosDeParada(String turno, String hora, String parada) {
        this.turno = turno;
        this.hora = hora;
        this.parada = parada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getParada() {
        return parada;
    }

    public void setParada(String parada) {
        this.parada = parada;
    }
}