package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.submitdialogfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.TimelineMVP;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

public class SubmitDialogFragment extends DialogFragment {

    private TimelineMVP.Presenter presenter;
    private TransactionDto trans;
    private Button uploadButton;
    private Button submitButton;

    public void setPresenter(TimelineMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setTrans(TransactionDto trans) {
        this.trans = trans;
    }

    public static SubmitDialogFragment getInstance(TimelineMVP.Presenter presenter, TransactionDto trans) {
        SubmitDialogFragment fragment = new SubmitDialogFragment();
        fragment.setPresenter(presenter);
        fragment.setTrans(trans);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.submit_dialog_fragment, container);
        uploadButton = view.findViewById(R.id.dialogUploadButton);
        submitButton = view.findViewById(R.id.dialogSubmitButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.uploadButtonAction();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.submitButtonAction(trans);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }
}
