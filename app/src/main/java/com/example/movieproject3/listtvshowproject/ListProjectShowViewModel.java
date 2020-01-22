package com.example.movieproject3.listtvshowproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.movieproject3.KatalogFIlm;
import com.example.movieproject3.listtvshowproject.pojo.ResponseProjectShows;

public class ListProjectShowViewModel extends ViewModel {
    private MutableLiveData<ResponseProjectShows> responseTvShows = new MutableLiveData<>();

    MutableLiveData getTvShowList() {
        if (responseTvShows == null) {
            doRequestListTvshows();
        }
        return responseTvShows;
    }

    void doRequestListTvshows() {
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/tv")
                .addQueryParameter("api_key", KatalogFIlm.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseProjectShows.class, new ParsedRequestListener<ResponseProjectShows>() {
                    @Override
                    public void onResponse(ResponseProjectShows response) {
                        responseTvShows.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseTvShows.setValue(new ResponseProjectShows(anError));
                    }
                });
    }
}
