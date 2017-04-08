package com.sunil.data.source;

import android.support.annotation.NonNull;

import com.sunil.data.model.Article;
import com.sunil.data.model.ArticleDao;
import com.sunil.data.source.db.GreenDaoDatabase;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import timber.log.Timber;

/**
 * Created by sunil on 4/7/2017.
 */

public class ArticleLocalDataSource implements ArticleDataSource{

    private final  static String TAG = ArticleLocalDataSource.class.getSimpleName();

    private ArticleDao getArticleDao() {
        Timber.d(TAG, "getArticleDao()", Thread.currentThread().getName(), Thread.currentThread().getId());
        return GreenDaoDatabase.getInstance().getDaoSession().getArticleDao();
    }

    @Override
    public Flowable<List<Article>> getArticles() {
        return Flowable.fromCallable(new Callable<List<Article>>() {
            @Override
            public List<Article> call() throws Exception {
                Timber.d(TAG, "getArticles()", Thread.currentThread().getName(), Thread.currentThread().getId());
                List<Article> list = getArticleDao().loadAll();
                Timber.d("getArticles: " + list.size());
                return list;
            }
        });
    }

    @Override
    public void saveArticle(@NonNull Article article) {
        Timber.d(TAG, "saveBook()", Thread.currentThread().getName(), Thread.currentThread().getId());
        getArticleDao().insertOrReplace(article);
    }

    @Override
    public void deleteArticle(@NonNull String id) {
        Timber.d(TAG, "deleteArticle()", Thread.currentThread().getName(), Thread.currentThread().getId());
        getArticleDao().deleteByKey(Long.valueOf(id));
    }
}
