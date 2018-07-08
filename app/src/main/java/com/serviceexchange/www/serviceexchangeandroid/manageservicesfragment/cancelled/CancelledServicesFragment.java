package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.cancelled;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.pendingtransactionsdetailactivity.PendingTransactionActivity;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CancelledServicesFragment extends Fragment implements CancelledMVP.View {

    @Inject
    CancelledMVP.Presenter presenter;
    @Inject
    Context context;

    RecyclerView recyclerView;
    CancelledServicesAdapter adapter;


    public CancelledServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancelled_services, container, false);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);

        recyclerView = view.findViewById(R.id.cancelledServicesRecyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        adapter = new CancelledServicesAdapter(null, context, presenter);
        recyclerView.setAdapter(adapter);
        Log.i("pending", "should request");
        presenter.loadPendingServices(0);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.reRegister();
        presenter.loadPendingServices(0);
    }

    @Override
    public void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    public void setList(List<TransactionDto> object) {
        adapter.refreshList(object);
    }

    @Override
    public void showPendingTransactionActivity(TransactionDto trans) {
        Intent intent = new Intent(getContext(), PendingTransactionActivity.class);
        intent.putExtra("trans", trans);
        startActivity(intent);
    }
}
