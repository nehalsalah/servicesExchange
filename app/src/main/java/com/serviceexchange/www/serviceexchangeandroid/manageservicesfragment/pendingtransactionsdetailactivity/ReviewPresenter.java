package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.pendingtransactionsdetailactivity;

import android.content.Context;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;
import com.serviceexchange.www.serviceexchangeandroid.utils.filedownloaderUtil.FileDownloaderUtilInt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Date;

public class ReviewPresenter implements ReviewMVP.Presenter {

    private ReviewMVP.View view;
    private OmarApiModelInt apiModel;
    private FileDownloaderUtilInt fileDownloader;

    public ReviewPresenter(OmarApiModelInt apiModel, FileDownloaderUtilInt fileDownloader, SharedPreferencesModelInt preferencesModel) {
        this.apiModel = apiModel;
        this.fileDownloader = fileDownloader;
        this.preferencesModel = preferencesModel;
        user = preferencesModel.getCurrentUserFromSharedPreferences();
        EventBus.getDefault().register(this);
    }

    private SharedPreferencesModelInt preferencesModel;
    private UserDTO user;

    @Override
    public void setView(ReviewMVP.View view) {
        this.view = view;
    }

    @Override
    public void downloadButtonAction(String jopFile) {
        fileDownloader.downloadFile(jopFile);
    }

    @Override
    public void submitButtonAction() {
        if (checkInputValidity()) {
            TransactionDto trans = view.getTrans();
            ReviewDTO review = new ReviewDTO();
            review.setComment(view.getCommentString());
            review.setRating(Integer.parseInt(view.getRatingString()));
            review.setTransactionId(trans.getId());
            review.setReviewDate(new Date().getTime());
            review.setMadeBy(user.getId());
            apiModel.approveTransaction(trans, review);
        } else {
            view.showInvalidInputMessage();
        }
    }

    @Override
    public void reRegister() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    @Override
    public void onPostEvent(CustomEvent event) {
        if (event.getEventType() == CustomEventType.APPROVED_TRANS)
            view.submissionSuccess();
    }

    private boolean checkInputValidity() {
        try {
            Integer rating = Integer.parseInt(view.getRatingString());
            String comment = view.getCommentString();
            if (comment.length() == 0 || rating < 0 || rating > 5)
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
