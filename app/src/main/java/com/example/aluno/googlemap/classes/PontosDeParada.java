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
    @ColumnInfo(name = "placaBusParada", typeAffinity = ColumnInfo.TEXT)
    private String placaOnibus;

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
    public PontosDeParada(byte turnoPdP, String placaOnibus, String localPdP, String horaPdP, double latParada, double longPdP) {
        this.turnoPdP = turnoPdP;
        this.placaOnibus = placaOnibus;
        this.horaPdP = horaPdP;
        this.localPdP = localPdP;
        this.latParada = latParada;
        this.longPdP = longPdP;
    }

    public int getIdPdP() {
        return idPdP;
    }

    public void setIdPdP(int idPdP) {
        this.idPdP = idPdP;
    }

    public String getPlacaOnibus() {
        return placaOnibus;
    }

    public void setPlacaOnibus(String placaOnibus) {
        this.placaOnibus = placaOnibus;
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

    public static PontosDeParada[] populateDB() {
        return new PontosDeParada[]{
                //Manhã - OEE-7903
                new PontosDeParada((byte) 1, "OEE-7906", "CTF", "06:50", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Procuradoria Federal", "06:51", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Freitas", "06:53", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Miguel Das Pias", "06:54", 0.0, 0.0),
                new PontosDeParada((byte) 1, "OEE-7906", "Educandário", "06:55", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Antiga Yamaha", "07:01", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Rodoviária Nova", "07:06", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Posto R. Sá", "07:11", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "FM", "07:13", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Agespisa", "07:14", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Freitas", "07:15", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "Procuradoria Federal", "07:17", -6.777723, -43.031713),
                new PontosDeParada((byte) 1, "OEE-7906", "CTF", "07:22", -6.777723, -43.031713),
                //Tarde - OEE-7903
                new PontosDeParada((byte) 2, "OEE-7906", "CTF", "13:20", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Freitas", "13:23", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Miguel Das Pias", "13:24", 0.0, 0.0),
                new PontosDeParada((byte) 2, "OEE-7906", "Educandário", "13:25", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Antiga Yamaha", "13:27", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Rodoviária Nova", "13:36", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Posto R. Sá", "13:41", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "FM", "13:43", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Agespisa", "13:44", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Freitas", "13:45", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "Procuradoria Federal", "13:47", -6.777723, -43.031713),
                new PontosDeParada((byte) 2, "OEE-7906", "CTF", "13:51", -6.777723, -43.031713),
                //Crepúsculo - OEE-2466
                new PontosDeParada((byte) 3, "OEE-2466", "CTF", "17:00", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Terreno Baldio", "17:05", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Garagem da Prefeitura", "17:07", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Posto 5", "17:10", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Diocese", "17:12", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Paraíba", "17:15", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Antiga Yamaha", "17:17", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Hotel Pousada", "17:20", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Rodoviária Nova", "17:23", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Posto R. Sá", "17:28", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Rádio FM", "17:29", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Agespisa", "17:30", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Freitas", "17:31", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "Procuradoria Federal", "17:33", -6.777723, -43.031713),
                new PontosDeParada((byte) 3, "OEE-2466", "CTF", "17:38", -6.777723, -43.031713),
                //Crepúsculo - ????
                new PontosDeParada((byte) 3, "NHU-1403", "CTF", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Procuradoria Federal", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Freitas", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Agespisa", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Rádio FM", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Posto R. Sá", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Posto Fiscal de Pontões", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Posto Fiscal do Barão", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Funerária", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Posto Fiscal de Pontões", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Posto R. Sá", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Quick Sorvetes", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Freitas", "15:00", 0.0, 0.0),
                new PontosDeParada((byte) 3, "NHU-1403", "Procuradoria Federal", "15:00", 0.0, 0.0),

        };
        //Bus 2
    }
}