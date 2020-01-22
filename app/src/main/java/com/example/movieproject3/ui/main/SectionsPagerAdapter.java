package com.example.movieproject3.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.movieproject3.R;
import com.example.movieproject3.listproject.ListProjectsFragment;
import com.example.movieproject3.listtvshowproject.ListProjectShowFragment;


class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLE = new int[]{R.string.tab_movies, R.string.tab_tv_shows};
    private final Context mcontext;
    private ListProjectsFragment moviesFragment;
    private ListProjectShowFragment tvShowFragment;

    SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mcontext = context;
        moviesFragment = new ListProjectsFragment();
        tvShowFragment = new ListProjectShowFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return moviesFragment;
        } else {
           return tvShowFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mcontext.getResources().getString(TAB_TITLE[position]);
    }

    @Override
    public int getCount() { return 2; }
}
