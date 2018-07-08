package com.serviceexchange.www.serviceexchangeandroid.dummyactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.GuestHome;

public class Dummy extends AppCompatActivity {

    Button guestHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

//        guestHomeButton = findViewById(R.id.guestHomeButton);
//        guestHomeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .add(android.R.id.content, new GuestHome())
//                        .addToBackStack(null).commit();
//            }
//        });
    }
}
