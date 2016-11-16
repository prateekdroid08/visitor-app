package com.visitorapp.bloominfotech.presenter.visitor_form;

import java.util.HashMap;

import retrofit2.http.Field;

/**
 * Created by hp on 10/24/2016.
 */
public interface VisitorPresenter {
    void submitVisitorForm(String EmailAddress, String FirstName, String LastName, String UserMembers, String CompanyName, String Phone, String PurposeName, String MeetingID, String isPostBack, String CarNumber);
}
