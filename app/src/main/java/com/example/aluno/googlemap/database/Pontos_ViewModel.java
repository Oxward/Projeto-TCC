package com.example.aluno.googlemap.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.List;

public class Pontos_ViewModel extends AndroidViewModel {

    private PontosRepository pontosRepository;
    private List<PontosDeParada> pontosDeParadaList;

    public Pontos_ViewModel(@NonNull Application application) {
        super(application);
        pontosRepository = new PontosRepository(application);
        pontosDeParadaList = pontosRepository.getAllPdP();
    }

    public Pontos_ViewModel(@NonNull Application application, String turn) {
        super(application);
        pontosRepository = new PontosRepository(application);
        pontosDeParadaList = pontosRepository.getPdPByTurn(turn);
    }

    public List<PontosDeParada> getAllPoints() {
        return pontosDeParadaList;
    }

    public List<PontosDeParada> getPdPByTurn(String turn) {
        return pontosRepository.getPdPByTurn(turn);
    }

    public void insertPdP(PontosDeParada... pontosDeParada) {
        for (PontosDeParada p : pontosDeParada) {
            pontosRepository.insertPonto(pontosDeParada);
        }
    }

}