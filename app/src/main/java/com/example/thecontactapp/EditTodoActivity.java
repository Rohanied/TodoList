package com.example.thecontactapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

public class EditTodoActivity extends AppCompatActivity  {
    public static final String EXTRA_REPLY =
            "com.example.android.TheContactApp.REPLY";
    public static final String EXTRA_REPLY_1 =
            "com.example.android.TheContactApp.REPLY";
    TextInputEditText title;
    TextInputEditText description;
    TextInputEditText date;
    TextInputEditText time;


    TodoViewModel viewModel;

    String descriptionPrev;
    Long idnow;
    String titlePrev;
    String datePrev;
    String timePrev;
    String idString;
    Boolean isCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        title = findViewById(R.id.title_text_edit);
        description = findViewById(R.id.desc_text_edit);
        date =findViewById(R.id.date_text_edit);
        time =findViewById(R.id.time_text_edit);

        final Intent i =getIntent();
        titlePrev =i.getStringExtra("title");
        descriptionPrev =i.getStringExtra("description");
        datePrev =i.getStringExtra("date");
        timePrev =i.getStringExtra("time");
        idString=i.getStringExtra("id");
        idnow=Long.parseLong(idString);
        isCompleted = i.getBooleanExtra("isCompleted", false);


        //filling fields
        title.setText(titlePrev);
        description.setText(descriptionPrev);
        date.setText(datePrev);
        time.setText(timePrev);
        Button save_button = findViewById(R.id.save_button_edit);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 if(TextUtils.isEmpty(title.getText())||TextUtils.isEmpty(description.getText())){
                   Toast.makeText(EditTodoActivity.this,"Name or Phone is Empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Todo todo = new Todo(idnow, title.getText().toString(), description.getText().toString(), date.getText().toString(), time.getText().toString(), isCompleted);

                    viewModel.update(todo);
                    Toast.makeText(EditTodoActivity.this,"Updating",Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        });

    }
}
