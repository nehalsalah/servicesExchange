package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.active;

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

class ActiveServicesAdapter extends RecyclerView.Adapter<ActiveServicesAdapter.ViewHolder> {

    List<TransactionDto> list;
    Context context;
    ActiveMVP.Presenter presenter;

    public ActiveServicesAdapter(List<TransactionDto> serviceList, Context context, ActiveMVP.Presenter presenter) {
        this.list = serviceList;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TransactionDto trans = list.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cardClickedAtPosition(trans);
            }
        });
        Picasso.with(context).load(trans.getOtherUserImage()).into(holder.userImage);
        holder.username.setText(trans.getOtherUserName());
        holder.serviceName.setText(trans.getServiceName());
        holder.price.setText(""+trans.getPrice() + " Points");
        Log.i("timeline", " in adapter "+trans.isServiceProvider());
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public void refreshList(List<TransactionDto> object) {
        list = object;
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
