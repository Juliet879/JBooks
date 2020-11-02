package com.moringaschool.jbooks.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.BookDetailFragment;

import java.util.List;

public class BookPagerAdapter extends FragmentPagerAdapter {
    private List<Item> mGoogle_book;

    public BookPagerAdapter(FragmentManager fm, int behavior, List<Item> google_book) {
        super(fm, behavior);
        mGoogle_book = google_book;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BookDetailFragment.newInstance(mGoogle_book.get(position));
    }

    @Override
    public int getCount() {
        return mGoogle_book.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGoogle_book.get(position).getVolumeInfo().getTitle();
    }
}