package com.moringaschool.jbooks.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moringaschool.jbooks.Constants;
import com.moringaschool.jbooks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @BindView(R.id.getStartedButton) Button mGetStartedButton;
    @BindView(R.id.bookEditText) EditText mbookEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();


        mGetStartedButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v == mGetStartedButton) {

                    String book = mbookEditText.getText().toString();
                    if(!(book).equals("")) {
                        addToSharedPreferences(book);
                    }
                    Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                    intent.putExtra("book", book);
                    startActivity(intent);

                }
            }

    private void addToSharedPreferences(String book) {
        mEditor.putString(Constants.PREFERENCES_BOOK_KEY, book).apply();    }
}