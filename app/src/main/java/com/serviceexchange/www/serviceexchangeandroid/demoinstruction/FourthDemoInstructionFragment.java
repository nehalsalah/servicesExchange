package com.serviceexchange.www.serviceexchangeandroid.demoinstruction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginActivity;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.FirstEnterFlag;
import com.serviceexchange.www.serviceexchangeandroid.splashactivity.SplashActivity;

public class FourthDemoInstructionFragment extends Fragment {

    Button instruction_fourth_button;

    FirstEnterFlag firstEnterFlag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_demo_instruction, container, false);

        firstEnterFlag = FirstEnterFlag.getInstance(getContext());

        firstEnterFlag.putFirstEnterFlag(1);

        instruction_fourth_button = view.findViewById(R.id.instruction_fourth_button);

        instruction_fourth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoginActivity = new Intent(getContext(), LoginActivity.class);
                startActivity(intentLoginActivity);
                getActivity().finish();
            }
        });
        return view;
    }
}
