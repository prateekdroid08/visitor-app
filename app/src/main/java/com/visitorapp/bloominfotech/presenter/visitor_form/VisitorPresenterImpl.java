package com.visitorapp.bloominfotech.presenter.visitor_form;

import android.content.Context;

import com.visitorapp.bloominfotech.models.PostResponse;
import com.visitorapp.bloominfotech.models.form_response.ResponseVisitorForm;

import java.util.HashMap;

/**
 * Created by hp on 10/24/2016.
 */
public class VisitorPresenterImpl implements VisitorPresenter, OnVisitorListener {

    Context context;
    VisitorView visitorView;
    VisitorInteractor visitorInteractor;

    public VisitorPresenterImpl(Context context, VisitorView visitorView) {
        this.context = context;
        this.visitorView = visitorView;
        visitorInteractor = new VisitorInteractorImpl();
    }

    @Override
    public void submitVisitorForm(String EmailAddress,String FirstName,String LastName,String UserMembers,  String CompanyName,  String Phone, String PurposeName,  String MeetingID,  String isPostBack, String CarNumber) {
        visitorView.showProgress();
        visitorInteractor.submitVisitorForm(EmailAddress,FirstName,LastName,UserMembers,CompanyName, Phone,PurposeName,MeetingID,isPostBack,CarNumber,this);
    }

    @Override
    public void onSuccess(PostResponse postResponse) {
        visitorView.hideProgress();
        visitorView.onSuccess(postResponse);
    }

    @Override
    public void onError(String message) {
        visitorView.hideProgress();
        visitorView.onError(message);
    }
}
