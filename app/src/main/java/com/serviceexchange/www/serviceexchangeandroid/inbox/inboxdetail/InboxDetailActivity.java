package com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;

import java.util.List;

import javax.inject.Inject;

public class InboxDetailActivity extends AppCompatActivity implements InboxDetailMVP.View {

    @Inject
    InboxDetailMVP.Presenter presenter;

    @Inject
    Context context;

    private RecyclerView recyclerView;
    private InboxDetailAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Toolbar toolbar;
    TextView toolbarTextView;
    ImageButton inbox_send;
    EditText edittext_chatbox;

    private SharedPreferencesModel sharedPreferencesModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_detail);

        toolbar = findViewById(R.id.inbox_detail_toolbar);
        toolbarTextView = findViewById(R.id.inbox_detail_toolbar_title);

        inbox_send = findViewById(R.id.inbox_send);
        edittext_chatbox = findViewById(R.id.edittext_chatbox);

        sharedPreferencesModel = SharedPreferencesModel.getInstance(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ((App) getApplication()).getComponent().inject(this);

        presenter.setView(this);

        recyclerView = findViewById(R.id.recyclerview_message_list);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new InboxDetailAdapter(presenter, context);

        TransactionChatDto transactionChatDto = (TransactionChatDto) getIntent().getSerializableExtra("transactionChatDto");

        recyclerView.setAdapter(adapter);

        presenter.loadAllTransactionMessages(transactionChatDto.getTransactionId(), 0);

        toolbarTextView.setText(transactionChatDto.getUserName());

        adapter.setTransactionChatDto(transactionChatDto);

        inbox_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageGeneralDto messageGeneralDto = new MessageGeneralDto();
                messageGeneralDto.setText(edittext_chatbox.getText().toString());
                messageGeneralDto.setReceiverId(transactionChatDto.getUserId());
                messageGeneralDto.setSenderId(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId());
                messageGeneralDto.setTransactionId(transactionChatDto.getTransactionId());
                presenter.sendTransactionMessages(messageGeneralDto);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
        presenter.setIsChatRunningFlag(true);
    }

    @Override
    protected void onStop() {
        presenter.setIsChatRunningFlag(false);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

    @Override
    public void setMessageList(List<MessageGeneralDto> messageGeneralDtoList) {
        adapter.refreshList(messageGeneralDtoList);
    }

    @Override
    public void addMessageToList(MessageGeneralDto object) {
        adapter.appendMessage(object);
    }

    @Override
    public void sendMessage(MessageGeneralDto messageGeneralDto) {
        presenter.onSendButtonClicked(messageGeneralDto);
    }
}
