package com.sunil.mvprxjava.ui.addarticlle;

import android.support.annotation.NonNull;

import com.sunil.data.model.Article;
import com.sunil.mvprxjava.base.BasePresenter;
import com.sunil.mvprxjava.base.BaseView;

import java.util.ArrayList;

/**
 * Created by sunil on 4/7/2017.
 */

public class ArticleContract {

    public interface Presenter extends BasePresenter {

        void saveArticle(@NonNull Article article);
        void deleteArticle(@NonNull String id);
        void loadArticle(boolean forceUpdate);
    }

    public interface View extends BaseView<Presenter> {

        void onLoading();
        void onLoadOk(ArrayList<Article> articles);
        void onLoadError(String msg);
        void onLoadFinish();
        void deleteArticle(@NonNull String id);
        void onAction();
        void onActionOk();
        void onActionError(String msg);
        void onActionFinish();
    }
}
