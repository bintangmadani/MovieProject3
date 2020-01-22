package com.example.movieproject3.listproject.detailproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.movieproject3.R;
import com.example.movieproject3.databinding.ActivityDetailProjectBinding;
import com.example.movieproject3.databinding.ActivityDetailProjectBinding;
import com.example.movieproject3.listproject.pojo.ResultsItem;

import java.util.Objects;

public class DetailProject extends AppCompatActivity {
    public static final String SELECTED_PROJECT = "selected_project";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DetailProjectViewModel viewModel = ViewModelProviders.of(this).get(DetailProjectViewModel.class);
        ActivityDetailProjectBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_project);
        ResultsItem moviemodel = getIntent().getParcelableExtra(SELECTED_PROJECT);
        viewModel.setResultsItem(moviemodel);
        binding.setViewmodel(viewModel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+moviemodel.getPosterPath()).into(binding.imageposter);
        setTitle(viewModel.getResultsItem().getTitle());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
