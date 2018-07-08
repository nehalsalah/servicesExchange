package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<SkillDTO> categoriesList;
    private Context context;
    private GuestHomeMPV.Presenter presenter;

    public CategoryAdapter(List<SkillDTO> categoriesList, Context context, GuestHomeMPV.Presenter presenter) {
        this.categoriesList = categoriesList;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        SkillDTO skill = categoriesList.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.categoryCardOnClick(skill.getId(), skill.getName());
            }
        });
        holder.categoryNameTextView.setText(skill.getName());
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
        TextView categoryNameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            categoryNameTextView = itemView.findViewById(R.id.categoryNameTextView);
        }
    }
}
