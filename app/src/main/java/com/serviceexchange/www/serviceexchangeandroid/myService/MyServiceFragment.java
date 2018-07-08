package com.serviceexchange.www.serviceexchangeandroid.myService;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.displayService.DisplayServiceFragment;

import java.util.List;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyServiceFragment extends Fragment implements MyServiceMVP.View {

    View v ;
    private RecyclerView recyclerView;
    ProgressBar progressBar;
    private MyServicesAdapter adapter;
    TextView emptyList;
    @Inject
    MyServiceMVP.Presenter presenter;

    public void setPresenter(MyServiceMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    public MyServiceFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=  inflater.inflate(R.layout.fragment_my_service, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews(){
        recyclerView = v.findViewById(R.id.my_recycler_view);
        progressBar =v.findViewById(R.id.progressBar3);
        emptyList = v.findViewById(R.id.textView27);
        emptyList.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab =  v.findViewById(R.id.addServices);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                       trans .replace(R.id.content_frame, new AddServiceFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        FragmentManager manager = getActivity().getSupportFragmentManager();
        adapter = new MyServicesAdapter(getActivity(), manager,presenter);
        recyclerView.setAdapter(adapter);
        presenter.loadMyServicesList();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
        presenter.loadMyServicesList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

    @Override
    public void navigateToDisplayService() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction trans = manager.beginTransaction();
                    trans .replace(R.id.content_frame, new DisplayServiceFragment())
                            .addToBackStack(null)
                            .commit();
    }

    @Override
    public void setMyServicesList(List<ServiceDTO> object) {
        if (object == null || object.size()<=0)
            emptyList.setVisibility(View.VISIBLE);
        adapter.refreshList(object);
        emptyList.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setEmptyService() {
        emptyList.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRemovedMyServicesList(Boolean object) {
        adapter.removeList();
    }
}
