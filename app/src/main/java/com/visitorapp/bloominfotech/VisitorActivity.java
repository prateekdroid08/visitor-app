package com.visitorapp.bloominfotech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.visitorapp.bloominfotech.presenter.main_presenter.VisitorPresenter;
import com.visitorapp.bloominfotech.presenter.main_presenter.VisitorPresenterImpl;
import com.visitorapp.bloominfotech.presenter.main_presenter.VisitorView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VisitorActivity extends AppCompatActivity implements VisitorView{

    public VisitorPresenter visitorPresenter;

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  /*init butterknife*/
        ButterKnife.bind(this);

        visitorPresenter = new VisitorPresenterImpl(this, this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    visitorPresenter.oneStepBack();
                }
            });
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        visitorPresenter.oneStepBack();
    }

    @Override
    public void hideNavigationButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

    }

    @Override
    public void showNavigationButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void changeTitle(String title) {

    }
}