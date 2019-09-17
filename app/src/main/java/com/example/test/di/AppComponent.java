package com.example.test.di;
import android.app.Application;

import com.example.test.service.ItemService;
import com.example.test.view.itemDetails.ItemDetailsViewModel;
import com.example.test.view.itemList.ItemListViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class, ServiceModule.class})
public interface AppComponent {

    void inject(ItemService itemService);
    void inject(ItemDetailsViewModel itemDetailsViewModel);
    void inject(ItemListViewModel itemListViewModel);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
