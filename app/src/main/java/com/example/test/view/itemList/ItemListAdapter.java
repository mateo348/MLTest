package com.example.test.view.itemList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.test.R;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.databinding.SearchItemBinding;
import com.example.test.model.Item;
import com.example.test.model.Result;
import com.example.test.util.DiffUtilCallback;
import com.example.test.view.itemDetails.ItemDetailsActivity;
import com.example.test.view.itemDetails.ItemDetailsViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ResultViewHolder> {

    List<Result> items = new ArrayList<>();
    Context context;
    final LayoutInflater layoutInflater;

    public ItemListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SearchItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.search_item, parent, false);

        ResultViewHolder item = new ResultViewHolder(binding);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null? 0 : items.size();
    }

    public void updateList(List<Result> newList) {
        //DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(this.items, newList));

        //diffResult.dispatchUpdatesTo(this);

        //this.items.clear();
        //this.items.addAll(newList);
       /* this.items = newList;
        notifyDataSetChanged();*/

        final DiffUtilCallback diffCallback = new DiffUtilCallback(this.items, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.items.clear();
        this.items.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        SearchItemBinding mBinding;
        ImageView imgThumb;

        public ResultViewHolder(SearchItemBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
            binding.getRoot().setOnClickListener(this);

            imgThumb = binding.getRoot().findViewById(R.id.imgThumb);


        }


        public void setData(Result result) {
            mBinding.setResult(result);
            Picasso.get().load(result.getThumbnail().replace("-I","-E")).into(imgThumb);
        }

        @Override
        public void onClick(View view) {
            Result result = items.get(getAdapterPosition());

            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("SelectedItem", result.getId());

            context.startActivity(intent);


        }
    }

}

