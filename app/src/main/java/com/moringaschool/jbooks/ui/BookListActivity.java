package com.moringaschool.jbooks.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.jbooks.adapters.BookListAdapter;
import com.moringaschool.jbooks.models.GoogleBooksSearchResponse;
import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.network.GoogleApi;
import com.moringaschool.jbooks.network.GoogleClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends AppCompatActivity {
    private static final String TAG = BookListActivity.class.getSimpleName();
//    private String[] books = new String[] {"Bye and Bye", "Living Today","Better with God"};
//    @BindView(R.id.bookTextView) TextView mBookTextView;
//    @BindView(R.id.listView) ListView mListView;

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

        Intent intent = getIntent();
        String book = intent.getStringExtra("book");
        intent.putExtra("book",book);


        GoogleApi client = GoogleClient.getClient();

            Call<GoogleBooksSearchResponse> Call = client.getBooks(book, "AIzaSyCgkz3A0W4a-AMczr9uiRCVyVjiFBbqvOc");

        Call.enqueue(new Callback<GoogleBooksSearchResponse>() {

            @Override
            public void onResponse(Call<GoogleBooksSearchResponse> call, Response<GoogleBooksSearchResponse> response) {
                
                hideProgressBar();

                if (response.isSuccessful()) {

                    google_book = response.body().getItems();
                mAdapter = new BookListAdapter(BookListActivity.this, google_book);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookListActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
//                if (response.isSuccessful()) {
//                    List<Item> booksList = response.body().getItems();
//                    String[] google_books = new String[booksList.size()];
//
//                    for (int i = 0; i < google_books.length; i++){
//                        google_books[i] = booksList.get(i).getVolumeInfo().getTitle();
//                    }
//
////
//
//                    ArrayAdapter adapter = new BooksArrayAdapter(BookActivity.this, android.R.layout.simple_list_item_1, books);
//                    mListView.setAdapter(adapter);
                    showBooks();

            } else {
                showUnsuccessfulMessage();
            }
        }

        @Override
        public void onFailure(Call<GoogleBooksSearchResponse> call, Throwable t) {
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

    private void showBooks() {
        mRecyclerView.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}