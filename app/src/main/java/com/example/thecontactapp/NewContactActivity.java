package com.example.thecontactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class NewContactActivity extends AppCompatActivity  {
    public static final String EXTRA_REPLY =
            "com.example.android.TheContactApp.REPLY";
    public static final String EXTRA_REPLY_1 =
            "com.example.android.TheContactApp.REPLY";
    TextInputEditText title;
    TextInputEditText description;
    TextInputEditText date;
    TextInputEditText time;
    TodoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        viewModel = ViewModelProviders.of(this).get(TodoViewModel.class);

        title = findViewById(R.id.title_text);
        description = findViewById(R.id.desc_text);
        date =findViewById(R.id.date_text);
        time =findViewById(R.id.time_text);



        Button save_button = findViewById(R.id.save_button);




        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(title.getText())||TextUtils.isEmpty(description.getText())){
                    Toast.makeText(NewContactActivity.this, "Please Enter All fields",Toast.LENGTH_SHORT).show();

                }
                else {
                    Todo todo = new Todo(0L, title.getText().toString(), description.getText().toString(), date.getText().toString(), time.getText().toString(), false);
                    viewModel.insert(todo);
                    Toast.makeText(NewContactActivity.this, "Saving Contact",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
