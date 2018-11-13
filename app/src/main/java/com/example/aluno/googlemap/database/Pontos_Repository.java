package com.example.aluno.googlemap.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.ArrayList;
import java.util.List;

public class Pontos_Repository {

    private Pontos_DAO pontos_dao;
    private static List<PontosDeParada> pontosDeParadaList;

    Pontos_Repository(Application application) {
        ApplicationDatabase database = ApplicationDatabase.getDatabase(application, new AppExecutors());
        pontos_dao = database.pdpDAO();
        pontosDeParadaList = new ArrayList<>();
    }

    public List<PontosDeParada> getAllPdP() {
        //new selectAsyncTask(pontos_dao).execute();
        //Log.d("getAllPdP", "getAllPdP: pDPlist size: " + pontosDeParadaList.size());
        return pontos_dao.selectAllPdP();
    }

    public List<PontosDeParada> getPdPByTurn(byte turno) {
        return pontos_dao.selectPdPTurno(turno);
    }

    public List<PontosDeParada> getPdPByTurnPlaca(byte turno, String placa) {
        return pontos_dao.selectPdPTurnoPlaca(turno, placa);
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

    private static class selectAsyncTask extends AsyncTask<Void, Void, List<PontosDeParada>> {

        private Pontos_DAO pontosDao;
        private List<PontosDeParada> mAsyncList;

        selectAsyncTask(Pontos_DAO pontos_dao) {
            this.pontosDao = pontos_dao;
            mAsyncList = new ArrayList<>();
        }

        @Override
        protected List<PontosDeParada> doInBackground(Void... voids) {
            Log.d("selectAsyncTask", "doInBackground: Entrou na Thread... size: " + mAsyncList.size());
            mAsyncList = pontosDao.selectAllPdP();
            Log.d("selectAsyncTask", "doInBackground: mAsyncList size: " + mAsyncList.size());
            return mAsyncList;
        }

        @Override
        protected void onPostExecute(List<PontosDeParada> list) {
            Log.d("selectAsyncTask", "onPostExecute: list size:" + list.size());
            pontosDeParadaList = list;
        }
    }

}