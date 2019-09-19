package com.example.test.view.itemDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.test.R;
import com.example.test.databinding.ActivityItemDetailsBinding;
import com.example.test.di.BaseApplication;
import com.example.test.di.DaggerItemDetailsComponent;
import com.example.test.di.DaggerItemsListComponent;
import com.example.test.di.ItemDetailsComponent;
import com.example.test.di.ItemDetailsModule;
import com.example.test.di.ItemsListComponent;
import com.example.test.model.Item;

import android.os.Bundle;

import javax.inject.Inject;

public class ItemDetailsActivity extends AppCompatActivity {

    @Inject
    ItemDetailsViewModelFactory viewModelFactory;
    ViewPager vpPictures;
    ItemDetailsViewModel viewModel;
    ActivityItemDetailsBinding binding;
    ItemDetailImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String selectedItemId = getIntent().getExtras().getString("SelectedItem");

        ItemDetailsComponent component = DaggerItemDetailsComponent.builder()
                                            .appComponent(BaseApplication.getAppComponent())
                                            .itemDetailsModule(new ItemDetailsModule(selectedItemId))
                                            .build();
        component.inject(this);

        viewModel= ViewModelProviders.of(this, viewModelFactory).get(ItemDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_details);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.getSelectedItem().observe(this, new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                binding.invalidateAll();
                imagesAdapter.notifyChanges(viewModel.getSelectedItem().getValue().getPictures());
            }
        });

        vpPictures = findViewById(R.id.vpPictures);
        imagesAdapter = new ItemDetailImagesAdapter(this);
        vpPictures.setAdapter(imagesAdapter);
    }


}
