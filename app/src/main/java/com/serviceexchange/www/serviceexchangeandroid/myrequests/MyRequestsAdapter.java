package com.serviceexchange.www.serviceexchangeandroid.myrequests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice.AllRequestOnServiceActivity;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice.AllRequestOnServiceFragment;
import com.serviceexchange.www.serviceexchangeandroid.navigationdrawer.NavigationDrawerActivity;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyRequestsAdapter extends RecyclerView.Adapter<MyRequestsAdapter.ViewHolder> {

    private MyRequestsMVP.Presenter presenter;
    private List<ServiceDTO> serviceDTOList;
    private Context context;

    public MyRequestsAdapter(MyRequestsMVP.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public MyRequestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrequests_listitem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ServiceDTO serviceDTO = serviceDTOList.get(position);

        holder.myrequest_description.setText(serviceDTO.getDescription());
        holder.text_request_type.setText(serviceDTO.getType());
        holder.text_number_of_offers_for_request.setText(String.valueOf(serviceDTO.getNumberOfTransaction()));
        holder.text_request_date.setText(String.valueOf(serviceDTO.getExpectDur()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AllRequestOnServiceFragment allRequestOnServiceFragment = new AllRequestOnServiceFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("serviceID", serviceDTO.getId());
//                allRequestOnServiceFragment.setArguments(bundle);
//                AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                activity.getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content_frame, allRequestOnServiceFragment)
//                        .commit();

                Intent intent = new Intent(context, AllRequestOnServiceActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("serviceID", serviceDTO.getId());
                context.startActivity(intent);
            }
        });

    }

    public void refreshList(List<ServiceDTO> serviceDTOS) {
        serviceDTOList = serviceDTOS;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (serviceDTOList != null) {
            return serviceDTOList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView myrequest_description;
        TextView text_request_type;
        TextView text_number_of_offers_for_request;
        TextView text_request_date;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.myrequest_description = itemView.findViewById(R.id.myrequest_description);
            this.text_request_type = itemView.findViewById(R.id.text_request_type);
            this.text_number_of_offers_for_request = itemView.findViewById(R.id.text_number_of_offers_for_request);
            this.text_request_date = itemView.findViewById(R.id.text_request_date);
        }
    }
}
