package com.moringaschool.jbooks.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.jbooks.Constants;
import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.models.Item;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailFragment extends Fragment implements View.OnClickListener {
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
        mGoogle_book = Parcels.unwrap(getArguments().getParcelable("google_book"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        ButterKnife.bind(this, view);

        mTitleLabel.setText(mGoogle_book.getVolumeInfo().getTitle());
        mPublisherLabel.setText(mGoogle_book.getVolumeInfo().getPublisher());
        mSelfLinkLabel.setText(mGoogle_book.getSelfLink());
        mPhoneLabel.setText(mGoogle_book.getAccessInfo().getAccessViewStatus());
//          mPhoneLabel.setOnClickListener(this);
        mSaveBookButton.setOnClickListener(this);

        mPhoneLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mSaveBookButton) {
//            DatabaseReference booksRef = FirebaseDatabase
//                    .getInstance()
//                    .getReference(Constants.FIREBASE_CHILD_BOOKS);
//            booksRef.push().setValue(mGoogle_book);
//            Toast.makeText(getContext(), "saved clearly", Toast.LENGTH_SHORT).show();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference google_bookRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BOOKS)
                    .child(uid);
            DatabaseReference pushRef = google_bookRef.push();
            String pushId = pushRef.getKey();
            mGoogle_book.setPushId(pushId);
            pushRef.setValue(mGoogle_book);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}


