package com.serviceexchange.www.serviceexchangeandroid.application.dagger;

import com.serviceexchange.www.serviceexchangeandroid.dagger.MainModule;
import com.serviceexchange.www.serviceexchangeandroid.earning.EarningFragment;
import com.serviceexchange.www.serviceexchangeandroid.firebasenotifications.MyFirebaseInstanceIdService;
import com.serviceexchange.www.serviceexchangeandroid.firebasenotifications.MyFirebaseMessagingService;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.StatisticFragment;

import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.GuestHome;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment.ServiceDetailFragment;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.ServicesForCategoryActivity;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments.OfferedServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments.RequestedServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity.SubCategoriesActivity;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.viewallcategoriesavtivity.ViewAllCategoriesActivity;
import com.serviceexchange.www.serviceexchangeandroid.inbox.InboxFragment;
import com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.InboxDetailActivity;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginActivity;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.dagger.LoginModule;

import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.active.ActiveServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.ActiveTransactionDetailActivity;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.cancelled.CancelledServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.completed.CompletedServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.pendingtransactionsdetailactivity.PendingTransactionActivity;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceFragment;

import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepDone.ServiceStepDoneFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepTwo.ServiceStepTwoFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepThree.ServiceStepThreeFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.displayService.DisplayServiceFragment;

import com.serviceexchange.www.serviceexchangeandroid.myrequests.MyRequestsFragment;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.addrquests.AddRequestsActivity;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice.AllRequestOnServiceActivity;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice.AllRequestOnServiceFragment;
import com.serviceexchange.www.serviceexchangeandroid.navigationdrawer.NavigationDrawerActivity;
import com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices.OfferReceivedFragment;
import com.serviceexchange.www.serviceexchangeandroid.profile.ProfileActivity;
import com.serviceexchange.www.serviceexchangeandroid.profile.ProfileFragment;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.RequestsFragment;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment.OfferDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, LoginModule.class, MainModule.class})
public interface AppComponent {
    void inject(LoginActivity activity);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Omar writes here ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    void inject(GuestHome fragment);

    void inject(ActiveServicesFragment fragment);

    void inject(CompletedServicesFragment fragment);

    void inject(CancelledServicesFragment fragment);

    void inject(RequestsFragment fragment);

    void inject(ServicesForCategoryActivity servicesForCategoryActivity);

    void inject(SubCategoriesActivity subCategoriesActivity);

    void inject(RequestedServicesFragment requestedServicesFragment);

    void inject(OfferedServicesFragment offeredServicesFragment);

    void inject(ViewAllCategoriesActivity viewAllCategoriesActivity);

    void inject(ServiceDetailFragment serviceDetailFragment);

    void inject(ActiveTransactionDetailActivity activeTransactionDetailActivity);

    void inject(OfferDetailActivity offerDetailActivity);

    void inject(NavigationDrawerActivity navigationDrawerActivity);

    void inject(PendingTransactionActivity pendingTransactionActivity);

    void inject(MyFirebaseInstanceIdService myFirebaseInstanceIdService);

    void inject(MyFirebaseMessagingService myFirebaseMessagingService);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Nehal writes here ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    void inject(StatisticFragment statisticFragment);

    void inject(EarningFragment earningFragment);

    void inject(MyServiceFragment myServiceFragment);

    void inject(AddServiceFragment addServiceFragment);

    void inject(DisplayServiceFragment displayServiceFragment);

    void inject(ProfileFragment profileFragment);

    void inject(ProfileActivity profileActivity);

    void inject(ServiceStepTwoFragment serviceStepTwoFragment);

    void inject(ServiceStepThreeFragment serviceStepThreeFragment);

    void inject(ServiceStepDoneFragment serviceStepDoneFragment);

    void inject(OfferReceivedFragment offerReceivedFragment  );

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Eslam writes here ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    void inject(InboxFragment inboxFragment);

    void inject(InboxDetailActivity inboxDetailActivity);

    void inject(MyRequestsFragment myRequestsFragment);

    void inject(AddRequestsActivity addRequestsActivity);

    void inject(AllRequestOnServiceFragment allRequestOnServiceFragment);

    void inject(AllRequestOnServiceActivity allRequestOnServiceActivity);

}
