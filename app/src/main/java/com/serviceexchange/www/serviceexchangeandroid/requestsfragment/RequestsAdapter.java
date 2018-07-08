package com.serviceexchange.www.serviceexchangeandroid.requestsfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    List<ServiceDTO> serviceList;
    Context context;
    RequestsMVP.Presenter presenter;

    public RequestsAdapter(List<ServiceDTO> serviceList, Context context, RequestsMVP.Presenter presenter) {
        this.serviceList = serviceList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ServiceDTO service = serviceList.get(position);
        holder.offerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.offerButtonClickAction(service);
            }
        });
        holder.username.setText(service.getUo().getUserName());
        Picasso.with(context).load(service.getUo().getImage()).into(holder.userImage);
        holder.serviceDate.setText(service.getName());
        holder.serviceRequirement.setText(service.getDescription());
        holder.serviceNoOfOffers.setText(service.getNumberOfTransaction() + " Offers Sent");
        holder.serviceDuration.setText("Duration: " + service.getDuration() + " Days");
        holder.serviceBudget.setText("Budget: " + service.getPrice() + " Points");
    }

    @Override
    public int getItemCount() {
        if (serviceList != null)
            return serviceList.size();
        return 0;
    }

    public void refreshList(List<ServiceDTO> object) {
        serviceList = object;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        Button offerButton;
        ImageView userImage;
        TextView username;
        TextView serviceDate;
        TextView serviceRequirement;
        TextView serviceNoOfOffers;
        TextView serviceDuration;
        TextView serviceBudget;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            offerButton = itemView.findViewById(R.id.offerButton);
            userImage = itemView.findViewById(R.id.requestUserImage);
            username = itemView.findViewById(R.id.requestUsername);
            serviceDate = itemView.findViewById(R.id.requestPostDate);
            serviceRequirement = itemView.findViewById(R.id.requestRequirement);
            serviceNoOfOffers = itemView.findViewById(R.id.requestNoOfOffers);
            serviceDuration = itemView.findViewById(R.id.serviceDuartion);
            serviceBudget = itemView.findViewById(R.id.serviceBudget);
        }
    }
}
