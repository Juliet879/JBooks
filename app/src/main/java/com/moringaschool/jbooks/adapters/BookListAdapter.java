package com.moringaschool.jbooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.ui.BookDetailActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private List<Item> mGoogle_books;
    private Context mContext;

    public BookListAdapter(Context context, List<Item> google_books) {
        mContext = context;
        mGoogle_books = google_books;
    }

    @Override
    public BookListAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookListAdapter.BookViewHolder holder, int position) {
        holder.bindGoogle_books(mGoogle_books.get(position));
    }

    @Override
    public int getItemCount() {
        return mGoogle_books.size();
    }

        public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            @BindView(R.id.bookImageView) ImageView mBookImageView;
            @BindView(R.id.titleTextView) TextView mTitleTextView;
            @BindView(R.id.publisherTextView) TextView mPublisherTextView;
            @BindView(R.id.selfLinkTextView) TextView mSelfLinkTextView;

            private Context mContext;

            public BookViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, BookDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("google_book", Parcels.wrap(mGoogle_books));
                mContext.startActivity(intent);
            }

            public void bindGoogle_books(Item google_books) {
                mTitleTextView.setText(google_books.getVolumeInfo().getTitle());
                mPublisherTextView.setText(google_books.getVolumeInfo().getPublisher());
                mSelfLinkTextView.setText(google_books.getSelfLink());
            }


        }
    }

