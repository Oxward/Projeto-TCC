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
    List<PontosDeParada> selectAllPdP();

    @Query("SELECT * FROM pontoDeParada")
    LiveData<List<PontosDeParada>> selectPdP();

    @Query("SELECT * FROM pontoDeParada WHERE turnoParada = :turno")
    List<PontosDeParada> selectPdPTurno(String turno);

    @Query("SELECT * FROM pontoDeParada WHERE turnoParada = :turno AND placaBusParada = :placa")
    List<PontosDeParada> selectPdPTurnoPlaca(String turno, String placa);

    @Query("DELETE FROM pontoDeParada")
    void deleteAllPdP();

}