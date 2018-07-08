package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepDone;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
//import com.service_exchange.api_services.dao.dto.UserInfo;
import com.service_exchange.api_services.dao.dto.UserInfo;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceStepDoneFragment extends Fragment implements ServiceStepDoneMVP.View {

    View v;
    Button save;
    String serviceName, myPoints, description, duration, selectedImage;
    List<Integer> skillList;
    SharedPreferencesModelInt sharedPreferencesModelInt;

    @Inject
    ServiceStepDoneMVP.Presenter presenter;
    //Boolean flag that keeps track of whether the Trip has been edited (true) or not (false)

    public ServiceStepDoneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferencesModelInt = SharedPreferencesModel.getInstance(getApplicationContext());
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_service_step_done, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            serviceName = bundle.getString("ServiceName", "");
            myPoints = bundle.getString("Points", "");
            skillList = bundle.getIntegerArrayList("skillList");
            duration = bundle.getString("duration", "");
            description = bundle.getString("description", "");
            selectedImage = bundle.getString("uri", "");
            Uri myUri = Uri.parse(selectedImage);
            Log.i("getbundle", "name: " + serviceName + " points: " + myPoints + " [[" + skillList.toString() + "]] desc: " + description + " duration: " + duration + " imgUri: " + myUri);
        }
        initViews();
    }

    private void initViews() {

        save = v.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });
    }


    @Override
    public void addService() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setName(serviceName);
        serviceDTO.setImage(selectedImage);
        serviceDTO.setPrice(Integer.valueOf(myPoints));
        serviceDTO.setType("offerd");
        serviceDTO.setDescription(description);
        serviceDTO.setAvailable("available");
        serviceDTO.setSkillList(skillList);
        serviceDTO.setDuration(Long.valueOf(duration));
        serviceDTO.setUo(new UserInfo(null, sharedPreferencesModelInt.getCurrentUserFromSharedPreferences().getId(), null));
        Gson gson = new Gson();
        String json = gson.toJson(serviceDTO);
        Log.i("json", json);
        presenter.addService(serviceDTO);
        nextFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Toolbar toolbar = v.findViewById(R.id.toolbar2);
        toolbar.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFragment();
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

    void backFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(ServiceStepDoneFragment.this);
        trans.commit();
        manager.popBackStack();
    }

    void nextFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction trans = manager.beginTransaction();
        Fragment frg = new MyServiceFragment();
        trans.replace(R.id.content_frame, frg)
                .commit();
    }

}
