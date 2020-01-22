package com.example.movieproject3.listtvshowproject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieproject3.R;
import com.example.movieproject3.listtvshowproject.pojo.ResultsItem;

import java.util.List;

public class ListProjectShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ResultsItem> itemList;

    private OnItemClickListener mitemclickListenere;

    ListProjectShowsAdapter(List<ResultsItem> itemList) { this.itemList = itemList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_project_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ResultsItem model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.itemTextTitle.setText(model.getName());

            if(model.getOverview().length()>50){
                genericViewHolder.itemTextOverview.setText(model.getOverview().substring(0,49)+" ... ");
            }else {
                genericViewHolder.itemTextOverview.setText(model.getOverview());
            }

            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w185"+model.getPosterPath()).into(genericViewHolder.imagePoster);
        }
    }

    @Override
    public int getItemCount() { return itemList.size(); }

    void SetOnItemClickListener(final OnItemClickListener mitemclickListenere) {
        this.mitemclickListenere = mitemclickListenere;
    }

    private ResultsItem getItem(int position) { return itemList.get(position); }

    public interface OnItemClickListener {
        void onItemClick(View view, ResultsItem model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePoster;
        private TextView itemTextTitle;
        private TextView itemTextOverview;

        ViewHolder(final View itemView) {
            super(itemView);

            this.imagePoster = itemView.findViewById(R.id.imageposter);
            this.itemTextTitle = itemView.findViewById(R.id.item_text_title);
            this.itemTextOverview = itemView.findViewById(R.id.item_text_overview);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mitemclickListenere.onItemClick(itemView, itemList.get(getAdapterPosition()));
                }
            });
        }
    }
}
