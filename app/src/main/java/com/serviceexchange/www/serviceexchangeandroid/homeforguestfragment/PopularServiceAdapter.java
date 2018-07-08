package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;

import java.util.List;

class PopularServiceAdapter extends RecyclerView.Adapter<PopularServiceAdapter.ViewHolder> {
    List<ServiceDTO> serviceList;
    Context context;
    GuestHomeMPV.Presenter presenter;

    public PopularServiceAdapter(List<ServiceDTO> serviceList, Context context, GuestHomeMPV.Presenter presenter) {
        this.serviceList = serviceList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pop_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
