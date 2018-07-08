package com.serviceexchange.www.serviceexchangeandroid.myService.displayService;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayServiceFragment extends Fragment {

    private RecyclerView recyclerView;
    private DisplayMyServiceAdapter adapter;
    TextView review, serviceName, description;
    String reviewStr, serviceNameStr, descriptionStr;
    float ratingBar;
    RatingBar ratingBar2;
    List<ReviewDTO> reviewDTOList;
    View v;
    SliderLayout sliderShow;
    ServiceDTO obj;
    String imgUrl;

    @Inject
    DisplayServiceMVP.Presenter presenter;

    public DisplayServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Toolbar toolbar = v.findViewById(R.id.toolbarService);
        toolbar.findViewById(R.id.backService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFragment();
            }
        });

    }

    void backFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(DisplayServiceFragment.this);
        trans.commit();
        manager.popBackStack();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_display_service, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            obj = (ServiceDTO) bundle.getSerializable("serviceDTO");
            if (obj.getRevList() != null)
                reviewStr = String.valueOf(obj.getRevList().size()) + " Reviews";
            else
                reviewStr = "0 Reviews";
            serviceNameStr = obj.getName();
            descriptionStr = obj.getDescription();
            if (obj.getRating() != null)
                ratingBar = obj.getRating().floatValue();
            else
                ratingBar = 0;
            reviewDTOList = obj.getRevList();
        }
        initViews();
    }

    void initViews() {

        serviceName = v.findViewById(R.id.serviceName);
        serviceName.setText(serviceNameStr);
        review = v.findViewById(R.id.rev);
        review.setText(reviewStr);
        description = v.findViewById(R.id.description);
        description.setText(descriptionStr);
        ratingBar2 = v.findViewById(R.id.ratingBar2);
        Drawable progress = ratingBar2.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.parseColor("#fbca03"));
        ratingBar2.setRating(ratingBar);
        recyclerView = v.findViewById(R.id.reviews_recycle_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DisplayMyServiceAdapter(reviewDTOList, getContext());
        recyclerView.setAdapter(adapter);
        sliderShow = v.findViewById(R.id.slider);
        TextSliderView textSliderView2 = new TextSliderView(getContext());
        if (obj.getImage() != null)
            textSliderView2
                    .description(serviceNameStr)
                    .image(obj.getImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
        else
            textSliderView2
                    .description(serviceNameStr)
                    .image(android.R.drawable.ic_dialog_info)
                    .setScaleType(BaseSliderView.ScaleType.Fit);
        sliderShow.addSlider(textSliderView2);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sliderShow.stopAutoCycle();
        super.onStop();
    }

}
