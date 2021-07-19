package com.example.thecontactapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private LiveData<List<Todo>> mAllContact;
    private TodoRepository mContactRepository;

    public TodoViewModel(Application application){
        super(application);
        mContactRepository = new TodoRepository(application);
        mAllContact = mContactRepository.getAllContacts();
    }

    LiveData<List<Todo>> getAllContact()  { return mAllContact; }

    public void insert(Todo todo){ mContactRepository.insert(todo);}

    public void deleteAll(){ mContactRepository.deleteAll();}

    public void delete(Todo todo){ mContactRepository.insert(todo);}

    public void deleteByName(String name){ mContactRepository.deleteByName(name);}

    public void update(Todo todo){ mContactRepository.update(todo);}

    public void contactUpdate(String name){ mContactRepository.update(name);}
}
