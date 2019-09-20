package com.example.test.view.itemList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SearchView;
import com.example.test.R;
import com.example.test.di.BaseApplication;
import com.example.test.di.DaggerItemsListComponent;
import com.example.test.di.ItemsListComponent;
import com.example.test.di.ItemsListScope;
import com.example.test.model.Result;
import com.example.test.util.Utils;
import com.google.android.material.snackbar.Snackbar;

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
        itemListViewModel.getErrorCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case Utils.NOT_INTERNET_ERROR_CODE:
                        notInternetActions();
                        break;
                    case Utils.SERVER_CONECCTION_ERROR_CODE:
                        notServerConecctionActions();
                        break;
                    case Utils.NOT_SEARCH_RESULT_ERROR_CODE:
                        notFindSearchResultActions();
                        break;
                }
            }
        });

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(200);
        itemAnimator.setRemoveDuration(200);
        recyclerView.setItemAnimator(itemAnimator);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (itemListViewModel.canSearchItems()){
                    itemListViewModel.searchItems(s);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


    }


    private void notInternetActions() {
        Snackbar.make(this.getCurrentFocus(), R.string.no_internet, Snackbar.LENGTH_LONG).show();
    }

    private void notServerConecctionActions() {
        Snackbar.make(this.getCurrentFocus(), R.string.server_error, Snackbar.LENGTH_LONG).show();
    }

    private void notFindSearchResultActions() {
        //Utils.showKeyboard(this, searchView);
        Snackbar.make(this.getCurrentFocus(), R.string.not_find_results, Snackbar.LENGTH_LONG).show();
    }


}
