package com.visitorapp.bloominfotech.presenter.admin_detail;

/**
 * Created by hp on 10/24/2016.
 */
public interface AdminDetailInteractor {

    void getadminDetailsAPI(String sort,String srchDate,String srchDateTo,String CompanyID,String MeetingID,String page,AdminDetailListener adminDetailListener);

}
