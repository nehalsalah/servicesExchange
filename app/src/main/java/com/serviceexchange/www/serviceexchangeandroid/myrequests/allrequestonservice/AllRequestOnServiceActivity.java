package com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.TransactionEslam;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import javax.inject.Inject;

public class AllRequestOnServiceActivity extends AppCompatActivity implements AllRequestOnServiceMVP.View {

    @Inject
    AllRequestOnServiceMVP.Presenter presenter;

    @Inject
    Context context;

    ImageView toolbar_back_allonrequest;

    private RecyclerView recyclerView;
    private AllRequestOnServiceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_allrequestonservice);

        ((App) this.getApplication()).getComponent().inject(this);

        presenter.setView(this);
        recyclerView = findViewById(R.id.recyclerview_allonrequest);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AllRequestOnServiceAdapter(presenter, context);

        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
//        int serviceID = getArguments().getInt("serviceID");
        int serviceID = intent.getIntExtra("serviceID", 0);
        presenter.loadAllRequstOnService(serviceID);

        toolbar_back_allonrequest = findViewById(R.id.toolbar_back_allonrequest);

        toolbar_back_allonrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

    @Override
    public void acceptOffer(TransactionDto transactionDto) {
        if (transactionDto != null)
            Toast.makeText(context, "Accept Offer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTransactionList(List<TransactionEslam> transactionEslams) {
        adapter.refreshList(transactionEslams);
    }
}
