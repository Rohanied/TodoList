package com.example.thecontactapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert
    void insert(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * from Todo ")
    LiveData<List<Todo>> getAllTodos();

    @Query("DELETE from Todo")
    void deleteAll();


    @Query("DELETE FROM Todo WHERE title = :title")
    void deleteTodo(String title);

    @Query("UPDATE Todo SET title = :title")
    void updateContact(String title);



    @Query("SELECT * FROM Todo WHERE title = :name")
    Todo findByName(String name);

    @Update
    void update(Todo todo);
}
