package com.example.test.view.itemList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.SearchView;
import com.example.test.R;
import com.example.test.di.BaseApplication;
import com.example.test.di.DaggerItemsListComponent;
import com.example.test.di.ItemsListComponent;
import com.example.test.di.ItemsListScope;
import com.example.test.model.Result;
import com.example.test.util.Utils;
import java.util.List;
import javax.inject.Inject;

@ItemsListScope
public class ItemListActivity extends AppCompatActivity {

    @Inject
    ItemsListViewModelFactory itemsListViewModelFactory;
    SearchView searchView;
    RecyclerView recyclerView;
    ItemListViewModel itemListViewModel;
    ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemsListComponent component = DaggerItemsListComponent.builder().appComponent(BaseApplication.getAppComponent()).build();
        component.inject(this);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        itemListAdapter = new ItemListAdapter(this);
        recyclerView.setAdapter(itemListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemListViewModel= ViewModelProviders.of(this, itemsListViewModelFactory).get(ItemListViewModel.class);
        itemListViewModel.getItems().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                itemListAdapter.updateList(results);
            }
        });

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(200);
        itemAnimator.setRemoveDuration(200);
        recyclerView.setItemAnimator(itemAnimator);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (Utils.isInternetAvailable(ItemListActivity.this)){
                    searchView.clearFocus();
                    itemListViewModel.searchItems(s);
                }
                else
                    Utils.showInternetError(ItemListActivity.this);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                return false;
            }
        });


    }


}
