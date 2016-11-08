package com.visitorapp.bloominfotech.presenter.visitor_form;

import com.visitorapp.bloominfotech.models.PostResponse;

/**
 * Created by hp on 10/24/2016.
 */
public interface VisitorView {
    void onSuccess(PostResponse postResponse);

    void onError(String message);

    void showProgress();

    void hideProgress();
}
