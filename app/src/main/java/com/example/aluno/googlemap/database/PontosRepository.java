package com.example.aluno.googlemap.database;

import android.app.Application;

import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.List;

public class PontosRepository {

    private Pontos_DAO pontos_dao;

    PontosRepository(Application application) {
        ApplicationDatabase database = ApplicationDatabase.getDatabase(application, new AppExecutors());
        pontos_dao = database.pdpDAO();
    }

    public List<PontosDeParada> getAllPdP() {
        return pontos_dao.selectAllPdP();
    }

    public List<PontosDeParada> getPdPByTurn(String turno) {
        return pontos_dao.selectPdPTurno(turno);
    }

    public void insertPonto(PontosDeParada pontosDeParada) {

    }


}