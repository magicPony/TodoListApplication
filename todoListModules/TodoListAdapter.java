package com.example.taras.todolistapplication.todoListModules;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taras.todolistapplication.IOnlineDbHandler;
import com.example.taras.todolistapplication.R;
import com.example.taras.todolistapplication.TodoModel;

import java.util.ArrayList;

/**
 * Created by Taras on 7/6/2017.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private final String TAG = "TodoListAdapter";

    private IOnlineDbHandler mDbHandler;
    private ArrayList<TodoModel> mTodos = new ArrayList<>();

    public TodoListAdapter(IOnlineDbHandler dbHandler) {
        mDbHandler = dbHandler;
    }

    public void initTodoList(ArrayList<TodoModel> todos) {
        mTodos = todos;
        notifyDataSetChanged();
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_row, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TodoViewHolder holder, final int position) {
        final TodoModel todo = mTodos.get(position);
        holder.tvTitle.setText(todo.getTitle());
        holder.tvMessage.setText(todo.getMessage());
        holder.tvDate.setText(todo.getDate());
        holder.itemView.setTag(todo.getKey());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "clicked on " + holder.itemView.getTag());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTodos.remove(position);
                notifyItemRemoved(position);
                mDbHandler.removeTodo(todo.getKey());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    public void addNote(TodoModel todo) {
        mTodos.add(todo);
        notifyItemInserted(getItemCount() - 1);
    }
}
