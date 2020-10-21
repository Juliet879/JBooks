package com.moringaschool.jbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookActivity extends AppCompatActivity {
    private String[] books = new String[] {"Bye and Bye", "Living Today","Better with God"};
    @BindView(R.id.bookTextView) TextView mBookTextView;
    @BindView(R.id.listView) ListView mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);

//        mListView = (ListView) findViewById(R.id.listView);
//        mBookTextView = (TextView) findViewById(R.id.bookTextView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String book = intent.getStringExtra("book");
        mBookTextView.setText("Current books available: " + book);
    }
}