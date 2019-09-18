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
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {

    String selectedItemID;
    //ViewPager vpPictures;
    TextView tvTitle, tvPrice, tvDescription;
    ItemDetailsViewModel viewModel;
    ActivityItemDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel= ViewModelProviders.of(this, new ItemDetailsViewModelFactory(this.getApplication(), getIntent().getExtras().getString("SelectedItem"))).get(ItemDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_details);
        binding.setLifecycleOwner(this);

        viewModel.getSelectedItem().observe(this, new Observer<Item>() {
            @Override
            public void onChanged(Item item) {
                binding.setViewModel(viewModel);
            }
        });

        //vpPictures = findViewById(R.id.vpPictures);
    }


}
