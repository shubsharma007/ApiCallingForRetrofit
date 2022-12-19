package com.example.apiexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiServices apiServices;
    AppCompatButton btn;
    TextView txtFact, txtLength;
    Call<Fact> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnGo);
        txtFact = findViewById(R.id.firstTxt);
        txtLength = findViewById(R.id.secondTxt);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://catfact.ninja/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);


        btn.setOnClickListener(view -> {
            call = apiServices.getFact();
            call.enqueue(new Callback<Fact>() {
                @Override
                public void onResponse(Call<Fact> call, Response<Fact> response) {

                    if (!response.isSuccessful()) {

                        Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    } else {
                        Fact myFact = response.body();
                        txtFact.setText(myFact.getFact());
                        txtLength.setText(myFact.getLength());
                    }

                }

                @Override
                public void onFailure(Call<Fact> call, Throwable t) {

                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        });


    }
}