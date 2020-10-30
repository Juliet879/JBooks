package com.moringaschool.jbooks.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.jbooks.Constants;
import com.moringaschool.jbooks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private ValueEventListener mSearchedBookReferenceListener;
    private DatabaseReference mSearchedBookReference;

    @BindView(R.id.getStartedButton) Button mGetStartedButton;
    @BindView(R.id.bookEditText) EditText mbookEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedBookReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_BOOK);


        mSearchedBookReferenceListener = mSearchedBookReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                    String book = bookSnapshot.getValue().toString();
                    Log.d("Book updated", "book: " + book); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//

        mGetStartedButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v == mGetStartedButton) {

                    String book = mbookEditText.getText().toString();
//                    if(!(book).equals("")) {
//                        addToSharedPreferences(book);
//                    }
                    saveBookToFirebase(book);
                    Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                    intent.putExtra("book", book);
                    startActivity(intent);

                }
            }
    public void saveBookToFirebase(String book) {
        mSearchedBookReference.push().setValue(book);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedBookReference.removeEventListener(mSearchedBookReferenceListener);
    }

//    private void addToSharedPreferences(String book) {
//        mEditor.putString(Constants.PREFERENCES_BOOK_KEY, book).apply();    }
}