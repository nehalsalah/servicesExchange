package com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.TransactionEslam;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import javax.inject.Inject;

public class AllRequestOnServiceFragment extends Fragment implements AllRequestOnServiceMVP.View {

    @Inject
    AllRequestOnServiceMVP.Presenter presenter;

    @Inject
    Context context;

    private RecyclerView recyclerView;
    private AllRequestOnServiceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_allrequestonservice, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);

        presenter.setView(this);
        recyclerView = fragmentView.findViewById(R.id.recyclerview_allonrequest);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AllRequestOnServiceAdapter(presenter, context);

        recyclerView.setAdapter(adapter);

        int serviceID = getArguments().getInt("serviceID");
        presenter.loadAllRequstOnService(serviceID);

        return fragmentView;
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
