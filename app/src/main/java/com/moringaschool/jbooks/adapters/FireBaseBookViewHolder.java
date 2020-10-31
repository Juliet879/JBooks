package com.moringaschool.jbooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class FireBaseBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    static View mView;
    Context mContext;

    public FireBaseBookViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }


    public static void bindGoogle_book(Item google_boook) {
        ImageView bookImageView = (ImageView) mView.findViewById(R.id.bookImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.titleTextView);
        TextView publisherTextView = (TextView) mView.findViewById(R.id.publisherTextView);
        TextView selfLinkTextView = (TextView) mView.findViewById(R.id.selfLinkTextView);


        titleTextView.setText(google_boook.getVolumeInfo().getTitle());
        publisherTextView.setText(google_boook.getVolumeInfo().getPublisher());
        selfLinkTextView.setText(google_boook.getSelfLink());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Item> google_boook = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BOOKS);
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
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
