package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.completed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.squareup.picasso.Picasso;

import java.util.List;

class CompletedServicesAdapter extends RecyclerView.Adapter<CompletedServicesAdapter.ViewHolder>{

    List<TransactionDto> serviceList;
    Context context;
    CompletedMVP.Presenter presenter;

    public CompletedServicesAdapter(List<TransactionDto> serviceList, Context context, CompletedMVP.Presenter presenter) {
        this.serviceList = serviceList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_active_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TransactionDto trans = serviceList.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Picasso.with(context).load(trans.getOtherUserImage()).placeholder(R.drawable.com_facebook_button_icon_blue)
                .into(holder.userImage);
        holder.username.setText(trans.getOtherUserName());
        holder.serviceName.setText(trans.getServiceName());
        holder.price.setText(""+trans.getPrice() + " Points");
    }

    @Override
    public int getItemCount() {
        if (serviceList != null)
            return serviceList.size();
        return 0;
    }

    public void refreshList(List<TransactionDto> object) {
        serviceList = object;
        Log.i("completed", "should refresh");
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView userImage;
        TextView username;
        TextView serviceName;
        TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            userImage = itemView.findViewById(R.id.transactionUserImage);
            username = itemView.findViewById(R.id.transactionUsername);
            serviceName = itemView.findViewById(R.id.transactionServiceName);
            price = itemView.findViewById(R.id.transactionPrice);
        }
    }
}
