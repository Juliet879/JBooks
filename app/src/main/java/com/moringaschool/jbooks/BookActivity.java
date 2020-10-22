package com.moringaschool.jbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.network.GoogleApi;
import com.moringaschool.jbooks.network.GoogleClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = BookActivity.class.getSimpleName();
//    private String[] books = new String[] {"Bye and Bye", "Living Today","Better with God"};
    @BindView(R.id.bookTextView) TextView mBookTextView;
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
//        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String book = ((TextView)view).getText().toString();
                Toast.makeText(BookActivity.this, book, Toast.LENGTH_LONG).show();
            }
        });

//        mListView = (ListView) findViewById(R.id.listView);
//        mBookTextView = (TextView) findViewById(R.id.bookTextView);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, books);
//        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String book = intent.getStringExtra("book");
        mBookTextView.setText("Current books available: " + book);


        GoogleApi client = GoogleClient.getClient();

            Call<GoogleBookSearchResponse> call = client.getBooks("quilting", "AIzaSyCgkz3A0W4a-AMczr9uiRCVyVjiFBbqvOc");

        call.enqueue(new Callback<GoogleBookSearchResponse>() {
            @Override
            public void onResponse(Call<GoogleBookSearchResponse> call, Response<GoogleBookSearchResponse> response) {
                
                hideProgressBar();
                
                if (response.isSuccessful()) {
                    List<Item> booksList = response.body().getItems();
                    String[] books = new String[booksList.size()];
//                    String[] volumeinfos = new String[booksList.size()];

                    for (int i = 0; i < books.length; i++){
                        books[i] = booksList.get(i).getKind();
                    }

//                    for (int i = 0; i < volumeinfos.length; i++) {
//                        VolumeInfo volumeinfo = booksList.get(i).getVolumeInfos().get(0);
//                        volumeinfos[i] = volumeinfo.getTitle();
//                    }

                    ArrayAdapter adapter = new BooksArrayAdapter(BookActivity.this, android.R.layout.simple_list_item_1, books);
                    mListView.setAdapter(adapter);
                    
                    showBooks();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<GoogleBookSearchResponse> call, Throwable t) {
                Log.e(TAG,"onFailure: ",t );
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
        mListView.setVisibility(View.VISIBLE);
        mBookTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}