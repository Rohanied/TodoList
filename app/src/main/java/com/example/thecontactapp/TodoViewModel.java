package com.example.thecontactapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private LiveData<List<Todo>> allTodos;
    private TodoRepository mTodoRepository;

    public TodoViewModel(Application application){
        super(application);
        mTodoRepository = new TodoRepository(application);
        allTodos = mTodoRepository.getList();
    }

    LiveData<List<Todo>> getAllTodo()  { return allTodos; }

    public void insert(Todo todo){ mTodoRepository.insert(todo);}

    public void deleteAll(){ mTodoRepository.deleteAll();}

    public void delete(Todo todo){ mTodoRepository.insert(todo);}

    public void deleteByName(String name){ mTodoRepository.deleteByName(name);}

    public void update(Todo todo){ mTodoRepository.update(todo);}

    public void contactUpdate(String name){ mTodoRepository.update(name);}
}
