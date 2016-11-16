package com.visitorapp.bloominfotech.presenter.visitor_form;

import java.util.HashMap;

/**
 * Created by hp on 10/24/2016.
 */
public interface VisitorInteractor {
    void submitVisitorForm(String EmailAddress, String FirstName, String LastName, String UserMembers, String CompanyName, String Phone, String PurposeName, String MeetingID, String isPostBack, String CarNumber, OnVisitorListener onVisitorListener);
}
