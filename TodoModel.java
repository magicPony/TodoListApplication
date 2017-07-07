package com.example.taras.todolistapplication;

import java.util.HashMap;

/**
 * Created by Taras on 7/6/2017.
 */

public class TodoModel {
    private String title = "title";
    private String message = "message";
    private String date = "01/01/1999";
    private String key = "-1";

    public TodoModel() {
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getKey() {
        return key;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setKey(String id) {
        this.key = id;
    }

    public HashMap<String, String> toHashMap() {
        HashMap<String, String> res = new HashMap<>();
        res.put(ApiConst.TITLE, title);
        res.put(ApiConst.MESSAGE, message);
        res.put(ApiConst.DATE, date);
        res.put(ApiConst.KEY, key);
        return res;
    }
}
