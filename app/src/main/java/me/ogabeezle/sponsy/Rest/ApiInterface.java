package me.ogabeezle.sponsy.Rest;

import me.ogabeezle.sponsy.Model.GetAccount;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("fetch")
    Call<GetAccount> getAccount();

    @GET("category/{category}")
    Call<GetAccount> byCategory(@Path("category") String category);

    @GET("search/{name}")
    Call<GetAccount> search(@Path("name") String name);


}
