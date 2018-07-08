package com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryApiInterface {

    @GET("skill/getMain")
    Call<List<SkillDTO>> getMainCategories();

    @GET("skill/getTop")
    Call<List<SkillDTO>> getTopCategories(@Query("size") int size, @Header("token") String token);
    @GET("skill/getServices")
    Call<List<ServiceDTO>> getServicesForCategories(@Query("skillId") int categoryId
            , @Query("type") String type
            , @Query("page") int page
            , @Header("token") String token);
    @GET("skill/getChild")
    Call<List<SkillDTO>> getSubCategoriesForCategory(@Query("skillId") int categoryId, @Header("token") String token);
}
