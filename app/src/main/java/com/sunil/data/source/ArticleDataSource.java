package com.sunil.data.source;

import android.support.annotation.NonNull;

import com.sunil.data.model.Article;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sunil on 4/7/2017.
 */

public interface ArticleDataSource {

    Flowable<List<Article>> getArticles();

    void saveArticle(@NonNull Article article);

    void deleteArticle(@NonNull String id);
}
