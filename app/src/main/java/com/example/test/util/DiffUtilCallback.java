package com.example.test.util;

import androidx.recyclerview.widget.DiffUtil;

import com.example.test.model.Result;

import java.util.List;

public class DiffUtilCallback extends DiffUtil.Callback {

    List<Result> oldList;
    List<Result> newList;

    public DiffUtilCallback(List<Result> oldList, List<Result> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {

        return newList == null? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getId() == oldList.get(oldItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).equals(oldList.get(oldItemPosition));
    }
}
