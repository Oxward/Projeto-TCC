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

    public Pontos_ViewModel(@NonNull Application application, String turno) {
        super(application);
        pontosRepository = new Pontos_Repository(application);
        pontosDeParadaList = getPdPByTurn(turno);
    }

    public Pontos_ViewModel(Application application, String turno, String placa) {
        super(application);
        pontosRepository = new Pontos_Repository(application);
        pontosDeParadaList = getPdPByTurnPlaca(turno, placa);
    }

    public List<PontosDeParada> getAllPoints() {
        pontosDeParadaList = pontosRepository.getAllPdP();
        return pontosDeParadaList;
    }

    public List<PontosDeParada> getPdPByTurn(String turn) {
        return pontosRepository.getPdPByTurn(turn);
    }

    public List<PontosDeParada> getPdPByTurnPlaca(String turn, String placa) {
        return pontosRepository.getPdPByTurnPlaca(turn, placa);
    }

    public void insertPdP(PontosDeParada... pontosDeParada) {
        for (PontosDeParada p : pontosDeParada) {
            pontosRepository.insertPonto(pontosDeParada);
        }
    }

}