package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class ServicesForCategoryAdapter extends RecyclerView.Adapter<ServicesForCategoryAdapter.ViewHolder>{

    List<ServiceDTO> servicesList;
    ServicesPerCategoryMVP.Presenter presenter;
    Context context;

    public ServicesForCategoryAdapter(ServicesPerCategoryMVP.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_service_for_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceDTO service = servicesList.get(position);
        Picasso.with(context).load(service.getImage()).into(holder.serviceImage);
        holder.description.setText(service.getName());
        holder.price.setText(service.getPrice() + " Points");
        holder.rating.setText(""+service.getRating());
        holder.provider.setText(service.getUo().getUserName());
        holder.transaction.setText("("+service.getNumberOfTransaction()+")");
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.serviceCardOnClickAction(service);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (servicesList != null)
            return servicesList.size();
        return 0;
    }

    public void refreshList(List<ServiceDTO> object) {
        servicesList = object;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView serviceImage;
        TextView rating;
        TextView transaction;
        TextView description;
        TextView provider;
        TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            serviceImage = itemView.findViewById(R.id.serviceImage);
            rating = itemView.findViewById(R.id.ratingTextView);
            transaction = itemView.findViewById(R.id.transactionNumberTextView);
            description = itemView.findViewById(R.id.descTextView);
            provider = itemView.findViewById(R.id.providerName);
            price = itemView.findViewById(R.id.priceTextView);
        }
    }
}
