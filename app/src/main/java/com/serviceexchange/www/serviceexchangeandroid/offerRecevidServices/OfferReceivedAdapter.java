package com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

public class OfferReceivedAdapter extends RecyclerView.Adapter<OfferReceivedAdapter.ViewHolder> {
    private List<TransactionDto> list;
    private OfferReceivedMVP.Presenter presenter;
    private Context context;

    public OfferReceivedAdapter(OfferReceivedMVP.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_offer_recevid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionDto trans = list.get(position);
        holder.clientName.setText(trans.getOtherUserName());
        holder.points.setText(trans.getPrice() + " Points");
//        holder.date.setText(trans.getsD().toString());
        holder.serviceDesc.setText(trans.getServiceDescription());
        holder.icon.setText(trans.getOtherUserName().substring(0, 1));
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.acceptOfferClicked(trans);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cancelOfferClicked(trans);
            }
        });
    }

    public void refreshList(List<TransactionDto> object) {
        list = object;
        Log.i("orders", "should refresh");
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView clientName, date, serviceDesc,points, icon;
        Button accept,cancel;
        public ViewHolder(View view) {
            super(view);
            clientName = view.findViewById(R.id.clientName);
            date = view.findViewById(R.id.date);
            serviceDesc = view.findViewById(R.id.serviceDesc);
            points = view.findViewById(R.id.points);
            accept = view.findViewById(R.id.accept);
            cancel = view.findViewById(R.id.cancel);
            icon = view.findViewById(R.id.iconName);
        }
    }

}