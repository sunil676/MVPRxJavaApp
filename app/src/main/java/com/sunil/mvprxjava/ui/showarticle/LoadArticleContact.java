package com.sunil.mvprxjava.ui.showarticle;

import android.support.annotation.NonNull;

import com.sunil.data.model.Article;
import com.sunil.mvprxjava.base.BasePresenter;
import com.sunil.mvprxjava.base.BaseView;

import java.util.List;

/**
 * Created by sunil on 4/7/2017.
 */

public class LoadArticleContact {

    public interface Presenter extends BasePresenter {
        void deleteArticle(@NonNull String id);
        void loadArticle(boolean forceUpdate);
    }

    public interface View extends BaseView<LoadArticleContact.Presenter> {
        void onLoading();
        void onLoadOk(List<Article> articles);
        void onLoadError(String msg);
        void onLoadFinish();
        void deleteArticle(@NonNull String id);
    }

}
