package com.example.aluno.googlemap.database;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.ArrayList;
import java.util.List;

import static com.example.aluno.googlemap.database.ApplicationDatabase.DATABASE_VERSION;

@Database(entities = {PontosDeParada.class}, exportSchema = false, version = DATABASE_VERSION)
public abstract class ApplicationDatabase extends RoomDatabase {

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    static final int DATABASE_VERSION = 1;
    private static final String TAG = "ApplicationDatabase";
    private static final String DATABASE_NAME = "passaAonde";

    private static ApplicationDatabase buildDatabase(final Context context, final AppExecutors appExecutors) {
        return Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        appExecutors.diskIO().execute(() -> {
                            addDelay();
                            ApplicationDatabase database = ApplicationDatabase.getDatabase(context, appExecutors);
                            database.setDatabaseCreated();
                            Log.d(TAG, "onCreate: Está aqui");
                        });
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        appExecutors.diskIO().execute(() -> {
                            //getDatabase(context, appExecutors).pdpDAO().insertPdP(PontosDeParada.populateDB());
                            new PopulateDbAsync(getDatabase(context, appExecutors)).execute();
                            Log.d(TAG, "onOpen: Populou o caralho");
                        });
                    }
                }).fallbackToDestructiveMigration().build();
    }

    private static volatile ApplicationDatabase INSTANCE;

    public static ApplicationDatabase getDatabase(final Context context, final AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (ApplicationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context.getApplicationContext(), appExecutors);
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    public abstract Pontos_DAO pdpDAO();

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Log.d(TAG, "addDelay: Thread Error");
        }
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final Pontos_DAO pontosDao;

        PopulateDbAsync(ApplicationDatabase database) {
            pontosDao = database.pdpDAO();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pontosDao.deleteAllPdP();
            pontosDao.insertPdP(PontosDeParada.populateDB());
            Log.d(TAG, "doInBackground: Populou DB");
            List<PontosDeParada> l = new ArrayList<>(pontosDao.selectAllPdP());
            Log.d(TAG, "onPostExecute: list size: " + l.size());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
