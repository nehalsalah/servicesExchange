package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.submitdialogfragment.SubmitDialogFragment;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.utils.PermissionUtils;

import javax.inject.Inject;

public class ActiveTransactionDetailActivity extends AppCompatActivity implements TimelineMVP.View {

    @Inject
    TimelineMVP.Presenter presenter;
    @Inject
    Context context;
    private TextView serviceName;
    private TextView transPrice;
    private TextView expectedDate;
    private TextView transRequirements;
    private TextView creationDate;
    private Toolbar toolbar;
    private TransactionDto trans;
    private UserDTO user;
    private boolean flag;

    private static final int READ_REQUEST_CODE = 42;
    private static final int EXTERNAL_PERMISSION_REQUEST_CODE = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_transaction_detail);

        ((App) getApplication()).getComponent().inject(this);
        presenter.setView(this);

        trans = (TransactionDto) getIntent().getSerializableExtra("trans");

        serviceName = findViewById(R.id.timelineServiceName);
        transPrice = findViewById(R.id.timelinePrice);
        expectedDate = findViewById(R.id.timelineExpectedDate);
        transRequirements = findViewById(R.id.timelineRequirements);
        creationDate = findViewById(R.id.timelineCreateDate);
        toolbar = findViewById(R.id.activeServiceDetailToolBar);
        setSupportActionBar(toolbar);

        serviceName.setText(trans.getServiceName());
        transPrice.setText(""+trans.getPrice());
//        expectedDate.setText(trans.getsD().toString());
        transRequirements.setText(trans.getServiceDescription());
//        creationDate.setText(trans.getsD().toString());

        user = SharedPreferencesModel.getInstance(context).getCurrentUserFromSharedPreferences();
        Log.i("timeline ", "isProvider "+trans.isServiceProvider());

        if (trans.getType().equals("requested") & trans.getsByUser().equals(user.getId()))
            flag = true;
        else if (trans.getType().equals("offerd") & !trans.getsByUser().equals(user.getId()))
            flag = true;

        Log.i("timeline ", "flag "+trans.isServiceProvider());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("timeline ", "flag on create menu "+trans.isServiceProvider());
        if (trans.isServiceProvider() || flag) {
            getMenuInflater().inflate(R.menu.timeline_menu, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deliverNowButton:
                // TODO upload
                presenter.deliverNowAction();
                return true;
            case R.id.chatButton:
                // TODO chat
                presenter.chatAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE) {
            Uri uri = data.getData();
            presenter.uploadFile(uri);
        } else if (requestCode == EXTERNAL_PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            startFileChooser();
        }
    }

    @Override
    public void showUploadAndSubmitDialogue() {
        SubmitDialogFragment fragment = SubmitDialogFragment.getInstance(presenter, trans);
        fragment.show(getSupportFragmentManager().beginTransaction().show(fragment), null);
    }

    @Override
    public void goToChatBox() {
        // TODO show chat box
    }

    @Override
    public void startFileChooser() {
        PermissionUtils utils = new PermissionUtils();
        if (checkPermissionREAD_EXTERNAL_STORAGE()) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, READ_REQUEST_CODE);
        }
    }

    @Override
    public void setFileUrl(String object) {
        trans.setJopFile(object);
    }

    @Override
    public void submitSuccess() {
        finish();
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE() {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    PermissionUtils.showPermissionDialog(context.getString(R.string.external_storage), context, Manifest.permission.READ_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_PERMISSION_REQUEST_CODE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
