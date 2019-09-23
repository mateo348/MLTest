package com.example.test.view.itemList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.test.R;
import com.example.test.di.ItemsList.DaggerItemsListComponent;
import com.example.test.util.AppUtils;
import com.example.test.view.BaseApplication;
import com.example.test.di.ItemsList.ItemsListComponent;
import com.example.test.di.ItemsList.ItemsListScope;
import com.example.test.model.Result;

import java.util.List;
import javax.inject.Inject;

/**
 * Activity que representa la pantalla de busqueda de publicaciones (item)
 */
@ItemsListScope
public class ItemListActivity extends AppCompatActivity {

    public static String SELECTED_ITEM_ID_KEY = "selectedItemID";
    private static final String TAG = "ItemListActivity";

    @Inject
    ItemsListViewModelFactory itemsListViewModelFactory;
    SearchView searchView;
    RecyclerView recyclerView;
    ItemListViewModel itemListViewModel;
    ItemListAdapter itemListAdapter;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDagger();

        setupControls();

        setupListAnimations();

        /**
         * Se observan los cambios sobre la lista para actualizarla con los resultados encontrados
         */
        itemListViewModel.getItems().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                itemListAdapter.updateList(results);
                if(results.size() > 0)
                    Log.i(TAG, ItemListActivity.this.getString(R.string.new_results));
                onDoneSearchControlsVisibility();
            }
        });

        /**
         *Se observa si hubo cambios sobre el codigo de error en caso de que no se halla realizado la busqueda
         * o hubera fallado, mostrando el mensaje correspondiente
         */
        itemListViewModel.getErrorCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case ItemListViewModel.NOT_INTERNET_ERROR_CODE:
                        notInternetActions();
                        break;
                    case ItemListViewModel.SERVER_CONECCTION_ERROR_CODE:
                        notServerConecctionActions();
                        break;
                    case ItemListViewModel.NOT_FOUND_RESULT_ERROR_CODE:
                        notFindSearchResultActions();
                        break;
                    case ItemListViewModel.SERVER_ERROR_CODE:
                        onServerErrorActions();
                        break;
                }
                onDoneSearchControlsVisibility();
            }
        });

        /**
         * Si está habilitada la busqueda, se llama al viewModel para realizarla
         */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (itemListViewModel.canSearchItems(AppUtils.getInstance())){
                    onSearchControlsVisibility();
                    itemListViewModel.searchItems(query);
                } return false;
            }
            @Override
            public boolean onQueryTextChange(String s) { return false; }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setSearchViewFocus();
    }

    /**
     * Si en la lista hay elementos, no se pone el foco de forma automatica sobre la busqueda, caso contrario
     * se fuerza el foco sobre la busqueda
     */
    private void setSearchViewFocus() {
        if(itemListViewModel.getItems().getValue() == null || itemListViewModel.getItems().getValue().size() == 0)
            searchView.requestFocus();
        else
            searchView.clearFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupControls() {
        pbLoading = findViewById(R.id.pbLoading);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        itemListAdapter = new ItemListAdapter(this);
        recyclerView.setAdapter(itemListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemListViewModel= ViewModelProviders.of(this, itemsListViewModelFactory).get(ItemListViewModel.class);
    }

    /**
     * Durante la busqueda de resulados, se muestra la barra de progreso
     */
    private void onSearchControlsVisibility() {
        recyclerView.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
    }

    /**
     * Una vez que ya se llamó a la API en busqueda de resultados, se reestablece la vista de la lista
     */
    private void onDoneSearchControlsVisibility() {
        recyclerView.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }

    private void setupListAnimations() {
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(200);
        itemAnimator.setRemoveDuration(200);
        recyclerView.setItemAnimator(itemAnimator);
    }

    private void setupDagger() {
        ItemsListComponent component = DaggerItemsListComponent.builder().baseComponent(BaseApplication.getBaseComponent()).build();
        component.inject(this);
    }

    private void notInternetActions() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    private void notServerConecctionActions() {
        Toast.makeText(this, R.string.server_connection_error, Toast.LENGTH_LONG).show();
    }

    private void notFindSearchResultActions() {
        Toast.makeText(this, R.string.not_find_results, Toast.LENGTH_LONG).show();
    }

    private void onServerErrorActions() {
        Toast.makeText(this, R.string.server_error, Toast.LENGTH_LONG).show();
    }



}
