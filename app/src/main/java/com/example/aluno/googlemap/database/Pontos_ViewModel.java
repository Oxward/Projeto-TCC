package com.example.aluno.googlemap.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.ArrayList;
import java.util.List;

public class Pontos_ViewModel extends AndroidViewModel {

    private Pontos_Repository pontosRepository;
    private List<PontosDeParada> pontosDeParadaList;

    public Pontos_ViewModel(@NonNull Application application) {
        super(application);
        pontosRepository = new Pontos_Repository(application);
        pontosDeParadaList = new ArrayList<>();
    }

    public Pontos_ViewModel(@NonNull Application application, String turn) {
        super(application);
        pontosRepository = new Pontos_Repository(application);
        pontosDeParadaList = pontosRepository.getPdPByTurn(turn);
    }

    public List<PontosDeParada> getAllPoints() {
        pontosDeParadaList = pontosRepository.getAllPdP();
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