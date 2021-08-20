package com.example.thecontactapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {
    private TodoDao mTodoDao;
    private LiveData<List<Todo>> mAllTodos;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final String NAME = "name";

    TodoRepository(Application application) {
        TodoRoomDatabase db = TodoRoomDatabase.getDatabase(application);
        mTodoDao = db.todoDao();
        mAllTodos = mTodoDao.getAllTodos();
    }

    LiveData<List<Todo>> getList() {
        return mAllTodos;
    }

    public void insert(final Todo todo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.insert(todo);
            }
        });
    }

    public void delete(final Todo todo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.delete(todo);
            }
        });
    }

    public void deleteByName(final String name) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.deleteTodo(name);
            }
        });
    }

    public void update(final Todo todo){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.update(todo);
            }
        });
    }

    public void update(final String name){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.updateContact(name);
            }
        });
    }




    public void deleteAll() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mTodoDao.deleteAll();
            }
        });
    }

    public class findContactAsync extends AsyncTask<String, Void, Todo> {
        private String name;
        private TodoDao dao;
        private TodoRepository repository;

        findContactAsync(TodoDao dao, String name) {
            this.dao = dao;
            this.name = name;
        }

        @Override
        protected Todo doInBackground(String... strings) {

            Todo todo = dao.findByName(strings[0]);
            return todo;
        }
    }
}
