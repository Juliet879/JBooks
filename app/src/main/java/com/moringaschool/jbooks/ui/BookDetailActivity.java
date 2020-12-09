package com.moringaschool.jbooks.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.moringaschool.jbooks.R;
import com.moringaschool.jbooks.adapters.BookPagerAdapter;
import com.moringaschool.jbooks.models.Item;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private BookPagerAdapter adapterViewPager;
    List<Item> mGoogle_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        mGoogle_book = Parcels.unwrap(getIntent().getParcelableExtra("google_book"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new BookPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mGoogle_book);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}