package com.example.test.di;

import com.example.test.view.itemList.ItemListActivity;
import dagger.Component;

@ItemsListScope
@Component(dependencies = AppComponent.class, modules = ItemsListModule.class)
public interface ItemsListComponent {
    void inject(ItemListActivity itemListActivity);
}
