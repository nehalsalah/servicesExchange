package com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.service_exchange.api_services.dao.dto.UserDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SharedPreferencesModel implements SharedPreferencesModelInt {

    private static final String USER = "userData";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private SharedPreferencesModel() {
    }

    private SharedPreferencesModel(Context context) {
        this.context = context;
    }

    public static SharedPreferencesModel getInstance(Context context) {
        SharedPreferencesModel instance = new SharedPreferencesModel(context);
        instance.preferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        instance.editor = instance.preferences.edit();
        return instance;
    }

    @Override
    public void putUserInSharedPreferences(UserDTO user) {
        editor.putInt(UserKeys.id.name(), user.getId());
        editor.putString(UserKeys.name.name(), user.getName());
        editor.putString(UserKeys.bio.name(), user.getBio());
        editor.putString(UserKeys.desc.name(), user.getDescrption());
        editor.putString(UserKeys.address.name(), user.getAddress());
        editor.putString(UserKeys.image.name(), user.getImage());
        editor.putString(UserKeys.status.name(), user.getStatus());
        editor.putString(UserKeys.accountType.name(), user.getAccountType());
        editor.putString(UserKeys.accountId.name(), user.getAccountId());
        editor.putInt(UserKeys.balance.name(), user.getBalance());
        editor.commit();
    }

    @Override
    public UserDTO getCurrentUserFromSharedPreferences() {
        UserDTO user = new UserDTO();
        user.setId(preferences.getInt(UserKeys.id.name(), 0));
        user.setName(preferences.getString(UserKeys.name.name(), null));
        if (user.getName() == null)
            return null;
        user.setImage(preferences.getString(UserKeys.image.name(), null));
        user.setStatus(preferences.getString(UserKeys.status.name(), null));
        user.setAccountType(preferences.getString(UserKeys.accountType.name(), null));
        user.setAccountId(preferences.getString(UserKeys.accountId.name(), null));
        user.setBalance(preferences.getInt(UserKeys.balance.name(), 0));
        user.setBio(preferences.getString(UserKeys.bio.name(), ""));
        user.setDescrption(preferences.getString(UserKeys.desc.name(), ""));
        user.setAddress(preferences.getString(UserKeys.address.name(), ""));
        return user;
    }

    @Override
    public void putToken(String token) {
        editor.putString(UserKeys.token.name(), token);
        editor.commit();
    }

    @Override
    public String getToken() {
        return preferences.getString(UserKeys.token.name(), null);
    }

    @Override
    public boolean isApplicationRunning() {
        return preferences.getBoolean("isApplicationRunning", false);
    }

    @Override
    public void putApplicationRunningFlag(boolean flag) {
        editor.putBoolean("isApplicationRunning", flag);
        editor.commit();
    }

    @Override
    public boolean isChatRunning() {
        return preferences.getBoolean("isChatRunning", false);
    }

    @Override
    public void putChatRunningFlag(boolean flag) {
        editor.putBoolean("isChatRunning", flag);
        editor.commit();
    }

    private enum UserKeys {
        id,
        name,
        desc,
        bio,
        address,
        image,
        status,
        birthDate,
        accountType,
        accountId,
        balance,
        token
    }
}
