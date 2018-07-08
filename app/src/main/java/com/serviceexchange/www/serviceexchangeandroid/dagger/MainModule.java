package com.serviceexchange.www.serviceexchangeandroid.dagger;

import android.content.Context;

import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseInt;
import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseManager;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsimplementations.EslamApiModelImpl;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsimplementations.NehalApiModelImpl;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsimplementations.OmarApiModelImpl;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.EslamApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.ApiClient;
import com.serviceexchange.www.serviceexchangeandroid.earning.EarningMVP;
import com.serviceexchange.www.serviceexchangeandroid.earning.EarningPresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.StatisticMVP;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.StatisticPresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.GuestHomeMPV;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.GuestHomePresenter;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment.ServiceDetailMVP;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment.ServiceDetailPresenter;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments.ServicesPerCategoryMVP;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments.ServicesPerCategoryPresenter;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity.SubCategoriesPresenter;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity.SubCategoryMVP;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.viewallcategoriesavtivity.ViewAllCategoriesPresenter;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.viewallcategoriesavtivity.ViewAllMVP;
import com.serviceexchange.www.serviceexchangeandroid.inbox.InboxMVP;
import com.serviceexchange.www.serviceexchangeandroid.inbox.InboxPresenter;
import com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.InboxDetailMVP;
import com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.InboxDetailPresenter;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.active.ActiveMVP;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.active.ActivePresenter;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.TimelineMVP;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.TimelinePresenter;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.cancelled.CancelledMVP;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.cancelled.CancelledPresenter;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.completed.CompletedMVP;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.completed.CompletedPresenter;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.pendingtransactionsdetailactivity.ReviewMVP;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.pendingtransactionsdetailactivity.ReviewPresenter;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServicesPresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServicePresenterImp;

import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepDone.ServiceStepDoneMVP;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepDone.ServiceStepDonePresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepThree.ServiceStepThreeMVP;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepThree.ServiceStepThreePresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepTwo.ServiceStepTwoMVP;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepTwo.ServiceStepTwoPresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.myService.displayService.DisplayMyServicePresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.myService.displayService.DisplayServiceMVP;

import com.serviceexchange.www.serviceexchangeandroid.myrequests.MyRequestsMVP;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.MyRequestsPresenter;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.addrquests.AddRequestsPresenter;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.addrquests.AddRquestsMVP;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice.AllRequestOnServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice.AllRequestOnServicePresenter;
import com.serviceexchange.www.serviceexchangeandroid.navigationdrawer.NavigationDrawerMVP;
import com.serviceexchange.www.serviceexchangeandroid.navigationdrawer.NavigationDrawerPresenter;
import com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices.OfferReceivedMVP;
import com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices.OfferReceivedPresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.profile.ProfileMVP;
import com.serviceexchange.www.serviceexchangeandroid.profile.ProfileModelImp;
import com.serviceexchange.www.serviceexchangeandroid.profile.ProfilePresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.RequestsMVP;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.RequestsPresenter;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment.OfferDetailMVP;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment.OfferDetailPresenter;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.filedownloaderUtil.FileDownloaderUtil;
import com.serviceexchange.www.serviceexchangeandroid.utils.filedownloaderUtil.FileDownloaderUtilInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.notifier.Notifier;
import com.serviceexchange.www.serviceexchangeandroid.utils.notifier.NotifierInt;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Omar writes here ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    public GuestHomeMPV.Presenter provideGuestHomePresenter(OmarApiModelInt apiModel) {
        return new GuestHomePresenter(apiModel);
    }

    @Provides
    public Retrofit provideRetrofit(Context context) {
        return ApiClient.getClient(context);
    }

    @Provides
    public ActiveMVP.Presenter provideActivePresenter(OmarApiModelInt apiModelInt
            , SharedPreferencesModelInt sharedPreferencesModelInt) {
        return new ActivePresenter(apiModelInt, sharedPreferencesModelInt);
    }

    @Provides
    public CancelledMVP.Presenter provideCancelledPresenter(OmarApiModelInt apiModel
            , SharedPreferencesModelInt sharedPreferencesModelInt) {
        return new CancelledPresenter(apiModel, sharedPreferencesModelInt);
    }

    @Provides
    public CompletedMVP.Presenter provideCompletedPresenter(OmarApiModelInt apiMdodel
            , SharedPreferencesModelInt sharedPreferencesModelInt) {
        return new CompletedPresenter(apiMdodel, sharedPreferencesModelInt);
    }

    @Provides
    public RequestsMVP.Presenter provideRequestsPresenter(OmarApiModelInt apiModel) {
        return new RequestsPresenter(apiModel);
    }

    @Provides
    public OmarApiModelInt provideOmarApiModel(Retrofit retrofit) {
        return new OmarApiModelImpl(retrofit);
    }

    @Singleton
    @Provides
    public SharedPreferencesModelInt provideSharedPreferencesModel(Context context) {
        return SharedPreferencesModel.getInstance(context);
    }

    @Provides
    public ServicesPerCategoryMVP.Presenter provideServicesForCategoryPresenter(OmarApiModelInt apiModelInt) {
        return new ServicesPerCategoryPresenter(apiModelInt);
    }

    @Provides
    public SubCategoryMVP.Presenter provideSubCategoriesPresenter(OmarApiModelInt apiModel) {
        return new SubCategoriesPresenter(apiModel);
    }

    @Provides
    public ViewAllMVP.Presenter provideViewAllPresenter(OmarApiModelInt apiModel) {
        return new ViewAllCategoriesPresenter(apiModel);
    }

    @Provides
    public ServiceDetailMVP.Presenter provideServiceDetailPresenter(OmarApiModelInt apiModel) {
        return new ServiceDetailPresenter(apiModel);
    }

    @Provides
    public FirebaseInt provideFirebaseManager() {
        return new FirebaseManager();
    }

    @Provides
    public OfferDetailMVP.Presenter provideOfferDetailPresenter(OmarApiModelInt apiModelInt
            , SharedPreferencesModelInt sharedPreferencesModelInt) {
        return new OfferDetailPresenter(apiModelInt, sharedPreferencesModelInt);
    }

    @Provides
    public TimelineMVP.Presenter provideTimelinePresenter(FirebaseInt firebaseInt, OmarApiModelInt apiModelInt) {
        return new TimelinePresenter(firebaseInt, apiModelInt);
    }

    @Provides
    public NavigationDrawerMVP.Presenter provideNavigationPresenter(SharedPreferencesModelInt model) {
        return new NavigationDrawerPresenter(model);
    }

    @Provides
    public ReviewMVP.Presenter provideReveiewPresenter(OmarApiModelInt apiModel, SharedPreferencesModelInt preferencesModel
            , FileDownloaderUtilInt fileDownloader) {
        return new ReviewPresenter(apiModel, fileDownloader, preferencesModel);
    }

    @Provides
    public FileDownloaderUtilInt provideFileDownloader(Context context) {
        return new FileDownloaderUtil(context);
    }

    @Provides
    public NotifierInt provideNotifier(Context context, SharedPreferencesModelInt preferencesModel) {
        return new Notifier(context, preferencesModel);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Nehal writes here ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    StatisticMVP.Presenter provideStatisticPresenter(NehalApiModelInt apiModel
            , SharedPreferencesModelInt sharedPreferencesModel) {
        return new StatisticPresenterImp(apiModel, sharedPreferencesModel);
    }

    @Provides
    EarningMVP.Presenter provideEarningPresenter(NehalApiModelInt apiModel
            , SharedPreferencesModelInt sharedPreferencesModel) {
        return new EarningPresenterImp(apiModel, sharedPreferencesModel);
    }

    @Provides
    MyServiceMVP.Presenter provideMyServicesPresenter(NehalApiModelInt apiModel
            , SharedPreferencesModelInt sharedPreferencesModel) {
        return new MyServicesPresenterImp(apiModel, sharedPreferencesModel);
    }

    @Provides
    AddServiceMVP.Presenter provideAddServicePresenter(OmarApiModelInt apiModel
            , SharedPreferencesModelInt sharedPreferencesModel) {
        return new AddServicePresenterImp(apiModel, sharedPreferencesModel);
    }

    @Provides
    DisplayServiceMVP.Presenter provideDisplayServicePresenter() {
        return new DisplayMyServicePresenterImp();
    }

    @Provides
    ServiceStepTwoMVP.Presenter provideServiceStepTwoPresenter() {
        return new ServiceStepTwoPresenterImp();
    }

    @Provides
    ServiceStepThreeMVP.Presenter provideServiceStepThreePresenter(FirebaseInt firebaseInt) {
        return new ServiceStepThreePresenterImp(firebaseInt);
    }

    @Provides
    ServiceStepDoneMVP.Presenter provideServiceStepDonePresenter(NehalApiModelInt apiModel
            , Context context) {
        return new ServiceStepDonePresenterImp(apiModel, context);
    }

    @Provides
    ProfileMVP.Presenter provideProfilePresenter(FirebaseInt firebaseInt,NehalApiModelInt apiModel,ProfileMVP.Model profileModel) {
        return new ProfilePresenterImp(firebaseInt,apiModel,profileModel);
    }


    @Provides
    ProfileMVP.Model provideProfileModel() {
        return new ProfileModelImp();
    }

    @Provides
    public NehalApiModelInt provideNehalApiModel(Retrofit retrofit) {
        return new NehalApiModelImpl(retrofit);
    }

    @Provides
    OfferReceivedMVP.Presenter provideOfferReceivedPresenter(OmarApiModelInt apiModel, SharedPreferencesModelInt sharedPreferencesModel) {
        return new OfferReceivedPresenterImp(apiModel,sharedPreferencesModel);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Eslam writes here ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Provides
    public EslamApiModelInt provideEslamApiModel(Retrofit retrofit) {
        return new EslamApiModelImpl(retrofit);
    }

    @Provides
    InboxMVP.Presenter provideInboxPresenter(EslamApiModelInt apiModelInt) {
        return new InboxPresenter(apiModelInt);
    }

    @Provides
    InboxDetailMVP.Presenter provideInboxDetailPresenter(EslamApiModelInt apiModelInt, SharedPreferencesModelInt preferencesModel) {
        return new InboxDetailPresenter(apiModelInt, preferencesModel);
    }

    @Provides
    MyRequestsMVP.Presenter provideMyRequestsPresenter(EslamApiModelInt apiModelInt) {
        return new MyRequestsPresenter(apiModelInt);
    }

    @Provides
    AddRquestsMVP.Presenter provideAddRequestsPresenter(EslamApiModelInt apiModelInt) {
        return new AddRequestsPresenter(apiModelInt);
    }

    @Provides
    AllRequestOnServiceMVP.Presenter provideAllRequestOnServicePresenter(EslamApiModelInt apiModelInt) {
        return new AllRequestOnServicePresenter(apiModelInt);
    }

}
