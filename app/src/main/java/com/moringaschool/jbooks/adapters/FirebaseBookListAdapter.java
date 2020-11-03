package com.moringaschool.jbooks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.ui.SavedBookListActivity;
import com.moringaschool.jbooks.util.ItemTouchHelperAdapter;
import com.moringaschool.jbooks.util.OnStartDragListener;

public class FirebaseBookListAdapter extends FirebaseRecyclerAdapter<Item, FireBaseBookViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;


    public FirebaseBookListAdapter(FirebaseRecyclerOptions<Item> options,
                                   DatabaseReference ref,
                                   OnStartDragListener onStartDragListener,
                                   Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final FireBaseBookViewHolder fireBaseBookViewHolder, int position, @NonNull Item google_book) {
        fireBaseBookViewHolder.bindGoogle_book(google_book);
        fireBaseBookViewHolder.mBookImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(fireBaseBookViewHolder);
                }
                return false;
            }
        });

    }

    @NonNull
    @Override
    public FireBaseBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item_drag,parent,false);
        return new FireBaseBookViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition,int toPosition){
        notifyItemMoved(fromPosition,toPosition);
        return false;
    }


    @Override
    public void onItemDismiss(int position){
        getRef(position).removeValue();
    }
}