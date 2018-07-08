package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceDetailFragment extends Fragment implements ServiceDetailMVP.View {

    @Inject
    ServiceDetailMVP.Presenter presenter;
    @Inject
    Context context;
    @Inject
    SharedPreferencesModelInt preferencesModel;

    private ImageView serviceImage;
    private TextView serviceName;
    private TextView serviceDesc;
    private ImageView userImage;
    private TextView userName;
    private TextView price;
    private TextView reviewsNumber;
    private RatingBar ratingBar;
    private Button offerButton;
    private RecyclerView reviewsRecyclerView;
    private UserDTO user;

    public ServiceDetailFragment() {
        // Required empty public constructor
    }

    public static ServiceDetailFragment getInstance(ServiceDTO service) {
        ServiceDetailFragment fragment = new ServiceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("service", service);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_detail, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        serviceImage = view.findViewById(R.id.serviceDetailImage);
        serviceName = view.findViewById(R.id.serviceDetailName);
        serviceDesc = view.findViewById(R.id.serviceDetailDesc);
        userImage = view.findViewById(R.id.serviceDetailUserImage);
        userName = view.findViewById(R.id.serviceDetailUsername);
        price = view.findViewById(R.id.serviceDetailPrice);
        reviewsNumber = view.findViewById(R.id.serviceDetailReviewsNo);
        ratingBar = view.findViewById(R.id.serviceDetailRatingBar);
        offerButton = view.findViewById(R.id.serviceDetailOfferButton);
        reviewsRecyclerView = view.findViewById(R.id.serviceDetailReviewsRecyclerView);
        ReviewAdapter adapter = new ReviewAdapter(context, presenter);
        reviewsRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        reviewsRecyclerView.setLayoutManager(manager);
        ServiceDTO service = (ServiceDTO) getArguments().getSerializable("service");
        serviceName.setText(service.getName());

        adapter.refreshList(service.getRevList());

        user = preferencesModel.getCurrentUserFromSharedPreferences();

        Picasso.with(context).load(service.getImage()).into(serviceImage);
        serviceName.setText(service.getName());
        serviceDesc.setText(service.getDescription());
        Picasso.with(context).load(service.getUo().getImage()).into(userImage);
        userName.setText(service.getUo().getUserName());
        price.setText("" + service.getPrice());
        reviewsNumber.setText("" + service.getNumberOfTransaction());
        ratingBar.setRating(service.getRating().floatValue());
        if (user != null) {
            if (!user.getId().equals(service.getUo().getId())) {
                offerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.sendOrder(service, user);
                    }
                });
            } else {
                offerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "This is Your Service", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            offerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You Have Be Logged In", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.reRegister();
    }

    @Override
    public void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    public void setReviewsList(List<ReviewDTO> object) {

    }

    @Override
    public void orderSuccess() {
        Toast.makeText(context, "Your Order is Sent", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void orderFailed() {
        Toast.makeText(context, "Your Order Failed", Toast.LENGTH_SHORT).show();
    }
}
