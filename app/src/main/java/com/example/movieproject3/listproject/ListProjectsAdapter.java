package com.example.movieproject3.listproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieproject3.R;
import com.example.movieproject3.listproject.pojo.ResultsItem;

import java.util.List;

public class ListProjectsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResultsItem> itemlist;

    private OnItemClickListener mitemclickListener;

    public ListProjectsAdapter(List<ResultsItem> itemlist) { this.itemlist = itemlist; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_project_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            final ResultsItem model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTextTitle.setText(model.getTitle());

            if(model.getOverview().length()>50) {
                genericViewHolder.itemTextOverview.setText(model.getOverview().substring(0,49)+" ... ");
            }else{
                genericViewHolder.itemTextOverview.setText(model.getOverview());
            }

            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w185"+model.getPosterPath()).into(genericViewHolder.imageposter);



        }
    }

    @Override
    public int getItemCount() { return itemlist.size(); }

    public void setOnItemClickListener(final OnItemClickListener mitemclickListener) {
        this.mitemclickListener = mitemclickListener;
    }

    private ResultsItem getItem(int position) { return itemlist.get(position); }

    public interface OnItemClickListener {
        void onItemClick(View view, ResultsItem model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageposter;
        private TextView itemTextTitle;
        private TextView itemTextOverview;

        ViewHolder(final View itemView) {
            super(itemView);

            this.imageposter = itemView.findViewById(R.id.imageposter);
            this.itemTextTitle = itemView.findViewById(R.id.item_text_title);
            this.itemTextOverview = itemView.findViewById(R.id.item_text_overview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mitemclickListener.onItemClick(itemView, itemlist.get(getAdapterPosition()));

                }
            });
        }
    }
}
