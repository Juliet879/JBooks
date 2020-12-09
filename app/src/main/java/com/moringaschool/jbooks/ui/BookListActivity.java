package com.moringaschool.jbooks.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.moringaschool.jbooks.Constants;
import com.moringaschool.jbooks.adapters.BookListAdapter;
import com.moringaschool.jbooks.models.GoogleBooksSearchResponse;
import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.network.GoogleApi;
import com.moringaschool.jbooks.network.GoogleClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends AppCompatActivity {
    private static final String TAG = BookListActivity.class.getSimpleName();
//    ////    private String[] books = new String[] {"Bye and Bye", "Living Today","Better with God"};
//////    @BindView(R.id.bookTextView) TextView mBookTextView;
//////    @BindView(R.id.listView) ListView mListView;
////
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentAddress;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private BookListAdapter mAdapter;
    public List<Item> google_book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
//        mListView.setAdapter(adapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String book = ((TextView)view).getText().toString();
//                Toast.makeText(BookActivity.this, book, Toast.LENGTH_LONG).show();
//            }
//        });
//

        final Intent intent = getIntent();
        String book = intent.getStringExtra("book");
        intent.putExtra("book", book);

//        getGoogle_book(book);
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_BOOK_KEY, null);
//
//        if (mRecentAddress != null) {
//            getGoogle_book(mRecentAddress);
//        }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//
//        final MenuItem menuItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                addToSharedPreferences(query);
//                getGoogle_book(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    private void addToSharedPreferences(String book) {
//        mEditor.putString(Constants.PREFERENCES_BOOK_KEY, book).apply();
//    }

//    private void getGoogle_book(String book) {
//        final GoogleClient googleClient = new GoogleClient();
//        googleClient.findGoogle_book(book, new Callback() {

    //
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                t.printStackTrace();
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                googleClient.processResults(response);
//                BookListActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        mAdapter = new BookListAdapter(getApplicationContext(), google_book);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookListActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//
//
//        });
//    }
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_BOOK_KEY, null);
//        if(mRecentAddress != null){
//            getGoogle_books(mRecentAddress);
//        }
//    }


        GoogleApi client = GoogleClient.getClient();

    Call<GoogleBooksSearchResponse> Call = client.getBooks(book, "AIzaSyCgkz3A0W4a-AMczr9uiRCVyVjiFBbqvOc");

    Call.enqueue(new Callback<GoogleBooksSearchResponse>() {


        @Override
    public void onResponse(Call<GoogleBooksSearchResponse> call, Response<GoogleBooksSearchResponse> response) {
            if (response.isSuccessful()) {
                hideProgressBar();
                google_book = response.body().getItems();
                mAdapter = new BookListAdapter(BookListActivity.this, google_book);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookListActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
                showGoogle_book();
            } else {
                showUnsuccessfulMessage();
            }
        }
        @Override
        public void onFailure(retrofit2.Call<GoogleBooksSearchResponse> call, Throwable t) {
            Log.e(TAG, "onFailure:", t);
            hideProgressBar();
            showFailureMessage();
        }
    });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showGoogle_book() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}