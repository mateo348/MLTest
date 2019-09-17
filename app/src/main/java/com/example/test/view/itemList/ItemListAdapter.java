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
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.model.Item;
import com.example.test.model.Result;
import com.example.test.view.itemDetails.ItemDetailsActivity;
import com.example.test.view.itemDetails.ItemDetailsViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ItemListAdapter extends RecyclerView.Adapter<ResultViewHolder> implements OnItemListener {

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
        View view = layoutInflater.inflate(R.layout.search_item,parent,false);
        ResultViewHolder item = new ResultViewHolder(view, this);
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
        this.items = newList;
        notifyDataSetChanged();

        /*final DiffUtilCallback diffCallback = new DiffUtilCallback(this.items, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.items.clear();
        this.items.addAll(newList);
        diffResult.dispatchUpdatesTo(this);*/
    }

    @Override
    public void onItemClick(int position) {
        Result result = this.items.get(position);

        Intent intent = new Intent(context, ItemDetailsActivity.class);
        intent.putExtra("SelectedItem", result.getId());

        context.startActivity(intent);
    }
}
class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvTitle, tvPrice, tvCondition;
    ImageView itemImage;
    OnItemListener onItemListener;

    public ResultViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        tvCondition = itemView.findViewById(R.id.tvCondition);
        itemImage = itemView.findViewById(R.id.itemImage);

        this.onItemListener = onItemListener;

        itemView.setOnClickListener(this);
    }

    public void setData(Result result) {
        tvTitle.setText((result.getTitle()));
        tvPrice.setText(Currency.getInstance(Locale.getDefault()).getSymbol() +  result.getPrice().toString());
        tvCondition.setText(result.getCondition());
        Picasso.get().load(result.getThumbnail().replace("-I","-E")).into(itemImage);

    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}

    interface OnItemListener {
        void onItemClick(int position);
}
