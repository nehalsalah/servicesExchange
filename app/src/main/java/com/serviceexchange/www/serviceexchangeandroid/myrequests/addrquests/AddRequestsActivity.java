package com.serviceexchange.www.serviceexchangeandroid.myrequests.addrquests;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserInfo;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.utils.PermissionUtils;

import javax.inject.Inject;

public class AddRequestsActivity extends AppCompatActivity implements AddRquestsMVP.View {

    @Inject
    AddRquestsMVP.Presenter presenter;

    @Inject
    Context context;

    private EditText request_name;
    private EditText request_price;
    private EditText request_description;
    private EditText duration_days;
    private Button cash_add_request;
    private Button exchange_add_request;
    private Button button_attach_file;
    private Button publish_request;
    Uri selectedImage;
    TextView text_attach_fil;

    private ServiceDTO serviceDTO;
    private SharedPreferencesModel sharedPreferencesModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrequests);

        ((App) this.getApplication()).getComponent().inject(this);

        presenter.setView(this);

        serviceDTO = new ServiceDTO();

        request_name = findViewById(R.id.request_name);
        request_price = findViewById(R.id.request_price);
        request_description = findViewById(R.id.request_description);
        duration_days = findViewById(R.id.duration_days);
        cash_add_request = findViewById(R.id.cash_add_request);
        exchange_add_request = findViewById(R.id.exchange_add_request);
        button_attach_file = findViewById(R.id.button_attach_file);
        publish_request = findViewById(R.id.publish_request);
        text_attach_fil=findViewById(R.id.text_attach_file);

        sharedPreferencesModel = SharedPreferencesModel.getInstance(context);

        cash_add_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cash_add_request_Clicked();
            }
        });

        exchange_add_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.exchange_add_request_Clicked();
            }
        });

        button_attach_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.button_attach_file_Clicked();
            }
        });

        publish_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = sharedPreferencesModel.getCurrentUserFromSharedPreferences().getName();
                int userId = sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId();
                String userImage = sharedPreferencesModel.getCurrentUserFromSharedPreferences().getImage();
                serviceDTO.setUo(new UserInfo(userName, userId, userImage));
                set_request_name();
                set_request_description();
                set_duration_days();
                serviceDTO.setAvailable("avalible");
                serviceDTO.setType("requested");
                serviceDTO.setPrice(Integer.valueOf(request_price.getText().toString()));
                presenter.publish_request_Clicked();
            }
        });
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
    public void setRequestTypeCash() {
        serviceDTO.setType("requested");
    }

    @Override
    public void setRequestTypeExchange() {
        serviceDTO.setType("offerd");
    }

    @Override
    public void attachFile() {
        if (checkPermissionREAD_EXTERNAL_STORAGE(getApplicationContext())) {
            startActivityForResult(new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                    PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE);

        }
    }
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(getParent(), Manifest.permission.READ_EXTERNAL_STORAGE)) {

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
        }
    }
    @Override
    public void publishRequest() {
        presenter.addMyRequests(serviceDTO);
    }

    @Override
    public void set_request_name() {
        serviceDTO.setName(request_name.getText().toString());
    }

    @Override
    public void set_request_description() {
        serviceDTO.setDescription(request_description.getText().toString());
    }

    @Override
    public void set_duration_days() {
        serviceDTO.setDuration(Long.valueOf(duration_days.getText().toString()));
    }

    @Override
    public void serviveObjectBack(ServiceDTO serviceDTO) {
        Toast.makeText(context, "Service: " + serviceDTO.getName() + " Added", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setFileUrl(String object) {
        text_attach_fil.setText("file attached");
        serviceDTO.setImage(object);
        /////////done kde...
    }
}
