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
import com.example.test.apiconnection.ApiService;
import com.example.test.di.BaseApplication;
import com.example.test.model.Result;
import com.example.test.model.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemListActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    ItemListViewModel itemListViewModel;
    ItemListAdapter itemListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);

        itemListAdapter = new ItemListAdapter(this);
        recyclerView.setAdapter(itemListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemListViewModel= ViewModelProviders.of(this).get(ItemListViewModel.class);
        itemListViewModel.getItems().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                itemListAdapter.updateList(results);
            }
        });

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                itemListViewModel.searchItems(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                return false;
            }
        });



    }


}
