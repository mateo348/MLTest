package com.example.test.view.itemDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.test.R;
import com.example.test.databinding.ActivityItemDetailsBinding;
import com.example.test.model.Item;

import android.os.Bundle;

public class ItemDetailsActivity extends AppCompatActivity {

    ViewPager vpPictures;
    ItemDetailsViewModel viewModel;
    ActivityItemDetailsBinding binding;
    ItemDetailImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel= ViewModelProviders.of(this, new ItemDetailsViewModelFactory(this.getApplication(), getIntent().getExtras().getString("SelectedItem"))).get(ItemDetailsViewModel.class);
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
