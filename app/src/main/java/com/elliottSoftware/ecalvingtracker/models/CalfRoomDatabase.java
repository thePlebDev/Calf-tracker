package com.elliottSoftware.ecalvingtracker.models;

import android.content.Context;

import com.elliottSoftware.ecalvingtracker.daos.CalfDao;
import com.elliottSoftware.ecalvingtracker.util.typeConverters.DateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
/**
 * TODO: set exportSchema to true when doing a database migration
 * **/
@Database(entities = {Calf.class},version = 1,exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class CalfRoomDatabase  extends RoomDatabase {

    public abstract CalfDao getCalfDao();

    private static volatile CalfRoomDatabase INSTANCE; //VOLATILE PROTECTS AGAINST MEMORY INCONSISTENCIES
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS); //HANDLES WORKER THREADS

    //IMPLEMENT THE SINGLETON PATTERN
    public static CalfRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CalfRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CalfRoomDatabase.class,"calf_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * called when the database is created for the first time
     * we are deleting everything???
     * **/
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{
                CalfDao dao = INSTANCE.getCalfDao();
                try {
                    dao.deleteAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    };
}
