package com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel;

import android.content.Context;

import com.service_exchange.api_services.dao.dto.UserDTO;

public interface SharedPreferencesModelInt {

    public void putUserInSharedPreferences(UserDTO user);

    public UserDTO getCurrentUserFromSharedPreferences();

    public void putToken(String token);

    public String getToken();

    boolean isApplicationRunning();

    void putApplicationRunningFlag(boolean flag);

    boolean isChatRunning();

    void putChatRunningFlag(boolean flag);
}
