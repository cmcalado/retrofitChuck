package com.cmcalado96.ejemploconeccionapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class MainActivity extends AppCompatActivity implements Callback<Chiste> {

    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/
    }

    private void cargarJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interface request = retrofit.create(Interface.class);
        Call<Chiste> call = request.getChiste();
        call.enqueue(new Callback<Chiste>() {
            @Override
            public void onResponse(Call<Chiste> call, Response<Chiste> response) {

                Chiste joke = response.body();
                text = (TextView) findViewById(R.id.text);
                /*Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
                text.setTypeface(face);*/
                text.setText(joke.getValue());

            }

            @Override
            public void onFailure(Call<Chiste> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
}
