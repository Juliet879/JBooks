package com.moringaschool.jbooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.moringaschool.jbooks.models.Item;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailFragment extends Fragment {
    @BindView(R.id.bookImageView) ImageView mImageLabel;
    @BindView(R.id.titleTextView) TextView mTitleLabel;
    @BindView(R.id.publisherTextView) TextView mPublisherLabel;
    @BindView(R.id.selfLinkTextView) TextView mSelfLinkLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.saveBookButton) TextView mSaveBookButton;

    private Item mGoogle_book;

    public BookDetailFragment() {
        // Required empty public constructor
    }

    public static BookDetailFragment newInstance(Item google_book) {
        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("google_book", Parcels.wrap(google_book));
        bookDetailFragment.setArguments(args);
        return bookDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoogle_book = Parcels.unwrap(getArguments().getParcelable("restaurant"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        ButterKnife.bind(this, view);

        List<String> categories = new ArrayList<>();


//        mTitleLabel.setText(mGoogle_book.getVolumeInfo().getTitle());
//        mPublisherLabel.setText(mGoogle_book.getVolumeInfo().getPublisher());
//        mSelfLinkLabel.setText(mGoogle_book.getSelfLink());
//        mPhoneLabel.setText(mGoogle_book.getAccessInfo().getAccessViewStatus());

        return view;
    }
}