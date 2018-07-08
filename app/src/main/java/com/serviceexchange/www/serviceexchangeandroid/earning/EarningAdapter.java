package com.serviceexchange.www.serviceexchangeandroid.earning;

import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Earning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nehal
 */

public class EarningAdapter extends RecyclerView.Adapter<EarningAdapter.ViewHolder> {
    private List<Earning> earningData;
    EarningMVP.Presenter presenter;

    public EarningAdapter( EarningMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public EarningAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_earning, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarningAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.serviceName.setText(earningData.get(i).getName());
        viewHolder.points.setText(earningData.get(i).getPrice()+" points");
        viewHolder.date.setText(earningData.get(i).getEndDate()+"");
    }

    public void refreshList(List<Earning> object) {
        if (object == null || object.size()<=0) {
            presenter.emptyList();
        } else {
            earningData = object;
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        if (earningData != null)
            return earningData.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView serviceName, points, date;
        public ViewHolder(View view) {
            super(view);
            serviceName = view.findViewById(R.id.serviceName);
            points = view.findViewById(R.id.points);
            date = view.findViewById(R.id.date);
        }
    }

}