package com.example.thecontactapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerClickListener.OnItemClickListener {
    private static TodoViewModel mContactViewModel;
    private final int NEW_CONTACT_ACTIVITY = 1;
    private final int VIEW_CONTACT_ACTIVITY = 1;
    RecyclerView recyclerView;

    Todo todo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        final TodoAdapter adapter = new TodoAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContactViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        mContactViewModel.getAllTodo().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter.setContacts(todos);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivityForResult(intent, NEW_CONTACT_ACTIVITY);
            }
        });
        CoordinatorLayout layout = findViewById(R.id.main_view);
        final Snackbar snackbar = Snackbar.make(layout,"Contact Deleted", BaseTransientBottomBar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContactViewModel.insert(todo);
                    }
                });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        todo = adapter.getContactAtPosition(position);
                        mContactViewModel.deleteByName(todo.getTitle());
                        snackbar.show();
                    }
                });
        helper.attachToRecyclerView(recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_all) {
            mContactViewModel.deleteAll();
            Toast.makeText(this, "Clearing the contacts", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Todo todo) {
        //Toast.makeText(MainActivity.this,"Clicked success",Toast.LENGTH_SHORT).show();
        Intent reply = new Intent(MainActivity.this, ViewTodoActivity.class);
        reply.putExtra("title", todo.getTitle());
        String idS = String.valueOf(todo.getId());
        reply.putExtra("id", idS);
        reply.putExtra("description", todo.getDescription());
        reply.putExtra("date", todo.getDate());
        reply.putExtra("time", todo.getTime());
        reply.putExtra("isCompleted",todo.getIsCompleted());

        startActivity(reply);
    }

    @Override
    public void onCheck(Todo todo, Boolean isChecked) {
        todo.setIsCompleted(isChecked);
        mContactViewModel.update(todo);
    }
}
