package com.serviceexchange.www.serviceexchangeandroid.profile;

import android.content.SharedPreferences;
import android.net.Uri;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ProfilePresenterImp implements ProfileMVP.Presenter {

    NehalApiModelInt apiModel;
    ProfileMVP.View view;
    ProfileMVP.Model model;
    SharedPreferencesModelInt sharedPreferencesModelInt;
    FirebaseInt firebaseInt;
    UserDTO userDTO1 = new UserDTO();

    public ProfilePresenterImp(FirebaseInt firebaseInt, NehalApiModelInt apiMode, ProfileMVP.Model model) {
        this.model = model;
        this.firebaseInt = firebaseInt;
        this.apiModel = apiMode;
        sharedPreferencesModelInt = SharedPreferencesModel.getInstance(getApplicationContext());
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void OnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.User_Statics)
            view.setData((UserStatics) event.getObject());
        else if (event.getEventType() == CustomEventType.updateUserData) {
            sharedPreferencesModelInt.putUserInSharedPreferences(userDTO1);
        }
        else if (event.getEventType() == CustomEventType.FILE_URL) {
            view.setFileUrl((String) event.getObject());
        }

    }

    @Override
    public void register() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void setUserData() {
        UserDTO userDTO2 = sharedPreferencesModelInt.getCurrentUserFromSharedPreferences();
        view.setUser(userDTO2);
    }

    @Override
    public void loadUserStatics() {
        apiModel.getUserStatic(sharedPreferencesModelInt.getCurrentUserFromSharedPreferences().getId());
    }

    @Override
    public void editDate(String bio, String name, String desc, String img, String add) {
        UserDTO userDTO = sharedPreferencesModelInt.getCurrentUserFromSharedPreferences();
        userDTO1.setId(userDTO.getId());
        userDTO1.setName(name);
        userDTO1.setDescrption(desc);
        userDTO1.setBio(bio);
        userDTO1.setAddress(add);
        userDTO1.setImage(img);
        userDTO1.setBalance(userDTO.getBalance());
        userDTO1.setAccountType(userDTO.getAccountType());
        userDTO1.setAccountId(userDTO.getAccountId());
        userDTO1.setUserEmailCollection(userDTO.getUserEmailCollection());
        userDTO1.setBD(userDTO.getBD());
        userDTO1.setStatus(userDTO.getStatus());
        userDTO1.setUserTelephone(userDTO.getUserTelephone());
        userDTO1.setUSkills(userDTO.getUSkills());
        userDTO1.setJoinedDate(userDTO.getJoinedDate());
        userDTO1.setFrist(userDTO.isFrist());
        apiModel.updateUserData(userDTO1);
    }

    @Override
    public void uploadFile(Uri uri) {
        firebaseInt.uploadFile(uri);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void setView(ProfileMVP.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

}
