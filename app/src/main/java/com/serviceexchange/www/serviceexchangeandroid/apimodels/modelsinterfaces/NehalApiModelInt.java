package com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;

import java.util.Map;

public interface NehalApiModelInt {

    void getUserServices(int userId, String type);

    void deleteUserServices(Map sereviceData);
    void getUserStatic(int userId);
    void getEarningList(int userId);
    void addService(ServiceDTO serviceDTO);
    void updateUserData(UserDTO userDTO);
    void updateUserService(ServiceDTO serviceDTO);
}
