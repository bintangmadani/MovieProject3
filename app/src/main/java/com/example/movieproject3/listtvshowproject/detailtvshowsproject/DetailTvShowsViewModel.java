package com.example.movieproject3.listtvshowproject.detailtvshowsproject;

import androidx.lifecycle.ViewModel;

import com.example.movieproject3.listtvshowproject.pojo.ResultsItem;


public class DetailTvShowsViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() { return resultsItem; }

    public void setResultsItem(ResultsItem movieModel) {
        this.resultsItem = movieModel;
    }
}
