package com.sunil.mvprxjava.ui.showarticle;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.common.base.Preconditions;
import com.sunil.data.model.Article;
import com.sunil.data.source.ArticleLocalDataSource;
import com.sunil.mvprxjava.adapter.ArticleAdapter;
import com.sunil.mvprxjavaapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 4/7/2017.
 */

public class LoadArticleFragment extends Fragment implements LoadArticleContact.View, ArticleAdapter.onItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private LoadArticleContact.Presenter mPresenter;

    public static LoadArticleFragment newInstance() {
        return new LoadArticleFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // do things if you want to create only first time when activity created.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_load_article, container, false);
        ButterKnife.bind(this, root);
        mPresenter = new LoadArticlePresenter(new ArticleLocalDataSource(), this);
        if (mPresenter  != null){
            mPresenter.loadArticle(true);
        }

        return root;
    }


    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadOk(List<Article> articles) {
        Preconditions.checkNotNull(articles);
        // call adapter
        ArticleAdapter articleAdapter = new ArticleAdapter(getActivity(), articles, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(articleAdapter);

    }

    @Override
    public void onLoadError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        onLoadFinish();
    }

    @Override
    public void onLoadFinish() {
       // finish the dialog if using
        Toast.makeText(getActivity(), "Completed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void deleteArticle(@NonNull String id) {
        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_LONG).show();
    }


    @Override
    public void setPresenter(LoadArticleContact.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void itemRemoveClick(long id) {
        Preconditions.checkNotNull(id);
        mPresenter.deleteArticle(String.valueOf(id));
    }
}
