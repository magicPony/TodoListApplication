package com.example.taras.todolistapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Taras on 7/6/2017.
 */

@SuppressLint("ValidFragment")
public class CreateFragment extends Fragment implements View.OnClickListener {

    private EditText etTitle, etMessage;
    private IOnlineDbHandler mDbHandler;

    @SuppressLint("ValidFragment")
    public CreateFragment(IOnlineDbHandler dbHandler) {
        mDbHandler = dbHandler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        etTitle = (EditText) view.findViewById(R.id.et_title_FC);
        etMessage = (EditText) view.findViewById(R.id.et_message_FC);
        view.findViewById(R.id.btn_ok_FC).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        TodoModel todo = new TodoModel();

        if (!TextUtils.isEmpty(etTitle.getText())) {
            todo.setTitle(etTitle.getText().toString());
        }

        if (!TextUtils.isEmpty(etMessage.getText())) {
            todo.setMessage(etMessage.getText().toString());
        }

        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
        todo.setDate(date);
        mDbHandler.addTodo(todo);
        getActivity().onBackPressed();
    }
}
