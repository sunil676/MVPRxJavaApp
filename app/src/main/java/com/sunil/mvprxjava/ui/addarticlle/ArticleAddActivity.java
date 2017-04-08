package com.sunil.mvprxjava.ui.addarticlle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sunil.mvprxjava.util.ActivityUtil;
import com.sunil.mvprxjavaapp.R;

/**
 * Created by sunil on 4/6/2017.
 */

public class ArticleAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        if (savedInstanceState == null){
            // create the fragment here
            ActivityUtil.addFragmentToActivity(getFragmentManager(), AddArticleFragment.newInstance(), R.id.frame_container, "AddArticleFragment");
        }

    }
}
