package com.visitorapp.bloominfotech.presenter.purpose_of_visit;

import android.content.Context;

import com.visitorapp.bloominfotech.models.purpose.PurposeAPIResponse;

/**
 * Created by prateekarora on 31/10/16.
 */
public class PurposePresenterImpl implements PurposePresenter, OnPurposeListener {

    Context context;
    PurposeView purposeView;
    PurposeInteractor purposeInteractor;

    public PurposePresenterImpl(Context context, PurposeView purposeView) {
        this.context = context;
        this.purposeView = purposeView;
        purposeInteractor = new PurposeInteractorImpl();
    }

    @Override
    public void getAllPurpose() {
        purposeView.showProgress();
        purposeInteractor.getAllPurpose(this);
    }

    @Override
    public void onSuccess(PurposeAPIResponse purposeAPIResponse) {
        purposeView.hideProgressView();
        purposeView.onSuccess(purposeAPIResponse);
    }

    @Override
    public void onError(String message) {
        purposeView.hideProgressView();
        purposeView.onError(message);
    }
}
