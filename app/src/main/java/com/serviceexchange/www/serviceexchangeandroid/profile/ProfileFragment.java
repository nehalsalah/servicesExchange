package com.serviceexchange.www.serviceexchangeandroid.profile;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.utils.PermissionUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileMVP.View {

    View v;
    EditText userName, bio, address, desc;
    TextView status;
    ImageView imgView;
    Uri selectedImage;
    String uploadImg;
    Boolean flagChange = false, editFlag = false;
    Button update, save, cancel;
    UserDTO user;
    ProgressBar prog;

    @Inject
    ProfileMVP.Presenter presenter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public View getV() {
        return v;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        return v;
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
        presenter.terminate();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
        presenter.loadUserStatics();
        presenter.setUserData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        userName = v.findViewById(R.id.userName);
        bio = v.findViewById(R.id.bio);
        address = v.findViewById(R.id.address);
        desc = v.findViewById(R.id.desc);
        status = v.findViewById(R.id.status);
        imgView = v.findViewById(R.id.imgView);
        update = v.findViewById(R.id.update);
        prog= v.findViewById(R.id.prog);
        save = v.findViewById(R.id.save);
        cancel = v.findViewById(R.id.cancel);
        presenter.setUserData();
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editFlag)
                    if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                        startActivityForResult(new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                                PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE);

                    }
            }

        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setFocusableInTouchMode(true);
                bio.setFocusableInTouchMode(true);
                address.setFocusableInTouchMode(true);
                desc.setFocusableInTouchMode(true);
                imgView.setClickable(true);
                update.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                editFlag = true;
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFlag = false;
                userName.setFocusable(false);
                bio.setFocusable(false);
                address.setFocusable(false);
                desc.setFocusable(false);
                imgView.setClickable(false);
                update.setVisibility(View.VISIBLE);
                save.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
                if (flagChange)
                    presenter.editDate(bio.getText().toString(), userName.getText().toString(), desc.getText().toString(), uploadImg, address.getText().toString());
                else
                    presenter.editDate(bio.getText().toString(), userName.getText().toString(), desc.getText().toString(), user.getImage(), address.getText().toString());

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setUserData();
                editFlag = false;
                userName.setFocusable(false);
                bio.setFocusable(false);
                address.setFocusable(false);
                desc.setFocusable(false);
                imgView.setClickable(false);
                update.setVisibility(View.VISIBLE);
                save.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
            }
        });
        //circle image view
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.profile);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bm);
        roundedBitmapDrawable.setCircular(true);
        imgView.setImageDrawable(roundedBitmapDrawable);

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
            prog.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setFileUrl(String fileUrl) {
        uploadImg = fileUrl;
        Picasso.with(getApplicationContext()).load(fileUrl).placeholder(R.drawable.profile).into(imgView);
        flagChange = true;
        prog.setVisibility(View.GONE);
    }

    @Override
    public void setData(UserStatics data) {
        //  level.setText(data.getCurrentLevel());
        //  points.setText(data.getAllUserPoint() + " points");
    }

    @Override
    public void setUser(UserDTO user) {
        this.user = user;
        userName.setText(user.getName() + "");
        bio.setText(user.getBio() + "");
        desc.setText(user.getDescrption() + "");
        address.setText(user.getAddress() + "");
        status.setText(user.getStatus() + "");
        Picasso.with(getApplicationContext()).load(user.getImage()).placeholder(R.drawable.profile).into(imgView);
    }
}
