package com.example.test.di.ItemDetails;

import com.example.test.di.base.BaseComponent;
import com.example.test.view.itemDetails.ItemDetailsActivity;
import dagger.Component;

/**
 * Dagger2: Componente de DI utilizado dentro del scope de la activity ItemDetail.
 * Ademas tiene acceso a los que expone BaseComponent
 */
@ItemDetailScope
@Component(dependencies = BaseComponent.class, modules = ItemDetailsModule.class)
public interface ItemDetailsComponent {
    void inject(ItemDetailsActivity itemListActivity);

    @Component.Builder
    interface Builder
    {
        Builder itemDetailsModule(ItemDetailsModule itemDetailsModule);
        Builder appComponent(BaseComponent baseComponent);
        ItemDetailsComponent build();
    }
}
