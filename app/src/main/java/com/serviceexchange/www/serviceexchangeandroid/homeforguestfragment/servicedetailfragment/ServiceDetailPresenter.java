package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigInteger;
import java.util.List;

public class ServiceDetailPresenter implements ServiceDetailMVP.Presenter {

    private OmarApiModelInt apiModel;
    private ServiceDetailMVP.View view;


    public ServiceDetailPresenter(OmarApiModelInt apiModel) {
        this.apiModel = apiModel;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(ServiceDetailMVP.View view) {
        this.view = view;
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
    public void reviewsListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.REVIEWS_FOR_SERVICES)
            view.setReviewsList((List<ReviewDTO>) event.getObject());
        else if (event.getEventType() == CustomEventType.STARTED_TRANS)
            view.orderSuccess();
        else if (event.getEventType() == CustomEventType.FAILED_REQUEST)
            view.orderFailed();
    }

    @Override
    public void sendOrder(ServiceDTO service, UserDTO user) {
        TransactionDto trans = new TransactionDto();
        trans.setServiceName(service.getName());
        trans.setServiceId(service.getId());
        trans.setPrice(service.getPrice());
        trans.setDuration(BigInteger.valueOf(service.getDuration()));
        trans.setsByUser(user.getId());
        trans.setServiceDescription(service.getDescription());
        apiModel.sendOffer(trans);
    }
}
