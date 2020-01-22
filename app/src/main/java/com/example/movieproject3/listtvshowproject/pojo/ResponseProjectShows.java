package com.example.movieproject3.listtvshowproject.pojo;

import androidx.annotation.NonNull;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseProjectShows {
    private ANError anError;

    public ResponseProjectShows(ANError anError) { this.anError = anError; }

    public ResponseProjectShows() {
    }

    public ANError getAnError() { return anError; }

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("total_result")
    private int totalResult;


    public List<ResultsItem> getResults() { return results; }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseProjects{" +
                        "page = '" + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        "total_results = '" + totalResult + '\'' +
                        "}";
    }
}
