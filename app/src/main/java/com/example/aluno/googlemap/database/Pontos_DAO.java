package com.example.aluno.googlemap.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.List;

@Dao
public interface Pontos_DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPdP(PontosDeParada... pontosDeParada);

    @Query("SELECT * FROM pontoDeParada")
    LiveData<List<PontosDeParada>> selectAllPdPLD();

    @Query("SELECT * FROM pontoDeParada")
    List<PontosDeParada> selectAllPdP();

    @Query("SELECT * FROM pontoDeParada WHERE turnoParada = :turno")
    List<PontosDeParada> selectPdPTurno(byte turno);

    @Query("SELECT * FROM pontoDeParada WHERE placaBusParada = :placa")
    List<PontosDeParada> selectPdPPlaca(String placa);

    @Query("SELECT * FROM pontoDeParada WHERE turnoParada = :turno AND placaBusParada = :placa")
    List<PontosDeParada> selectPdPTurnoPlaca(byte turno, String placa);

    @Query("DELETE FROM pontoDeParada")
    void deleteAllPdP();

}