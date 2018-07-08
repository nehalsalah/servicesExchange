package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne;


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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepTwo.ServiceStepTwoFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddServiceFragment extends Fragment implements AddServiceMVP.ViewStepOne {

    View v;
    List<String> categoryList, SubCategoryList;
    List<SkillDTO> categoryListObj, subCategoryListObj;
    List<Integer> skillList;
    EditText serviceNameEdt, points;
    Spinner spinnerSubCategory, spinnerCategory;
    String serviceName = "", myPoints = "";
    Button cancel, save;

    @Inject
    AddServiceMVP.Presenter presenter;
    //Boolean flag that keeps track of whether the Trip has been edited (true) or not (false)
    private boolean HasChanged = false;

    public boolean isHasChanged() {
        return HasChanged;
    }

    public AddServiceFragment() {
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
        v = inflater.inflate(R.layout.fragment_add_service, container, false);
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
//        Bundle bundle = this.getArguments();
        categoryList = new ArrayList<>();
        SubCategoryList = new ArrayList<>();
        categoryListObj = new ArrayList<>();
        subCategoryListObj = new ArrayList<>();
        skillList = new ArrayList<>();
        initViews();
//        if (bundle != null) {
//            obj = (ServiceDTO) bundle.getSerializable("serviceDTO");
//            spinnerCategory.setEnabled(false);
//            spinnerSubCategory.setEnabled(false);
//            editFlag = true;
//        }
    }

    private void initViews() {

        presenter.loadMainCategories();
        serviceNameEdt = v.findViewById(R.id.serviceNameEdt);
        points = v.findViewById(R.id.points);
        spinnerSubCategory = v.findViewById(R.id.spinnerSubCategory);
        spinnerCategory = v.findViewById(R.id.spinnerCategory);
        save = v.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    addService();

            }
        });
        cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkChanges();
            }
        });


        serviceNameEdt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    serviceNameEdt.clearFocus();
                    getView().requestFocus();
                }
                return false;
            }
        });
        points.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    points.clearFocus();
                    getView().requestFocus();
                }
                return false;
            }
        });
    }

    void setSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, (List<String>) categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    //Toast.makeText(getActivity(), item.toString(),
                    //      Toast.LENGTH_SHORT).show();
                    if (!(item.toString().equals("no item"))) {
                        skillList.clear();
                        skillList.add(categoryListObj.get(position).getId());
                        presenter.loadSubCategories(categoryListObj.get(position).getId());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void setSubSpinner() {

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, SubCategoryList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubCategory.setAdapter(adapter2);
        spinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    // Toast.makeText(getActivity(), item.toString(),
                    //        Toast.LENGTH_SHORT).show();
                    if (!(item.toString().equals("no item"))) {
                        Log.i("item", item.toString());
                        if (skillList.size() == 2) {
                            skillList.remove(1);
                        }
                        skillList.add(subCategoryListObj.get(position).getId());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void setMainCategoriesList(List<SkillDTO> object) {
        if (object == null || object.size() <= 0) {
            categoryList.clear();
            categoryList.add("no item");
        } else {
            for (SkillDTO element : object) {
                categoryList.add(element.getName());
                categoryListObj.add(element);
            }
            setSpinner();
        }
    }

    @Override
    public void setSubCategoriesList(List<SkillDTO> object) {
        if (object == null || object.size() <= 0) {
            SubCategoryList.clear();
            SubCategoryList.add("no item");
        } else {
            SubCategoryList.clear();
            for (SkillDTO element : object) {
                SubCategoryList.add(element.getName());
                subCategoryListObj.add(element);
            }
        }
        setSubSpinner();
    }

    @Override
    public void addService() {

        myPoints = points.getText().toString().trim();
        serviceName = serviceNameEdt.getText().toString().trim();
        // validate all the required information
        if (!(TextUtils.isEmpty(myPoints)) && !(TextUtils.isEmpty(serviceName))) {
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("skillList", (ArrayList<Integer>) skillList);
            bundle.putString("ServiceName", serviceName);
            bundle.putString("Points", myPoints);

            Toast.makeText(getActivity(), "service added", Toast.LENGTH_LONG).show();
            nextFragment(bundle);
        } else {
            Toast.makeText(getActivity(), "fill all empty fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void editService() {
        if (isHasChanged()) {
            Toast.makeText(getActivity(), "service edited", Toast.LENGTH_LONG).show();
            backFragment();
        } else {
            Toast.makeText(getActivity(), "no thing changed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();

        serviceNameEdt.setOnTouchListener(mTouchListener);
        points.setOnTouchListener(mTouchListener);
        spinnerSubCategory.setOnTouchListener(mTouchListener);
//        spinnerCategory.setOnTouchListener(mTouchListener);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Toolbar toolbar = v.findViewById(R.id.toolbar2);
        TextView titleText = toolbar.findViewById(R.id.titleBar);
//        if (editFlag) {
//            titleText.setText("Edit Services");
//        }
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
        trans.remove(AddServiceFragment.this);
        trans.commit();
        manager.popBackStack();
    }

    void nextFragment(Bundle bundle) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        ServiceStepTwoFragment fragment = new ServiceStepTwoFragment();
        fragment.setArguments(bundle);
        trans.replace(R.id.content_frame, fragment)
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
