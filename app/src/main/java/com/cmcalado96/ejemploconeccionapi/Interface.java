package com.cmcalado96.ejemploconeccionapi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by xd15d on 13/11/2017.
 */

public interface Interface {

    public interface Chistaco {
        @GET("Jokes/random")
        Call<Chiste> getChiste();
    }
}
