package com.serviceexchange.www.serviceexchangeandroid.myService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.google.gson.Gson;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.displayService.DisplayServiceFragment;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by nehal
 */

public class MyServicesAdapter extends RecyclerView.Adapter<MyServicesAdapter.ViewHolder> {
    private List<ServiceDTO> MyServicesData;
    View view;
    FragmentManager manager;
    MyServiceMVP.Presenter presenter;
    int removeServiceIndex;
    Activity activity;
    SharedPreferencesModelInt sharedPreferencesModelInt;

    public MyServicesAdapter(Activity activity, FragmentManager manager, MyServiceMVP.Presenter presenter) {
        this.presenter = presenter;
        this.manager = manager;
        this.activity = activity;
        sharedPreferencesModelInt = SharedPreferencesModel.getInstance(getApplicationContext());
    }

    @Override
    public MyServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_my_service, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyServicesAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.serviceName.setText(MyServicesData.get(i).getName());
        viewHolder.idd.setText(MyServicesData.get(i).getId() + "");
        if (!(String.valueOf(MyServicesData.get(i).getPrice()).equals("null")))
            viewHolder.points.setText(String.valueOf(MyServicesData.get(i).getPrice()) + " point");
        else
            viewHolder.points.setText("0 point");
        if (!(String.valueOf(MyServicesData.get(i).getRating()).equals("null")))
            viewHolder.totalRate.setText(String.valueOf(MyServicesData.get(i).getRating()));
        else
            viewHolder.totalRate.setText(0 + "");
//        if (!MyServicesData.get(i).getImage().isEmpty())
            Picasso.with(getApplicationContext()).load(MyServicesData.get(i).getImage()).placeholder(android.R.drawable.ic_dialog_info).fit().centerCrop().into(viewHolder.img);
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
            }
        });
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(i);
            }
        });

//        viewHolder.buttonEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (ServiceDTO serviceDTO : MyServicesData) {
//                    if (serviceDTO.getId() != null && serviceDTO.getId() == Integer.parseInt(viewHolder.idd.getText().toString())) {
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("EditServiceDTO", serviceDTO);
//                        DisplayServiceFragment displayServiceFragment = new DisplayServiceFragment();
//                        displayServiceFragment.setArguments(bundle);
//                        FragmentTransaction trans = manager.beginTransaction();
//                        trans.replace(R.id.content_frame, displayServiceFragment)
//                                .addToBackStack(null)
//                                .commit();
//                        break;
//                    }
//                }
//            }
//        });

    }

    private void showDeleteDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage("Confirm delete This Service?");
        builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        builder.setNegativeButton("delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int serviceId = MyServicesData.get(i).getId();
                removeServiceIndex = i;
                int userId = sharedPreferencesModelInt.getCurrentUserFromSharedPreferences().getId();
                Map map = new LinkedHashMap();
                map.put("serviceId", serviceId);
                map.put("userId", userId);
                map.put("forced", true);
                Log.i("delete", "serviceId= " + serviceId + " userId= " + userId);
                Log.i("Map", map.toString());
                presenter.removeService(map);
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }

    public void refreshList(List<ServiceDTO> object) {
        if (object == null || object.size() <= 0) {
            if (MyServicesData != null) {
                MyServicesData.clear();
            }
            presenter.emptyList();
        } else {
            MyServicesData = object;
            notifyDataSetChanged();
        }
    }

    public void removeList() {
        Log.i("index", removeServiceIndex + "");
        Log.i("size", MyServicesData.size() + "");
        //MyServicesData.remove(removeServiceIndex);
        presenter.loadMyServicesList();
        if (MyServicesData == null || MyServicesData.size() <= 0)
            presenter.emptyList();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (MyServicesData != null)
            return MyServicesData.size();
        return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView serviceName, points, totalRate, idd;
        ImageView buttonDelete, buttonEdit, img;
        ConstraintLayout cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = itemView.findViewById(R.id.cardLayout);
            swipeLayout = itemView.findViewById(R.id.swipe);
//            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, swipeLayout.findViewById(R.id.bottom_wrapper));
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.bottom_wrapper2));
            serviceName = view.findViewById(R.id.myServiceName);
            points = view.findViewById(R.id.myPoints);
            totalRate = view.findViewById(R.id.totalRate);
            img = view.findViewById(R.id.servicesPic);
            idd = view.findViewById(R.id.idd);
            idd.setVisibility(View.GONE);
            buttonDelete = itemView.findViewById(R.id.trash);
//            buttonEdit = itemView.findViewById(R.id.edit);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "onItemSelected: " + serviceName.getText().toString(), Toast.LENGTH_SHORT).show();
                    //.....
                    for (ServiceDTO serviceDTO : MyServicesData) {
                        if (serviceDTO.getId() != null && serviceDTO.getId() == Integer.parseInt(idd.getText().toString())) {
                            Gson gson = new Gson();
                            String json = gson.toJson(serviceDTO);
                            Log.i("json", json);
                            Log.i("rating", serviceDTO.getRating() + "");
                            Log.i("revList", serviceDTO.getRevList() + "");
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("serviceDTO", serviceDTO);
                            FragmentTransaction trans = manager.beginTransaction();
                            DisplayServiceFragment displayServiceFragment = new DisplayServiceFragment();
                            displayServiceFragment.setArguments(bundle);
                            trans.replace(R.id.content_frame, displayServiceFragment)
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        }
                    }

                }
            });
        }
    }

}
