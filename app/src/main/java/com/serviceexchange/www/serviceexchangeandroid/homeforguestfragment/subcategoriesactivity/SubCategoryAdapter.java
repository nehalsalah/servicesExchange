package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;

import java.util.List;

class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    List<SkillDTO> categoriesList;
    Context context;
    SubCategoryMVP.Presenter presenter;

    public SubCategoryAdapter(Context context, SubCategoryMVP.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_sub_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cardOnClick(categoriesList.get(position).getId());
            }
        });
        holder.name.setText(categoriesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (categoriesList != null)
            return categoriesList.size();
        return 0;
    }

    public void refreshList(List<SkillDTO> object) {
        categoriesList = object;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.subCatNameTextView);
        }
    }
}
