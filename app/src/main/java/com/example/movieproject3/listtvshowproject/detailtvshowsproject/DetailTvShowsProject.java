package com.example.movieproject3.listtvshowproject.detailtvshowsproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.movieproject3.R;
import com.example.movieproject3.databinding.ActivityProjectShowsBinding;
import com.example.movieproject3.listtvshowproject.pojo.ResultsItem;

import java.util.Objects;

public class DetailTvShowsProject extends AppCompatActivity {
    public static final String SELECTED_TV_PROJECT = "selected_tv_project";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DetailTvShowsViewModel viewModel = ViewModelProviders.of(this).get(DetailTvShowsViewModel.class);
        ActivityProjectShowsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_project_shows);
        ResultsItem tvShowModel = getIntent().getParcelableExtra(SELECTED_TV_PROJECT);
        Log.d("IDN","MovieModel : "+tvShowModel.getName());
        viewModel.setResultsItem(tvShowModel);
        binding.setViewmodel(viewModel);


        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+tvShowModel.getPosterPath()).into(binding.imagePoster);
        setTitle(viewModel.getResultsItem().getName());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
