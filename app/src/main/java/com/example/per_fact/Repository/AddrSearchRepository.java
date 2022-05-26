package com.example.per_fact.Repository;

import android.util.Log;

import com.example.per_fact.Data.Location;
import com.example.per_fact.Retrofit.RetrofitNet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddrSearchRepository {
    private static AddrSearchRepository INSTANCE;

    public static AddrSearchRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AddrSearchRepository();
        }
        return INSTANCE;
    }

    public void getAddressList(String search, AddressResponseListener listener) {
        if (search != null) {
            Call<Location> call = RetrofitNet.getRetrofit().getSearchAddrService().searchAddressList(search, "KakaoAK b7da65cd26d1be7fe973d194db579efd");
            call.enqueue(new Callback<Location>() {
                @Override
                public void onResponse(Call<Location> call, Response<Location> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            for (int i = 0; i < response.body().documentsList.size(); i++) {
                                Log.i("sooyeon", "[GET] getAddressList : " + response.body().documentsList.get(i).getPlace_name());
                                Log.i("sooyeon", "[GET] getAddressList : " + response.body().documentsList.get(i).getCategory_name());
                                Log.i("sooyeon", "[GET] getAddressList : " + response.body().documentsList.get(i).getX());
                                Log.i("sooyeon", "[GET] getAddressList : " + response.body().documentsList.get(i).getY());
                            }
                            listener.onSuccessResponse(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Location> call, Throwable t) {
                    listener.onFailResponse();
                }
            });
        }
    }

    public interface AddressResponseListener{
        void onSuccessResponse(Location locationData);
        void onFailResponse();
    }

}
