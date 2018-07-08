package com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfferReceivedFragment extends Fragment implements OfferReceivedMVP.View {

    View v ;
    private RecyclerView recyclerView;
    private  OfferReceivedAdapter adapter;
    @Inject
    OfferReceivedMVP.Presenter presenter;
    @Inject
    Context context;

    public OfferReceivedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_offer_recevid, container, false);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        return v ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();

    }
    private void initViews(){
        recyclerView = v.findViewById(R.id.recyclerView);
        presenter.loadOfferReceivedList();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new OfferReceivedAdapter(presenter, context);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setOfferReceivedList(List<TransactionDto> object) {
        adapter.refreshList(object);
    }

    @Override
    public void reload() {
        presenter.loadOfferReceivedList();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    public void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

}
