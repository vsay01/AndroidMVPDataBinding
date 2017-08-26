package com.vsay.demo.mvpdatabinding.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vsay.demo.mvpdatabinding.R;

/**
 * Created by u742670 on 8/25/17.
 */

public class ImageBinder {

    private ImageBinder() {
        //NO-OP
    }

    @BindingAdapter({"imageURL"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .error(R.drawable.placeholder_nomoon)
                .placeholder(R.drawable.placeholder_nomoon)
                .into(view);
    }
}
