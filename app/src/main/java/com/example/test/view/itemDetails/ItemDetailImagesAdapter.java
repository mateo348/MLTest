package com.example.test.view.itemDetails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.test.model.Picture;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ItemDetailImagesAdapter extends PagerAdapter {

    List<Picture> pictures;
    Context mContext;

    public ItemDetailImagesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return pictures == null? 0 : pictures.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Picasso.get()
                .load(pictures.get(position).getSecureUrl())

                .into(imageView);
        container.addView(imageView);

        return imageView;
    }

    public void notifyChanges(List<Picture> pictures)
    {
        this.pictures = pictures;
        this.notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
