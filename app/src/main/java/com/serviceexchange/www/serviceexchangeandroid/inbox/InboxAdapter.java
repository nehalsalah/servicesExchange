package com.serviceexchange.www.serviceexchangeandroid.inbox;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    private InboxMVP.Presenter presenter;
    private List<TransactionChatDto> transactionChatDtoList;
    private List<MessageGeneralDto> messageGeneralDtoList;
    private Context context;

    public InboxAdapter(InboxMVP.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public InboxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_inbox_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(cardView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        TransactionChatDto transactionChatDto = transactionChatDtoList.get(position);
        messageGeneralDtoList = transactionChatDto.getTransactionChatMessagesList();
        MessageGeneralDto messageGeneralDto = messageGeneralDtoList.get(position);

        holder.inbox_user_name.setText(transactionChatDto.getUserName());
        holder.inbox_user_message.setText(messageGeneralDto.getText());
        holder.inbox_user_date.setText(new SimpleDateFormat("MM/dd").format(messageGeneralDto.getDate()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MyAdapter", "Position: " + position);
                presenter.onCardListItemClicked(transactionChatDto);
            }
        });
    }

    public void refreshList(List<TransactionChatDto> object) {
        transactionChatDtoList = object;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (transactionChatDtoList != null) {
            return transactionChatDtoList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView inbox_user_image;
        public TextView inbox_user_name;
        public TextView inbox_user_message;
        public TextView inbox_user_date;
        public ImageButton inbox_user_action;

        public ViewHolder(View v) {
            super(v);
            view = v;
            this.inbox_user_name = v.findViewById(R.id.inbox_user_name);
            this.inbox_user_image = v.findViewById(R.id.inbox_user_image);
            this.inbox_user_message = v.findViewById(R.id.inbox_user_message);
            this.inbox_user_date = v.findViewById(R.id.inbox_user_date);
            this.inbox_user_action = v.findViewById(R.id.inbox_user_action);
        }
    }
}
