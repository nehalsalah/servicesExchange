package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepTwo;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepThree.ServiceStepThreeFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceMVP;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceStepTwoFragment extends Fragment implements ServiceStepTwoMVP.View{


    View v;
    EditText description, duration;
    String serviceName,myPoints;
    List<Integer> skillList ;
    String descriptionStr="", durationStr="";
    Button cancel,save;

    @Inject
    ServiceStepTwoMVP.Presenter presenter;
    //Boolean flag that keeps track of whether the Trip has been edited (true) or not (false)
    private boolean HasChanged = false;

    public boolean isHasChanged() {
        return HasChanged;
    }

    public ServiceStepTwoFragment() {
        // Required empty public constructor
    }

    View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            HasChanged = true;
            return false;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_service_step_two, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    checkChanges();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             serviceName = bundle.getString("ServiceName","");
             myPoints = bundle.getString("Points","");
             skillList = bundle.getIntegerArrayList("skillList");
            Log.i("getbundle",serviceName + " "+ myPoints+" "+skillList.toString());
        }
        initViews();
    }

    private void initViews() {

        description = v.findViewById(R.id.description);
        duration = v.findViewById(R.id.duration);
        save=v.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });
        cancel= v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkChanges();
            }
        });
        description.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    description.clearFocus();
                    getView().requestFocus();
                }
                return false;
            }
        });
        duration.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    duration.clearFocus();
                    getView().requestFocus();
                }
                return false;
            }
        });

    }


    @Override
    public void addService() {

        durationStr = duration.getText().toString().trim();
        descriptionStr = description.getText().toString().trim();
        // validate all the required information
        if (!(TextUtils.isEmpty(durationStr))
                && !(TextUtils.isEmpty(descriptionStr)) ) {
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("skillList", (ArrayList<Integer>) skillList);
            bundle.putString("ServiceName", serviceName);
            bundle.putString("Points", myPoints);
            bundle.putString("duration",durationStr);
            bundle.putString("description",descriptionStr);
            Toast.makeText(getActivity(), "service saved", Toast.LENGTH_LONG).show();
            nextFragment(bundle);
        } else {
            Toast.makeText(getActivity(), "fill all empty fields", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onResume() {
        super.onResume();

        duration.setOnTouchListener(mTouchListener);
        description.setOnTouchListener(mTouchListener);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Toolbar toolbar = v.findViewById(R.id.toolbar2);
        toolbar.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkChanges();
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the Product.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void backFragment() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(ServiceStepTwoFragment.this);
        trans.commit();
        manager.popBackStack();
    }
    void nextFragment(Bundle bundle){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        ServiceStepThreeFragment fragment= new ServiceStepThreeFragment();
        fragment.setArguments(bundle);
        trans .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    void checkChanges() {
        if (!isHasChanged()) {
            backFragment();
        } else {
            // Otherwise if there are unsaved changes, setup a dialog to warn the user.
            // Create a click listener to handle the user confirming that
            // changes should be discarded.
            DialogInterface.OnClickListener discardButtonClickListener =
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // User clicked "Discard" button, navigate to parent
                            backFragment();
                        }
                    };

            // Show a dialog that notifies the user they have unsaved changes
            showUnsavedChangesDialog(discardButtonClickListener);
        }
    }

}
