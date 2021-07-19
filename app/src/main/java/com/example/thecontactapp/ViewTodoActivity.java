package com.example.thecontactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTodoActivity extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView date;
    TextView time;
    TextView isCompleted;

    String descriptionView;
    long idw;
    String titleView;
    String dateView;
    String timeView;
    Boolean isCompletedView;
    String idString;

    Todo todo;

    TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

         title = findViewById(R.id.title_view);
         description = findViewById(R.id.desc_view);
         date = findViewById(R.id.date_view);
         time = findViewById(R.id.time_view);
         isCompleted = findViewById(R.id.is_completed_view);

        Intent i = getIntent();


        titleView =i.getStringExtra("title");
        descriptionView =i.getStringExtra("description");
        dateView =i.getStringExtra("date");
        timeView =i.getStringExtra("time");
        isCompletedView =i.getBooleanExtra("isCompleted", false);
        idString=i.getStringExtra("id");

        title.setText(titleView);
        description.setText(descriptionView);
        date.setText(dateView);
        time.setText(titleView);
        if(isCompletedView)
            isCompleted.setText("Task has been completed");
        else
            isCompleted.setText("Task still pending");
        idw=Long.parseLong(i.getStringExtra("id"));

        todo = new Todo(idw, titleView, descriptionView, dateView, timeView, isCompletedView);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_contact_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.delete:
                viewModel.deleteByName(titleView);
                Toast.makeText(ViewTodoActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();
                //Snackbar.make(,nameContact+"Contact Deleted",Snackbar.LENGTH_LONG).setAction("Undo", viewModel.insert(new Contact(0L,nameContact,phoneNo,emailC,ageC,gebderC,cityC,collegeC));)
                finish();
                return true;

            case R.id.edit:
                Intent reply = new Intent(ViewTodoActivity.this, EditTodoActivity.class);
                reply.putExtra("title", titleView);
                reply.putExtra("id",idString);
                reply.putExtra("description", descriptionView);
                reply.putExtra("date", dateView);
                reply.putExtra("time", timeView);
                reply.putExtra("isCompleted", isCompletedView);
                startActivity(reply);
                finish();
                return true;

            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, titleView);
                intent.putExtra(Intent.EXTRA_TEXT, titleView +"\n"+ descriptionView);
                startActivity(Intent.createChooser(intent,"Share List"));
                return true;
        }
        return false;
    }
}
