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
    private int idPdP;

    @NonNull
    @ColumnInfo(name = "turnoParada", typeAffinity = ColumnInfo.INTEGER)
    private byte turnoPdP;

    @NonNull
    @ColumnInfo(name = "horaParada", typeAffinity = ColumnInfo.TEXT)
    private String horaPdP;

    @NonNull
    @ColumnInfo(name = "localParada", typeAffinity = ColumnInfo.TEXT)
    private String localPdP;

    @NonNull
    @ColumnInfo(name = "latParada", typeAffinity = ColumnInfo.REAL)
    private double latParada;

    @NonNull
    @ColumnInfo(name = "longPdP", typeAffinity = ColumnInfo.REAL)
    private double longPdP;

    public PontosDeParada() {
    }

    @Ignore
    public PontosDeParada(String horaPdP, String localPdP) {
        this.horaPdP = horaPdP;
        this.localPdP = localPdP;
    }

    @Ignore
    public PontosDeParada(byte turnoPdP, String localPdP, double latParada, double longPdP) {
        this.turnoPdP = turnoPdP;
        this.localPdP = localPdP;
        this.latParada = latParada;
        this.longPdP = longPdP;
    }

    @Ignore
    public PontosDeParada(byte turnoPdP, String horaPdP, String localPdP, double latParada, double longPdP) {
        this.turnoPdP = turnoPdP;
        this.horaPdP = horaPdP;
        this.localPdP = localPdP;
        this.latParada = latParada;
        this.longPdP = longPdP;
    }

    public static PontosDeParada[] populateDB() {
        return new PontosDeParada[]{
                new PontosDeParada((byte) 1, "CTF", "07:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Procuradoria Federal", "08:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Freitas", "09:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Educandário", "10:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Paraíba", "11:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Antiga Yamaha", "12:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Hotel Pousada", "13:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Rodoviária nova", "14:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Posto R. Sá", "15:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "FM", "16:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Agespisa", "17:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "Procuradoria Federal", "18:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "CTF", "19:30", -6.777723, -43.031713)
        };
        //Bus 2
    }

    public int getIdPdP() {
        return idPdP;
    }

    public void setIdPdP(int idPdP) {
        this.idPdP = idPdP;
    }

    public byte getTurnoPdP() {
        return turnoPdP;
    }

    public void setTurnoPdP(byte turnoPdP) {
        this.turnoPdP = turnoPdP;
    }

    public String getHoraPdP() {
        return horaPdP;
    }

    public void setHoraPdP(String horaPdP) {
        this.horaPdP = horaPdP;
    }

    public String getLocalPdP() {
        return localPdP;
    }

    public void setLocalPdP(String localPdP) {
        this.localPdP = localPdP;
    }

    public double getLatParada() {
        return latParada;
    }

    public void setLatParada(double latParada) {
        this.latParada = latParada;
    }

    public double getLongPdP() {
        return longPdP;
    }

    public void setLongPdP(double longPdP) {
        this.longPdP = longPdP;
    }
}