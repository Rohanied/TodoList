package com.example.thecontactapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Todo.class}, exportSchema = false, version = 3)
public abstract class TodoRoomDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();

    private static TodoRoomDatabase INSTANCE;

    public static TodoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ((TodoRoomDatabase.class)) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodoRoomDatabase.class, "todo_list")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

//    public static RoomDatabase.Callback sRoomDatabase = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            new PopulateDb(INSTANCE).execute();
//        }
//    };
//
//    public static class PopulateDb extends AsyncTask<Void, Void, Void> {
//
//        private final TodoDao mDao;
//        Todo todo = new Todo(0L,"Jeff", "9045358945","jeffwinger@gmail.com","32","Male","California","Greendale");
//        Todo con = new Todo(0L,"Annie", "849298483","anniedison@ad.con","21","Female","California","Greendale");
//
//        PopulateDb(TodoRoomDatabase db) {
//            mDao = db.todoDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            if (mDao.getAnyTodo().length < 1) {
//                mDao.insert(todo);
//                mDao.insert(con);
//
//            }
//            return null;
//        }
//    }
}


