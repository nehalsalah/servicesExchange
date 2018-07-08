package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepThree;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseInt;
import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseManager;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepDone.ServiceStepDoneFragment;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.utils.PermissionUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceStepThreeFragment extends Fragment implements ServiceStepThreeMVP.View {

    View v;
    ImageView img;
    Uri selectedImage;
    String serviceName, myPoints, description, duration,uploadImg;
    List<Integer> skillList;
    Boolean flagChange = false;
    Button cancel, save;
    ProgressBar progressBar4;

    @Inject
    ServiceStepThreeMVP.Presenter presenter;
    //Boolean flag that keeps track of whether the Trip has been edited (true) or not (false)
    private boolean HasChanged = false;

    public boolean isHasChanged() {
        return HasChanged;
    }

    public ServiceStepThreeFragment() {
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
        v = inflater.inflate(R.layout.fragment_service_step_three, container, false);
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
            serviceName = bundle.getString("ServiceName", "");
            myPoints = bundle.getString("Points", "");
            skillList = bundle.getIntegerArrayList("skillList");
            duration = bundle.getString("duration", "");
            description = bundle.getString("description", "");
            Log.i("getbundle", "name: " + serviceName + " points: " + myPoints + " [[" + skillList.toString() + "]] desc: " + description + " duration: " + duration);
        }
        initViews();
    }

    private void initViews() {

        img = v.findViewById(R.id.img);
        save = v.findViewById(R.id.save);
        progressBar4 = v.findViewById(R.id.progressBar4);
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
        img.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    img.clearFocus();
                    getView().requestFocus();
                }
                return false;
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                    startActivityForResult(new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                            PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE);

                }
            }

        });

    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    PermissionUtils.showPermissionDialog(context.getString(R.string.external_storage), context, Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {

                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE && resultCode == Activity.RESULT_OK) {
            selectedImage = data.getData();
            presenter.uploadFile(selectedImage);
            progressBar4.setVisibility(View.VISIBLE);
//            FirebaseInt firebaseInt = new FirebaseManager();
//            firebaseInt.uploadFile(selectedImage);
//            Bitmap bm = null;
//            try {
//                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bm);
//            img.setImageDrawable(roundedBitmapDrawable);
//            flagChange = true;
        }
    }

    @Override
    public void addService() {

        // validate all the required information
        if (flagChange) {

            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("skillList", (ArrayList<Integer>) skillList);
            bundle.putString("ServiceName", serviceName);
            bundle.putString("Points", myPoints);
            bundle.putString("duration", duration);
            bundle.putString("description", description);
            bundle.putString("uri",uploadImg );
            Log.i("uri", String.valueOf(selectedImage));
            Toast.makeText(getActivity(), "service saved", Toast.LENGTH_LONG).show();
            nextFragment(bundle);
        } else {
            Toast.makeText(getActivity(), "fill all empty fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setFileUrl(String fileUrl) {
        uploadImg=fileUrl;
        progressBar4.setVisibility(View.GONE);
        Picasso.with(getApplicationContext()).load(fileUrl).placeholder(android.R.drawable.ic_dialog_info).into(img);
        flagChange = true;

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

        img.setOnTouchListener(mTouchListener);

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
        trans.remove(ServiceStepThreeFragment.this);
        trans.commit();
        manager.popBackStack();
    }

    void nextFragment(Bundle bundle) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        ServiceStepDoneFragment fragment = new ServiceStepDoneFragment();
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
