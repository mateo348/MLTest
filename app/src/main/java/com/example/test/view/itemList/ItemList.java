package com.example.test.view.itemList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.test.R;

public class ItemList extends AppCompatActivity {

    SearchView searchView;
    RecyclerView reciRecyclerView;
    ItemListViewModel itemListModel;
    ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        reciRecyclerView = findViewById(R.id.recyclerView);



    }
}
