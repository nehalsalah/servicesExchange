package com.serviceexchange.www.serviceexchangeandroid.inbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.InboxDetailActivity;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;

import java.util.List;

import javax.inject.Inject;

public class InboxFragment extends Fragment implements InboxMVP.View {

    @Inject
    InboxMVP.Presenter presenter;

    @Inject
    Context context;

    private RecyclerView recyclerView;
    private InboxAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private SharedPreferencesModel sharedPreferencesModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_inbox, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);

        presenter.setView(this);
        recyclerView = fragmentView.findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new InboxAdapter(presenter, context);

        recyclerView.setAdapter(adapter);

        sharedPreferencesModel = SharedPreferencesModel.getInstance(context);

        presenter.loadAllUserTransactionChats(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId(), 0);

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
    public void goToInboxDetailActivity(TransactionChatDto transactionChatDto) {
        Intent inboxDetailActivityIntent = new Intent(getContext(), InboxDetailActivity.class);
        inboxDetailActivityIntent.putExtra("transactionChatDto", transactionChatDto);
        startActivity(inboxDetailActivityIntent);
    }

    @Override
    public void colorActionButton() {

    }

    @Override
    public void unColorActionButton() {

    }

    @Override
    public void setMessageList(List<TransactionChatDto> transactionChatDtoList) {
        adapter.refreshList(transactionChatDtoList);
    }
}
