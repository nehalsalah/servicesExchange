package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.pendingtransactionsdetailactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.PermissionUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class PendingTransactionActivity extends AppCompatActivity implements ReviewMVP.View {

    @Inject
    ReviewMVP.Presenter presenter;
    @Inject
    Context context;

    @BindView(R.id.pendingRating)
    EditText rating;
    @BindView(R.id.pendingComment)
    EditText comment;
    @BindView(R.id.pendingDownloadFile)
    Button downloadFileButton;
    @BindView(R.id.pendingSubmitButton)
    Button submitReviewButton;
    @BindView(R.id.toolbarForPendingTransaction)
    Toolbar toolbar;

    private TransactionDto trans;
    private static final int EXTERNAL_PERMISSION_REQUEST_CODE = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_transaction);

        ((App) getApplication()).getComponent().inject(this);
        presenter.setView(this);

        rating = findViewById(R.id.pendingRating);
        comment = findViewById(R.id.pendingComment);
        downloadFileButton = findViewById(R.id.pendingDownloadFile);
        submitReviewButton = findViewById(R.id.pendingSubmitButton);
        toolbar = findViewById(R.id.toolbarForPendingTransaction);

        trans = (TransactionDto) getIntent().getSerializableExtra("trans");
        toolbar.setTitle(trans.getServiceName());
        if (trans.getJopFile() == null || trans.getJopFile().length() == 0) {
            downloadFileButton.setEnabled(false);
        } else {
            downloadFileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkPermissionREAD_EXTERNAL_STORAGE()) {
                        presenter.downloadButtonAction(trans.getJopFile());
                    }
                }
            });
        }
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submitButtonAction();
            }
        });
    }

    @Override
    public String getRatingString() {
        return rating.getText().toString();
    }

    @Override
    public String getCommentString() {
        return comment.getText().toString();
    }

    @Override
    public void showInvalidInputMessage() {
        Toast.makeText(context, "Write a comment", Toast.LENGTH_SHORT).show();
    }

    public TransactionDto getTrans() {
        return trans;
    }

    @Override
    public void submissionSuccess() {
        Toast.makeText(context, "Review is submitted", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.reRegister();
    }

    @Override
    protected void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EXTERNAL_PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            downloadFileButton.performClick();
        }
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
