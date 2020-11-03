package com.moringaschool.jbooks.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.jbooks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//
//    private ValueEventListener mSearchedBookReferenceListener;
//    private DatabaseReference mSearchedBookReference;

    @BindView(R.id.saveBookButton) Button mSavedBookButton;
    @BindView(R.id.getStartedButton) Button mGetStartedButton;
    @BindView(R.id.bookEditText) EditText mbookEditText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

//    private FirebaseRecyclerOptions<model> options;
//    private FirebaseRecyclerAdapter<model, MyViewHolder> adapter;
//
//    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //display welcome message
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }

            }
        };
//        mSearchedBookReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_BOOK);
//
//
//        mSearchedBookReferenceListener = mSearchedBookReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
//                for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
//                    String book = bookSnapshot.getValue().toString();
//                    Log.d("Book updated", "book: " + book); //log
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
//
//            }
//        });
//


//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//

//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mbookEditText = (EditText) findViewById(R.id.bookEditText);
        mGetStartedButton.setOnClickListener(this) ;

        mSavedBookButton.setOnClickListener(this);
//        options = new FirebaseRecyclerOptions.Builder<model>().setQuery(ref.model.class).build();
//        adapters = new FirebaseRecyclerAdapter<model,MyViewHolder>(options)
    }

        @Override
            public void onClick(View v) {
                if (v == mGetStartedButton) {
                    String book = mbookEditText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                    intent.putExtra("book", book);
                    startActivity(intent);
                }

//
//                    if(!(book).equals("")) {
//                        addToSharedPreferences(book);
//                    }
                if (v == mSavedBookButton) {
                    Intent intent = new Intent(MainActivity.this, SavedBookListActivity.class);
//                    intent.putExtra("book", book);
                    startActivity(intent);

                }
            }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);
    }
//    public void saveBookToFirebase(String book) {
//        mSearchedBookReference.push().setValue(book);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedBookReference.removeEventListener(mSearchedBookReferenceListener);
//    }
//
//    private void addToSharedPreferences(String book) {
//        mEditor.putString(Constants.PREFERENCES_BOOK_KEY, book).apply();    }
 }
