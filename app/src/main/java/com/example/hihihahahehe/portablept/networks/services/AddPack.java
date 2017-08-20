package com.example.hihihahahehe.portablept.networks.services;

import com.example.hihihahahehe.portablept.models.JSONModel.PackJSONModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by hihihahahehe on 8/17/17.
 */

public interface AddPack {
    @POST("add-pack")
    Call<PackJSONModel> addPack(@Body PackJSONModel pack);
}
