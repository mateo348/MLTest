package com.example.test.di;

import com.example.test.view.itemDetails.ItemDetailsActivity;
import dagger.Component;

@ItemDetailScope
@Component(dependencies = AppComponent.class, modules = ItemDetailsModule.class)
public interface ItemDetailsComponent {
    void inject(ItemDetailsActivity itemListActivity);

    @Component.Builder
    interface Builder
    {
        Builder itemDetailsModule(ItemDetailsModule itemDetailsModule);
        Builder appComponent(AppComponent appComponent);
        ItemDetailsComponent build();
    }
}
