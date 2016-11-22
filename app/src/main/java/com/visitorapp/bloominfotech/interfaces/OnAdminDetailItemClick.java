package com.visitorapp.bloominfotech.interfaces;

import com.visitorapp.bloominfotech.models.admin_detail.UserList;
import com.visitorapp.bloominfotech.models.companies.ResponseCompanies;

import java.util.ArrayList;

/**
 * Created by hp on 11/19/2016.
 */

public interface OnAdminDetailItemClick {

    void onAdminDetailItemClicked(ArrayList<UserList> lstEvt, int position);


}
