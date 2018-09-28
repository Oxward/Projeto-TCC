package com.example.aluno.googlemap.database;

import android.app.Application;
import android.os.AsyncTask;

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

    public List<PontosDeParada> getPdPByTurn(String turn) {
        return pontos_dao.selectPdPTurno(turn);
    }

    public void insertPonto(PontosDeParada... pontosDeParada) {
        new insertAsyncTask(pontos_dao).execute(pontosDeParada);
    }

    private static class insertAsyncTask extends AsyncTask<PontosDeParada, Void, Void> {

        private Pontos_DAO mAsyncDAO;

        insertAsyncTask(Pontos_DAO pontos_dao) {
            this.mAsyncDAO = pontos_dao;
        }

        @Override
        protected Void doInBackground(PontosDeParada... pontosDeParadas) {

            for (PontosDeParada p : pontosDeParadas) {
                mAsyncDAO.insertPdP(p);
            }
            return null;
        }
    }

}