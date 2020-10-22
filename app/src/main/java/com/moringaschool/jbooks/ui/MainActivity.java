package com.moringaschool.jbooks.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moringaschool.jbooks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.getStartedButton) Button mGetStartedButton;
    @BindView(R.id.bookEditText) EditText bookEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mGetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book = bookEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);

            }

        });
    }
}