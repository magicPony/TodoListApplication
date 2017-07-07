package com.example.taras.todolistapplication.todoListModules;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taras.todolistapplication.R;

/**
 * Created by Taras on 7/6/2017.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle, tvMessage, tvDate;
    public Button btnDelete;
    public View itemView;


    public TodoViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title_LTR);
        tvMessage = (TextView) itemView.findViewById(R.id.tv_message_LTR);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date_LTR);
        btnDelete = (Button) itemView.findViewById(R.id.btn_delete_LTR);
    }
}
