package com.sunil.mvprxjava.adapter;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunil.data.model.Article;
import com.sunil.mvprxjavaapp.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 4/7/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Article> itemModels;
    private Context context;
    private onItemClickListener listener;

    public ArticleAdapter(Context context, List<Article> articles, Fragment fragment) {
        this.itemModels = articles;
        this.context = context;
        this.listener = (onItemClickListener) fragment;
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_item_layout, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Article model = itemModels.get(position);
        initializeViews(model, holder, position);
    }


    private void initializeViews(final Article model, final RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder)holder).title.setText(model.getTittle());
        ((ItemViewHolder)holder).description.setText(model.getDescription());
        ((ItemViewHolder)holder).author.setText(model.getAuthor());
        ((ItemViewHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemModels.remove(model);
                notifyItemRemoved(position);
                listener.itemRemoveClick(model.getId());
            }
        });
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.delete)
        ImageView delete;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onItemClickListener{
        public void itemRemoveClick(long position);
    }
}
