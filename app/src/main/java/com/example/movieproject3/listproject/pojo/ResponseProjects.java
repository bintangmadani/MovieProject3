package com.example.movieproject3.listproject.pojo;

import androidx.annotation.NonNull;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseProjects {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("total_results")
    private int totalResults;

    private ANError anError;

    public ANError getAnError() { return anError; }

    public ResponseProjects() {
    }

    public ResponseProjects(ANError anError) { this.anError = anError; }

    public List<ResultsItem> getResults() { return results; }

    @NonNull
    @Override
    public String toString() {
        return
                "ResponseProjects{" +
                        "page = '" + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        "total_results = '" + totalResults + '\'' +
                        "}";
    }
}
