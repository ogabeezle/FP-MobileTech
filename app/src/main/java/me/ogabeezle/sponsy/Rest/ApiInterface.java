package me.ogabeezle.sponsy.Rest;

import java.util.HashMap;

import me.ogabeezle.sponsy.Model.GetAccount;
import me.ogabeezle.sponsy.Model.SignUpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("fetch")
    Call<GetAccount> getAccount();

    @GET("category/{category}")
    Call<GetAccount> byCategory(@Path("category") String category);

    @GET("search/{name}")
    Call<GetAccount> search(@Path("name") String name);

    @POST("register")
    Call<SignUpResponse> register(@Body HashMap register);
}
