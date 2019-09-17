package com.example.test.view.itemDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.test.R;
import com.example.test.databinding.ActivityItemDetailsBinding;

import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {

    String selectedItemID;
    //ViewPager vpPictures;
    TextView tvTitle, tvPrice, tvDescription;
    ItemDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        viewModel= ViewModelProviders.of(this).get(ItemDetailsViewModel.class);
        ActivityItemDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_item_details);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        //vpPictures = findViewById(R.id.vpPictures);
        tvTitle = findViewById(R.id.tvTitle);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);

        if (savedInstanceState != null) {
            selectedItemID = savedInstanceState.getString("SelectedItem");
            setSelectedItem(selectedItemID);
        }
    }

    private void setSelectedItem(String id){

    }

}
