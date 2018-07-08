package com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;

import javax.inject.Inject;

public class OfferDetailActivity extends AppCompatActivity implements OfferDetailMVP.View {

    private EditText points;
    private EditText duration;
    private EditText description;
    private Button sendOfferButton;
    private ServiceDTO service;

    @Inject
    OfferDetailMVP.Presenter presenter;
    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);
        ((App) getApplication()).getComponent().inject(this);
        presenter.setView(this);
        points = findViewById(R.id.offerPoints);
        duration = findViewById(R.id.offerDuration);
        description = findViewById(R.id.offerDecription);
        sendOfferButton = findViewById(R.id.offerSendButton);
        service = (ServiceDTO) getIntent().getSerializableExtra("service");
        sendOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendOfferButtonAction();
            }
        });
    }

    public ServiceDTO getService() {
        return service;
    }

    public String getPointsText() {
        return points.getText().toString();
    }

    @Override
    public String getDurationText() {
        return duration.getText().toString();
    }

    @Override
    public String getDescText() {
        return description.getText().toString();
    }

    @Override
    public void transStartedSuccess() {
        Log.i("callback", "should finish");
        finish();
    }

    @Override
    public void cantSendOffer() {
        Toast.makeText(context, "Can't Offer: Request is made by you!", Toast.LENGTH_SHORT).show();
    }
}
