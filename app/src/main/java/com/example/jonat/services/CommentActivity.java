package com.example.jonat.services;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CommentActivity extends AppCompatActivity {

    public static final String mAccess = "access";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        getSupportFragmentManager().beginTransaction().add(R.id.comment_container, new CommentFragment()).commit();
    }
}
