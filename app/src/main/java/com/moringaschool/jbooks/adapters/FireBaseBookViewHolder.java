package com.moringaschool.jbooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.jbooks.Constants;
import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.ui.BookDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FireBaseBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    public ImageView mBookImageView;
    Context mContext;


    public FireBaseBookViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

        public void bindGoogle_book(Item google_book){

//        mBookImageView = itemView.findViewById(R.id.BookImageView);
//        TitleTextView = itemView.findViewById(R.id.TitleTextView);
//        PublisherTextView = itemView.findViewById(R.id.PublisherTextView);
//        SelfLinkTextView = itemView.findViewById(R.id.SelfLinkTextView);

            mBookImageView = mView.findViewById(R.id.bookImageView);
            TextView titleTextView = mView.findViewById(R.id.titleTextView);
            TextView publisherTextView = mView.findViewById(R.id.publisherTextView);
            TextView selfLinkTextView = mView.findViewById(R.id.selfLinkTextView);

            titleTextView.setText(google_book.getVolumeInfo().getTitle());
            publisherTextView.setText(google_book.getVolumeInfo().getPublisher());
            selfLinkTextView.setText(google_book.getSelfLink());


    }

    @Override
    public void onClick(View view) {
        final ArrayList<Item> google_boook = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BOOKS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    google_boook.add(snapshot.getValue(Item.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, BookDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("google_boook", Parcels.wrap(google_boook));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}

//    View mView;
//    Context mContext;
//
//    public FireBaseBookViewHolder(View itemView) {
//        super(itemView);
//        mView = itemView;
//        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
//    }
//
//    public void bindGoogle_book(Item google_book) {
//        ImageView bookImageView = (ImageView) mView.findViewById(R.id.bookImageView);
//        TextView titleTextView = (TextView) mView.findViewById(R.id.titleTextView);
//        TextView publisherTextView = (TextView) mView.findViewById(R.id.publisherTextView);
//        TextView selfLinkTextView = (TextView) mView.findViewById(R.id.selfLinkTextView);
//
////        Picasso.get().load(restaurant.getImageUrl()).into(restaurantImageView);
//
//        titleTextView.setText(google_book.getVolumeInfo().getTitle());
//        publisherTextView.setText(google_book.getVolumeInfo().getPublisher());
//        selfLinkTextView.setText(google_book.getSelfLink());
//    }
//
//    @Override
//    public void onClick(View view) {
//        final List<Item> google_book = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BOOKS);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    google_book.add(snapshot.getValue(Item.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, BookDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("google_book", Parcels.wrap(google_book));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }
//}
