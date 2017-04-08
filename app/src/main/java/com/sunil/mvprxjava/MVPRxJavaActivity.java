package com.sunil.mvprxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.sunil.mvprxjava.ui.addarticlle.ArticleAddActivity;
import com.sunil.mvprxjava.ui.showarticle.LoadArticleActivity;
import com.sunil.mvprxjavaapp.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sunil on 4/7/2017.
 */

public class MVPRxJavaActivity extends AppCompatActivity {

    @BindView(R.id.addArticle)
    Button addArticle;
    @BindView(R.id.showArticle)
    Button showArticle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.addArticle)
    public void addArticleClick(){
        startActivity(new Intent(MVPRxJavaActivity.this, ArticleAddActivity.class));
    }

    @OnClick(R.id.showArticle)
    public void showArticleClick(){
        startActivity(new Intent(MVPRxJavaActivity.this, LoadArticleActivity.class));
    }
}
