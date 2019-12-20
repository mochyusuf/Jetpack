package mocha.yusuf.jetpack3.Data.Source.Local.Room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;

@androidx.room.Database(entities = {MovieEntity.class, TVEntity.class},
        version = 1,
        exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database INSTANCE;
    public abstract DatabaseDao databaseDao();
    private static final Object lock = new Object();
    public static Database getINSTANCE(Context context) {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, "Database.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}