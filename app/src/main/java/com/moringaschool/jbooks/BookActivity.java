package com.moringaschool.jbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String book = intent.getStringExtra("books");
        mBookTextView.setText("Current books available: " + book);


        GoogleApi client = GoogleClient.getClient();

            Call<GoogleBookSearchResponse> call = client.getBooks(book, "books");

        call.enqueue(new Callback<GoogleBookSearchResponse>() {
            @Override
            public void onResponse(Call<GoogleBookSearchResponse> call, Response<GoogleBookSearchResponse> response) {
                if (response.isSuccessful()) {
                    List<VolumeInfo> booksList = response.body().getVolumeinfos();
                    String[] books = new String[books.size()];

                    for (int i = 0; i < books.length; i++){
                        books[i] = booksList.get(i).getTitle();
                    }

                    for (int i = 0; i < item.length; i++) {
                        Item item = booksList.get(i).getCategories().get(0);
                        items[i] = item.getKind();
                    }

                    ArrayAdapter adapter
                            = new MyRestaurantsArrayAdapter(RestaurantsActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
                    mListView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {

            }

        });
    }
}