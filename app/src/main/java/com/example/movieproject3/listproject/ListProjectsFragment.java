package com.example.movieproject3.listproject;

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
import com.example.movieproject3.listproject.detailproject.DetailProject;
import com.example.movieproject3.listproject.pojo.ResponseProjects;
import com.example.movieproject3.listproject.pojo.ResultsItem;

public class ListProjectsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;

    private Observer<ResponseProjects> getMovies = new Observer<ResponseProjects>() {
        @Override
        public void onChanged(ResponseProjects responseMovies) {
            if(responseMovies!=null){
                if(responseMovies.getAnError()==null){
                    ListProjectsAdapter madapter = new ListProjectsAdapter(responseMovies.getResults());
                    madapter.setOnItemClickListener(new ListProjectsAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, ResultsItem model) {
                            Intent gotodetailMovie = new Intent(view.getContext(), DetailProject.class);
                            gotodetailMovie.putExtra(DetailProject.SELECTED_PROJECT,model);
                            startActivity(gotodetailMovie);
                        }
                    });
                    recyclerView.setAdapter(madapter);
                }else{
                    alertDialog.setMessage(responseMovies.getAnError().getMessage());
                    alertDialog.show();
                }
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_projects_fragment, container, false);
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
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        }).create();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ListProjectsViewModel mViewModel = ViewModelProviders.of(this).get(ListProjectsViewModel.class);
        mViewModel.doRequestListMovies();
        mViewModel.getMovies().observe(this,getMovies);
    }
}
