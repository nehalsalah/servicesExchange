package com.serviceexchange.www.serviceexchangeandroid.myService.displayService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.earning.Pojo;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by nehal
 */

public class DisplayMyServiceAdapter extends RecyclerView.Adapter<DisplayMyServiceAdapter.ViewHolder> {
    private List<ReviewDTO> reviewDTOList;
    Context context;

    public DisplayMyServiceAdapter(List<ReviewDTO> reviewDTOList, Context context) {
        this.reviewDTOList = reviewDTOList;
        this.context = context;
    }

    @Override
    public DisplayMyServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_reviews, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisplayMyServiceAdapter.ViewHolder viewHolder, int i) {
        viewHolder.ratingBar.setRating(reviewDTOList.get(i).getRating());
        viewHolder.clientName.setText(reviewDTOList.get(i).getUserInfo().getUserName());
        Date date=new Date(reviewDTOList.get(i).getReviewDate());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate= formatter.format(date);
        viewHolder.data.setText(strDate);
        Picasso.with(getApplicationContext()).load(reviewDTOList.get(i).getUserInfo().getImage()).placeholder(R.drawable.com_facebook_profile_picture_blank_square).fit().centerCrop().into(viewHolder.imgView);
        viewHolder.ratingBar.setRating(reviewDTOList.get(i).getRating().floatValue());
        viewHolder.review.setText(reviewDTOList.get(i).getComment());
    }

    @Override
    public int getItemCount() {
        if (reviewDTOList != null)
            return reviewDTOList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName, review, data;
        RatingBar ratingBar;
        ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            clientName = view.findViewById(R.id.clientName);
            ratingBar = view.findViewById(R.id.ratingBar);
            imgView = view.findViewById(R.id.clientPic);
            data = view.findViewById(R.id.data);
            //circle image view
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.com_facebook_profile_picture_blank_square);
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bm);
            roundedBitmapDrawable.setCircular(true);
            imgView.setImageDrawable(roundedBitmapDrawable);
            Drawable progress = ratingBar.getProgressDrawable();
            DrawableCompat.setTint(progress, Color.parseColor("#fbca03"));
            review = view.findViewById(R.id.review);
        }
    }

}