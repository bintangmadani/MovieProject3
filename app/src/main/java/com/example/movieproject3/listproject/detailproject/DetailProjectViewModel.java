package com.example.movieproject3.listproject.detailproject;

import androidx.lifecycle.ViewModel;

import com.example.movieproject3.listproject.pojo.ResultsItem;

public class DetailProjectViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }
}
