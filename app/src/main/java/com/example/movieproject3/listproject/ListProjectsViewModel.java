package com.example.movieproject3.listproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.movieproject3.KatalogFIlm;
import com.example.movieproject3.listproject.pojo.ResponseProjects;

public class ListProjectsViewModel extends ViewModel {

    private MutableLiveData<ResponseProjects> responseMovies = new MutableLiveData<>();


    public MutableLiveData getMovies(){
        if (responseMovies==null){
            doRequestListMovies();
        }
        return responseMovies;
    }

    public void doRequestListMovies(){
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", KatalogFIlm.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseProjects.class, new ParsedRequestListener<ResponseProjects>() {
                    @Override
                    public void onResponse(ResponseProjects response) {
                        responseMovies.postValue(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        responseMovies.setValue(new ResponseProjects(anError));
                    }
                });
    }
}
