package com.sunil.mvprxjava.ui.showarticle;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.sunil.data.model.Article;
import com.sunil.data.source.ArticleLocalDataSource;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by sunil on 4/7/2017.
 */

public class LoadArticlePresenter implements LoadArticleContact.Presenter{

    @NonNull
    private final LoadArticleContact.View mArticleView;

    private CompositeDisposable mCompositeDisposable;

    private ArticleLocalDataSource mArticleLocalDataSource;

    public LoadArticlePresenter(@NonNull ArticleLocalDataSource articleLocalDataSource, @NonNull LoadArticleContact.View articleView) {
        mArticleLocalDataSource = articleLocalDataSource;
        mArticleView = articleView;
        mArticleView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }


    @Override
    public void deleteArticle(@NonNull String id) {
         Preconditions.checkNotNull(id);
         mArticleLocalDataSource.deleteArticle(id);
         mArticleView.deleteArticle(id);
    }

    @Override
    public void loadArticle(boolean forceUpdate) {
        if (forceUpdate) {
            Flowable<List<Article>> listFlowable = mArticleLocalDataSource.getArticles();
            listFlowable.doOnSubscribe(new Consumer<Subscription>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull Subscription subscription) throws Exception {

                }
            }).subscribe(new Consumer<List<Article>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull List<Article> articles) throws Exception {
                    mArticleView.onLoadOk(articles);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    Preconditions.checkNotNull(throwable);
                    mArticleView.onLoadError(throwable.getMessage());
                }
            }, new Action() {
                @Override
                public void run() throws Exception {
                    mArticleView.onLoadFinish();
                }
            });

        }
    }

    @Override
    public void subscribe() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void unSubscribe() {
        mArticleView.onLoadFinish();
        mCompositeDisposable.clear();

    }
}
