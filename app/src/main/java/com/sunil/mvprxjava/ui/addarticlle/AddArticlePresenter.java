package com.sunil.mvprxjava.ui.addarticlle;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.sunil.data.model.Article;
import com.sunil.data.source.ArticleLocalDataSource;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sunil on 4/7/2017.
 */

public class AddArticlePresenter implements ArticleContract.Presenter {

    private static final String TAG = AddArticlePresenter.class.getSimpleName();
    @NonNull
    private final ArticleContract.View mArticleView;

    private CompositeDisposable mCompositeDisposable;

    private ArticleLocalDataSource mArticleLocalDataSource;


    public AddArticlePresenter(@NonNull ArticleLocalDataSource articleLocalDataSource, @NonNull ArticleContract.View articleView) {
        mArticleLocalDataSource = articleLocalDataSource;
        mArticleView = articleView;
        mArticleView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void saveArticle(@NonNull Article article) {
        Preconditions.checkNotNull(article);
        mArticleLocalDataSource.saveArticle(article);
        mArticleView.onActionFinish();
    }

    @Override
    public void deleteArticle(@NonNull String id) {
        Preconditions.checkNotNull(id);
        mArticleLocalDataSource.deleteArticle(id);
        mArticleView.onActionFinish();
    }

    @Override
    public void loadArticle(boolean forceUpdate) {

    }

    @Override
    public void subscribe() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        //initRxBus();
       // loadArticle(false);
    }

    @Override
    public void unSubscribe() {
        mArticleView.onLoadFinish();
        mCompositeDisposable.clear();
        mArticleView.onActionFinish();
    }

   /* private void initRxBus() {
        mCompositeDisposable.add(RxBus.getBus()
                .toObserverable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    if (o instanceof Events.ReloadEvent) {
                        loadArticle(((Events.ReloadEvent) o).mForceUpdate);
                    }
                }));
    }*/
}
