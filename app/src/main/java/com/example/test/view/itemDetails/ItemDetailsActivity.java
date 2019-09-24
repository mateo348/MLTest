package com.example.test.view.itemDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.test.R;
import com.example.test.databinding.ActivityItemDetailsBinding;
import com.example.test.model.item.ItemDescription;
import com.example.test.view.BaseApplication;
import com.example.test.di.ItemDetails.DaggerItemDetailsComponent;
import com.example.test.di.ItemDetails.ItemDetailsComponent;
import com.example.test.di.ItemDetails.ItemDetailsModule;
import com.example.test.model.item.Item;
import com.example.test.view.itemsSearch.ItemsSearchActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

/**
 *Activity que representa la pantalla con el detalle del item seleccionado
 */
public class ItemDetailsActivity extends AppCompatActivity {
    private static final String TAG = "ItemDetailsActivity";
    @Inject
    ItemDetailsViewModelFactory viewModelFactory;
    ViewPager vpPictures;
    ItemDetailsViewModel viewModel;
    ActivityItemDetailsBinding binding;
    ItemDetailImagesAdapter imagesAdapter;
    CardView cardView;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDagger();

        setupControls();

        /**
         *Se observa el cambio que se produce sobre el item seleccionado al ser instanciado
         * y se actualizan los bindeos y lista de imagenes
         */
        viewModel.getSelectedItem().observe(this, new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                Log.i(TAG, ItemDetailsActivity.this.getString(R.string.seted_selected_item));
                binding.setViewModel(viewModel);
                imagesAdapter.notifyChanges(viewModel.getSelectedItem().getValue().getPictures());
                onDoneSearchControlsVisibility();
            }
        });

        /**
         * Se observa el cambio sobre el objeto que tiene la descripcion del item y se actualiza la vista
         */
        viewModel.getItemDescription().observe(this, new Observer<ItemDescription>() {
            @Override
            public void onChanged(ItemDescription itemDescription) {
                binding.invalidateAll();
                //onDoneSearchControlsVisibility();
            }
        });

        /**
         * Se observan los cambios sobre el codigo de error y realiza las acciones pertinentes
         */
        viewModel.getErrorCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case ItemDetailsViewModel.SERVER_CONECCTION_ERROR_CODE:
                        notServerConecctionActions();
                    case ItemDetailsViewModel.SERVER_ERROR_CODE:
                        onServerErrorActions();
                        break;
                }
                onDoneSearchControlsVisibility();
            }
        });

        onSearchControlsVisibility();
    }

    private void setupControls() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ItemDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_details);
        binding.setLifecycleOwner(this);
        pbLoading = findViewById(R.id.pbLoading);
        cardView = findViewById(R.id.cardView);
        vpPictures = findViewById(R.id.vpPictures);
        imagesAdapter = new ItemDetailImagesAdapter(this);
        vpPictures.setAdapter(imagesAdapter);
    }

    private void notServerConecctionActions() {
        Toast.makeText(this, R.string.server_connection_error, Toast.LENGTH_LONG).show();
    }

    private void onServerErrorActions() {
        Toast.makeText(this, R.string.server_error, Toast.LENGTH_LONG).show();
    }

    /**
     * Se injectan las instancias con Dagger2 y se pasa como parametro el ID del item seleccionado para buscarlo
     */
    private void setupDagger() {
        String selectedItemId = getIntent().getExtras().getString(ItemsSearchActivity.SELECTED_ITEM_ID_KEY);

        ItemDetailsComponent component = DaggerItemDetailsComponent.builder()
                                            .appComponent(BaseApplication.getBaseComponent())
                                            .itemDetailsModule(new ItemDetailsModule(selectedItemId))
                                            .build();
        component.inject(this);
    }

    /**
     * Durante la carga de los datos del item Seleccionado, se muestra la barra de progreso
     */
    private void onSearchControlsVisibility() {
        vpPictures.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
    }

    /**
     * Una vez que ya se llamó a la API en busqueda del item seleccionado, se oculta la barra de progreso
     * y se muestran las imagenes
     */
    private void onDoneSearchControlsVisibility() {
        vpPictures.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
    }


}
