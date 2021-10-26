package com.example.lab2;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PhoneEntity.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    public abstract PhoneDao phoneDao();

    private static volatile PhoneRoomDatabase INSTANCE;

    static PhoneRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (PhoneRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneRoomDatabase.class, "PhoneDatabase2")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static  RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() ->{
                PhoneDao dao = INSTANCE.phoneDao();

                dao.insert(new PhoneEntity("Google","Pixel",12,"store.google.com"));
                dao.insert(new PhoneEntity("Xiaomi","miA2",11,"www.fdsfds.com"));
                dao.insert(new PhoneEntity("Apple","IphoneX",10,"www.dsytra.com"));
                dao.insert(new PhoneEntity("Samsung","Galaxy",10,"www.dxsnx.com"));
                dao.insert(new PhoneEntity("Asus","aniewiem",11,"www.rrdfdfdsa.com"));
                dao.insert(new PhoneEntity("Hauwei","cingyang",12,"www.dsa.com"));
                dao.insert(new PhoneEntity("TEST","TEST",9,"jakies"));
            });
        }

        //    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
//
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db){
//            super.onCreate(db);
//
//            databaseWriteExecutor.execute(() ->{
//                ElementDao dao = INSTANCE.elementDao();
//
//                dao.insert(PhoneEntity("Gugl"));
//                dao.insert(new PhoneEntity("SIAJOMI","miA2"));
//                dao.insert(new PhoneEntity("apl","Iphon0"));
//                dao.insert(new PhoneEntity("Szajsung","GalaxyBoomMax"));
//                dao.insert(new PhoneEntity("Azus","aniewiem"));
//                dao.insert(new PhoneEntity("huiawej","cingyang"));
//            });
//        }
   };
}
