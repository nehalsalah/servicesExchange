package com.serviceexchange.www.serviceexchangeandroid.earning;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.StatisticMVP;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.StatisticPresenterImp;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Earning;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import at.blogc.android.views.ExpandableTextView;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class EarningFragment extends Fragment implements EarningMVP.View {

    View v ;
    private RecyclerView recyclerView;
    private EarningAdapter adapter;
    TextView points,emptyText;
    ImageView userImg;
    SharedPreferencesModelInt sharedPreferencesModelInt;
    @Inject
    EarningMVP.Presenter presenter;

    public EarningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_earning, container, false);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        sharedPreferencesModelInt = SharedPreferencesModel.getInstance(getApplicationContext());
        presenter.setView(this);
        return v ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();

    }

    private void initViews(){
        recyclerView = v.findViewById(R.id.my_recycler_view);
        points= v.findViewById(R.id.points);
        userImg = v.findViewById(R.id.userImg);
        emptyText = v.findViewById(R.id.emptyText);
        if(!sharedPreferencesModelInt.getCurrentUserFromSharedPreferences().getImage().isEmpty())
        Picasso.with(getApplicationContext()).load(sharedPreferencesModelInt.getCurrentUserFromSharedPreferences().getImage()).placeholder(R.drawable.profile).into(userImg);
        points.setText(""+sharedPreferencesModelInt.getCurrentUserFromSharedPreferences().getBalance());
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EarningAdapter(presenter);
        recyclerView.setAdapter(adapter);
        presenter.loadEarningList();
        presenter.loadPoints();
    }

    @Override
    public void setEarningList(List<Earning> object) {
        if (object != null|| object.size()<=0)
        adapter.refreshList(object);
    }

    @Override
    public void setPoints(UserStatics object) {
      points.setText(object.getAllUserPoint()+" points");
    }

    @Override
    public void setEmptyText() {
        emptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

}
