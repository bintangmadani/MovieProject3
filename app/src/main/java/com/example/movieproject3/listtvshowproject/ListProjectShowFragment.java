package com.example.movieproject3.listtvshowproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieproject3.R;
import com.example.movieproject3.listtvshowproject.detailtvshowsproject.DetailTvShowsProject;
import com.example.movieproject3.listtvshowproject.pojo.ResponseProjectShows;
import com.example.movieproject3.listtvshowproject.pojo.ResultsItem;

public class ListProjectShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;

    private Observer<ResponseProjectShows> getTvShows = new Observer<ResponseProjectShows>() {
        @Override
        public void onChanged(ResponseProjectShows responseTvShows) {
            if(responseTvShows!=null){
                if(responseTvShows.getAnError()==null){
                    ListProjectShowsAdapter madapter = new ListProjectShowsAdapter(responseTvShows.getResults());
                    madapter.SetOnItemClickListener(new ListProjectShowsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent goToDetailMovie = new Intent(view.getContext(), DetailTvShowsProject.class);
                            goToDetailMovie.putExtra(DetailTvShowsProject.SELECTED_TV_PROJECT,model);
                            startActivity(goToDetailMovie);
                        }
                    });

                    recyclerView.setAdapter(madapter);
                }else{
                    alertDialog.setMessage(responseTvShows.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_project_show_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressbar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ListProjectShowViewModel mviewModel = ViewModelProviders.of(this).get(ListProjectShowViewModel.class);
        mviewModel.doRequestListTvshows();
        mviewModel.getTvShowList().observe(this, getTvShows);
    }
}
