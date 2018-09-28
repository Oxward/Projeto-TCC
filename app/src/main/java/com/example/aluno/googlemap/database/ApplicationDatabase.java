package com.example.aluno.googlemap.database;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.aluno.googlemap.classes.PontosDeParada;

import java.util.concurrent.Executors;

import static com.example.aluno.googlemap.database.ApplicationDatabase.DATABASE_VERSION;

@Database(entities = {PontosDeParada.class}, exportSchema = false, version = DATABASE_VERSION)
public abstract class ApplicationDatabase extends RoomDatabase {

    static final int DATABASE_VERSION = 1;
    private static final String TAG = "ApplicationDatabase";
    private static final String DATABASE_NAME = "passaAonde";
    private static volatile ApplicationDatabase INSTANCE;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

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
                        });
                    }
                }).addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor()
                                .execute(() -> {
                                    getDatabase(context, appExecutors).pdpDAO().insertPdP(PontosDeParada.populateDB());
                                    Log.d(TAG, "run: Populou BD");
                                });
                    }
                }).build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Log.d(TAG, "addDelay: Thread Error");
        }
    }

    public abstract Pontos_DAO pdpDAO();

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

}
