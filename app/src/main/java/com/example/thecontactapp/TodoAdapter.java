package com.example.thecontactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {
    LayoutInflater mInflater;
    List<Todo> mTodos;
    //private static Clicklistener clicklistener;
    RecyclerClickListener.OnItemClickListener listener;


    TodoAdapter(Context context, RecyclerClickListener.OnItemClickListener listener){
        mInflater=LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TodoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, final int position) {
        if(mTodos !=null){

        final Todo todo = mTodos.get(position);

            holder.isCompletedCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    listener.onCheck(todo, b);
                }
            });

            holder.ll.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(todo);
                }
            });

        holder.phoneTextView.setText(todo.getDescription());
        holder.nameTextView.setText(todo.getTitle());
        if (todo.getIsCompleted())
            holder.isCompletedCheck.setChecked(true);
        else
            holder.isCompletedCheck.setChecked(false);
    }
        else {
            holder.nameTextView.setText("No Name");
            holder.phoneTextView.setText("No Phone");
        }
    }

    void setContacts(List<Todo> todos){
        mTodos = todos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTodos !=null)
            return mTodos.size();
        return 0;
    }


    class TodoHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView phoneTextView;
        private  CheckBox isCompletedCheck;
        private LinearLayout ll;

        private TodoHolder(View view){
            super(view);
            nameTextView=view.findViewById(R.id.name);
            phoneTextView=view.findViewById(R.id.phone);
            isCompletedCheck=view.findViewById(R.id.is_completed_checkbox);
            ll = view.findViewById(R.id.list_ll);
        }

    }

    public Todo getContactAtPosition(int position){
         return mTodos.get(position);
    }


}

