package com.moringaschool.jbooks.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.jbooks.Constants;
import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.adapters.FireBaseBookViewHolder;
import com.moringaschool.jbooks.adapters.FirebaseBookListAdapter;
import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.util.ItemTouchHelperAdapter;
import com.moringaschool.jbooks.util.OnStartDragListener;
import com.moringaschool.jbooks.util.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedBookListActivity extends AppCompatActivity implements OnStartDragListener {
//    private DatabaseReference mGoogle_bookReference;
//    private FirebaseRecyclerAdapter<Item, FireBaseBookViewHolder> mFirebaseAdapter;
//
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book);
//        ButterKnife.bind(this);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        mGoogle_bookReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_BOOKS)
//                .child(uid);
//
//        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter(){
//        FirebaseRecyclerOptions<Item> options =
//                new FirebaseRecyclerOptions.Builder<Item>()
//                        .setQuery(mGoogle_bookReference, Item.class)
//                        .build();
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Item, FireBaseBookViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull FireBaseBookViewHolder fireBaseBookViewHolder, int i, @NonNull Item google_book) {
////                FireBaseBookViewHolder.bindGoogle_book(google_book);
//
//            }
//
//            @NonNull
//            @Override
//            public FireBaseBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
//                return new FireBaseBookViewHolder(view);
//            }
//        };
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirebaseAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(mFirebaseAdapter!= null) {
//            mFirebaseAdapter.stopListening();
//        }
//    }

    private DatabaseReference mGoogle_bookReference;
    private FirebaseBookListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mGoogle_bookReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BOOKS).child(uid);
        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(mGoogle_bookReference, Item.class)
                        .build();

        mFirebaseAdapter = new FirebaseBookListAdapter(options, mGoogle_bookReference, this, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }

}
