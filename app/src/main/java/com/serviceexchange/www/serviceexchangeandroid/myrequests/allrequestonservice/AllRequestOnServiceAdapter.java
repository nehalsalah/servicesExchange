package com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.TransactionEslam;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AllRequestOnServiceAdapter extends RecyclerView.Adapter<AllRequestOnServiceAdapter.ViewHolder> {

    private static AllRequestOnServiceMVP.Presenter presenter;
    private List<TransactionEslam> transactionEslamList;
    private Context context;

    public AllRequestOnServiceAdapter(AllRequestOnServiceMVP.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allrequestonservice_listitem, parent, false);

        AllRequestOnServiceAdapter.ViewHolder viewHolder = new AllRequestOnServiceAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionEslam transactionEslam = transactionEslamList.get(position);

        holder.text_name_allonrequest.setText(transactionEslam.getUserInfo().getUserName());
        holder.text_date_allonrequest.setText(new SimpleDateFormat("MM/dd/yyyy").format((new Date(transactionEslam.getDate()))));
        holder.text_description_allonrequest.setText(transactionEslam.getDescrption());
        holder.text_description_number_allonrequest.setText(String.valueOf(transactionEslam.getNumberOfDays()));

        holder.button_acceptoffer_allonrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setId(transactionEslam.getServiceId());
                transactionDto.setPrice(transactionEslam.getPrice());
                transactionDto.setDuration(BigInteger.valueOf(transactionEslam.getDuration()));
                presenter.onAcceptOfferClicked(transactionDto);
            }
        });
    }

    public void refreshList(List<TransactionEslam> transactionEslams) {
        transactionEslamList = transactionEslams;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (transactionEslamList != null)
            return transactionEslamList.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView text_name_allonrequest;
        TextView text_date_allonrequest;
        TextView text_description_allonrequest;
        TextView text_description_number_allonrequest;
        Button button_acceptoffer_allonrequest;


        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.text_name_allonrequest = itemView.findViewById(R.id.text_name_allonrequest);
            this.text_date_allonrequest = itemView.findViewById(R.id.text_date_allonrequest);
            this.text_description_allonrequest = itemView.findViewById(R.id.text_description_allonrequest);
            this.text_description_number_allonrequest = itemView.findViewById(R.id.text_description_number_allonrequest);
            this.button_acceptoffer_allonrequest = itemView.findViewById(R.id.button_acceptoffer_allonrequest);
        }
    }
}
