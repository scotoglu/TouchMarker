package com.example.touchmarker.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.touchmarker.Dao.BusStopsTimeDao;
import com.example.touchmarker.Dao.BusesDao;
import com.example.touchmarker.Dao.FavBusDao;
import com.example.touchmarker.Dao.StopsDao;
import com.example.touchmarker.Model.Bus;
import com.example.touchmarker.Model.BusStopTime;
import com.example.touchmarker.Model.FavoriteBus;
import com.example.touchmarker.Model.Stop;


@Database(entities = {Bus.class, Stop.class, BusStopTime.class, FavoriteBus.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BusesDao busesDao();
    public abstract StopsDao stopsDao();
    public abstract BusStopsTimeDao busStopsTimeDao();
    public abstract FavBusDao favBusDao();

    private static  AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Sefa.db")
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_LATEST)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    private static final Migration MIGRATION_LATEST  = new Migration(8,9) {
        @Override
        public void migrate(SupportSQLiteDatabase db) {
            //Log.i("Tag" , "Migration Started");
            //Migration logic
            //Log.i("Tag" , "Migration Ended");
        }
    };
    public static void destroyInstance(){
        INSTANCE = null;
    }

}
