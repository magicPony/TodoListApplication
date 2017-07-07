package com.example.taras.todolistapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.taras.todolistapplication.todoListModules.TodoListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IOnlineDbHandler {

    private final String TAG = "MainActivity";

    private TodoListAdapter mAdapter;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        initUI();
        mDatabase = FirebaseDatabase.getInstance();
    }

    private void initUI() {
        findViewById(R.id.fab_add_AM).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFragment fragment = new CreateFragment(MainActivity.this);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container_AM, fragment)
                        .addToBackStack(ApiConst.CREATE_FRAGMENT_TAG)
                        .commit();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_AM);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new TodoListAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void addTodo(TodoModel todo) {
        String key = mDatabase.getReference(ApiConst.TODO_LIST).push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, todo.toHashMap());
        mDatabase.getReference(ApiConst.TODO_LIST).updateChildren(childUpdates);
        // TODO: 7/7/2017  add to MainActivity recyclerView
        todo.setKey(key);
        mAdapter.addNote(todo);
    }

    @Override
    public void removeTodo(String id) {
        mDatabase.getReference(ApiConst.TODO_LIST).child(id).setValue(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference(ApiConst.TODO_LIST).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "OnDataChange");
                ArrayList<TodoModel> todos = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    TodoModel todo = data.getValue(TodoModel.class);
                    todo.setKey(data.getKey());
                    todos.add(todo);
                }

                mAdapter.initTodoList(todos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled");
            }
        });
    }
}
