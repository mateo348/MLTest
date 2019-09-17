package com.example.test.di;


import com.example.test.apiconnection.ApiService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private static final String BASE_URL = "https://api.mercadolibre.com/";

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    ApiService provideApiClient(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
