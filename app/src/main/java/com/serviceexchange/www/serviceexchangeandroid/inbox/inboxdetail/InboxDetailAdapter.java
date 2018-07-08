package com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class InboxDetailAdapter extends RecyclerView.Adapter {

    public static final String TAG = "InboxDetailAdapter";
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private InboxDetailMVP.Presenter presenter;
    private List<MessageGeneralDto> messageGeneralDtoList;
    private static Context context;
    private static TransactionChatDto transactionChatDto;
    private SharedPreferencesModel sharedPreferencesModel;

    public InboxDetailAdapter(InboxDetailMVP.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        sharedPreferencesModel = SharedPreferencesModel.getInstance(context);
    }

    @Override
    public int getItemViewType(int position) {
        MessageGeneralDto messageGeneralDto = messageGeneralDtoList.get(position);

        if (messageGeneralDto.getReceiverId() == sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId()) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageGeneralDto messageGeneralDto = messageGeneralDtoList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(messageGeneralDto);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(messageGeneralDto);
        }
    }

    @Override
    public int getItemCount() {
        if (messageGeneralDtoList != null) {
            return messageGeneralDtoList.size();
        }
        return 0;
    }

    private static class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView timeText;
        TextView nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
            nameText = itemView.findViewById(R.id.text_message_name);
            profileImage = itemView.findViewById(R.id.image_message_profile);
        }

        void bind(MessageGeneralDto messageGeneralDto) {
            messageText.setText(messageGeneralDto.getText());
            timeText.setText(new SimpleDateFormat("MM/dd").format(messageGeneralDto.getDate()));
            nameText.setText(transactionChatDto.getUserName());
            Picasso.with(context).load(transactionChatDto.getUserImage()).into(profileImage);
        }
    }

    private static class SentMessageHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView timeText;

        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
        }

        void bind(MessageGeneralDto messageGeneralDto) {
            messageText.setText(messageGeneralDto.getText());
            timeText.setText(new SimpleDateFormat("MM/dd").format(messageGeneralDto.getDate()));
        }
    }

    public void refreshList(List<MessageGeneralDto> object) {
        messageGeneralDtoList = object;
        notifyDataSetChanged();
    }

    public void appendMessage(MessageGeneralDto object) {
        messageGeneralDtoList.add(object);
        notifyDataSetChanged();
    }

    public void setTransactionChatDto(TransactionChatDto transactionChatDto) {
        InboxDetailAdapter.transactionChatDto = transactionChatDto;
    }
}
