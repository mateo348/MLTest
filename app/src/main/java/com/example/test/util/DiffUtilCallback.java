package com.example.test.util;
import androidx.recyclerview.widget.DiffUtil;
import com.example.test.model.search.Result;
import java.util.List;

/**
 * Se utiliza para verificar las diferencias entre la lista de la busqueda actual y la anterior,
 * obteniendo una unica lista resultante haciendo mas eficiente la actualizacion del adapter del RecyclerView
 */
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
