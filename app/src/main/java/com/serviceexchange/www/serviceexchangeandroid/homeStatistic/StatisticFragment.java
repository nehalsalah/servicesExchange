package com.serviceexchange.www.serviceexchangeandroid.homeStatistic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;

import javax.inject.Inject;

import at.blogc.android.views.ExpandableTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment implements StatisticMVP.View {

    View v;
    PieGraph responseRateGraph, serviceComputeGraph, onTimeDeliveryGraph, totalFeedBackGraph;
    TextView level, responseTime,nextLevel;
    TextView responseRate, orderCompletion, ontimeDelivery, totalFeedback;
    TextView personalBalance, EarningInCurrent, activeOrder, avgSelling;
    TextView unreadMsg;
    ExpandableTextView expandableTextView;
    Button buttonToggle;

    @Inject
    StatisticMVP.Presenter presenter;

    public void setPresenter(StatisticMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    public StatisticFragment() {
        // Required empty public constructor
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_statistic, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.loadUserStatics();
        expandableTextView = v.findViewById(R.id.nextLevel);
        buttonToggle = v.findViewById(R.id.button_toggle);
        expandableTextView.setLines(1);
        // set interpolators for both expanding and collapsing animations
        expandableTextView.setInterpolator(new OvershootInterpolator());
        // toggle the ExpandableTextView
        buttonToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                buttonToggle.setBackgroundResource(expandableTextView.isExpanded() ? R.drawable.star_copy_25 : R.drawable.baseline_expand_less_white_18);
                expandableTextView.toggle();
            }
        });

        responseRateGraph = v.findViewById(R.id.responseRateGraph);
        serviceComputeGraph = v.findViewById(R.id.serviceComputeGraph);
        onTimeDeliveryGraph = v.findViewById(R.id.onTimeDeliveryGraph);
        totalFeedBackGraph = v.findViewById(R.id.totalFeedBackGraph);
        level = v.findViewById(R.id.address);
        nextLevel = v.findViewById(R.id.nextLevel);
        responseTime = v.findViewById(R.id.responseTime);

        responseRate = v.findViewById(R.id.responseRate);
        orderCompletion = v.findViewById(R.id.orderCompletion);
        ontimeDelivery = v.findViewById(R.id.ontimeDelivery);
        totalFeedback = v.findViewById(R.id.totalFeedback);

        personalBalance = v.findViewById(R.id.personalBalance);
        EarningInCurrent = v.findViewById(R.id.EarningInCurrent);
        activeOrder = v.findViewById(R.id.activeOrder);
        avgSelling = v.findViewById(R.id.avgSelling);

        unreadMsg = v.findViewById(R.id.unreadMsg);

        setStatistic(responseRateGraph, 1);
        setStatistic(onTimeDeliveryGraph,1);
        setStatistic(serviceComputeGraph, 1);
        setStatistic(totalFeedBackGraph, 1);

    }

    void setStatistic(PieGraph graph, float value) {
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#fbca03"));
        slice.setGoalValue(value);
        graph.removeSlices();
        graph.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(Color.parseColor("#ffffff"));
        slice.setGoalValue(100 - value);
        graph.addSlice(slice);
        graph.setInnerCircleRatio(100);
        graph.setPadding(5);
        graph.setDuration(1000);//default if unspecified is 300 ms
        graph.setInterpolator(new AccelerateDecelerateInterpolator());//default if unspecified is linear; constant speed
        graph.animateToGoalValues();
    }


    @Override
    public void setUserStatics(UserStatics object) {
        if (object != null) {
//            Next level requirement \n
            level.setText(object.getCurrentLevel());
            responseTime.setText(object.getResponseTime() + " h");
            nextLevel.setText("Next level requirement \n "+object.getNextLevelDescription());

            setStatistic(responseRateGraph, ((object.getResponseRate()) * 100));
            setStatistic(onTimeDeliveryGraph,((object.getOnTimeDelivery()) * 100));
            setStatistic(serviceComputeGraph, ((object.getOrderCompletion()) * 100));
            setStatistic(totalFeedBackGraph, ((object.getFeedBackRate()) * 100));

            responseRate.setText(((object.getResponseRate()) * 100) + "%");
            orderCompletion.setText(((object.getOrderCompletion()) * 100) + "%");
            ontimeDelivery.setText(((object.getOnTimeDelivery()) * 100) + "%");
            totalFeedback.setText(((object.getFeedBackRate()) * 100) + "%");

            personalBalance.setText(object.getPresonalBalance() + " points");
            EarningInCurrent.setText(object.getEarningInThisMounth() + " points");
            avgSelling.setText(object.getAvgSellIng() + " points");
            activeOrder.setText(object.getActiveOrder().getOrderCount() + " ( " + object.getActiveOrder().getOrdersValue() + " points)");

            unreadMsg.setText(object.getNumberOfUnreadedMessage()+"");

        }
    }
}
