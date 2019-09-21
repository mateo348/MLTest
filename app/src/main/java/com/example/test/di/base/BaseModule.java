package com.example.test.di.base;

import com.example.test.apiconnection.ApiService;
import com.example.test.service.ItemService;
import com.example.test.service.ItemServiceImpl;
import com.example.test.util.Utils;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger2: Se definen las clases a utilar de manera cross y singleton
 */
@Module
public class BaseModule {


    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(gsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    ApiService provideApiClient(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    ItemService provideIItemService(ApiService apiService){
        return new ItemServiceImpl(apiService);
    }
}
