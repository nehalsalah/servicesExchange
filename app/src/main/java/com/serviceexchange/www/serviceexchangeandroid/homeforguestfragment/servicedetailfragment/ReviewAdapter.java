package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<ReviewDTO> reviewsList;
    private Context context;
    private ServiceDetailMVP.Presenter presenter;

    public ReviewAdapter(Context context, ServiceDetailMVP.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (reviewsList != null)
            return reviewsList.size();
        return 0;
    }

    public void refreshList(List<ReviewDTO> revList) {
        reviewsList = revList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
