package com.sunil.mvprxjava.ui.addarticlle;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.common.base.Preconditions;
import com.sunil.data.model.Article;
import com.sunil.data.source.ArticleLocalDataSource;
import com.sunil.mvprxjavaapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sunil on 4/6/2017.
 */

public class AddArticleFragment extends Fragment implements ArticleContract.View {

    @BindView(R.id.articleName)
    EditText articleName;
    @BindView(R.id.articleDesc)
    EditText articleDesc;
    @BindView(R.id.addButton)
    Button addButton;

    private ArticleContract.Presenter mPresenter;

    public static AddArticleFragment newInstance() {
        return new AddArticleFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_article, container, false);
        ButterKnife.bind(this, root);
        mPresenter = new AddArticlePresenter(new ArticleLocalDataSource(), this);
        return root;
    }

    @Override
    public void setPresenter(@NonNull ArticleContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadOk(ArrayList<Article> articles) {

    }

    @Override
    public void onLoadError(String msg) {

    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    public void deleteArticle(@NonNull String id) {

    }

    @Override
    public void onAction() {

    }

    @Override
    public void onActionOk() {

    }

    @Override
    public void onActionError(String msg) {

    }

    @Override
    public void onActionFinish() {
        getActivity().finish();
    }

    @OnClick(R.id.addButton)
    public void addArticleClick(){
        String title = articleName.getText().toString().trim();
        String description = articleDesc.getText().toString().trim();
        Preconditions.checkNotNull(title);
        Preconditions.checkNotNull(description);
        Article article = new Article();
        article.setTittle(title);
        article.setDescription(description);
        article.setAuthor("Sunil");
        mPresenter.saveArticle(article);

    }
}
