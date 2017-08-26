package com.vsay.demo.mvpdatabinding.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.google.gson.Gson;
import com.vsay.demo.mvpdatabinding.R;
import com.vsay.demo.mvpdatabinding.activities.DetailActivity;
import com.vsay.demo.mvpdatabinding.models.Event;
import com.vsay.demo.mvpdatabinding.utils.Constants;

/**
 * Created by u742670 on 8/25/17.
 */

public class EventHandler {
    public Context mContext;

    public EventHandler(Context context) {
        this.mContext = context;
    }

    public void onEventClicked(View clickedImageView, Event event) {
        if (mContext != null) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(Constants.EVENT_EXTRA, new Gson().toJson(event));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) mContext, clickedImageView, mContext.getString(R.string.sharedElement));
            mContext.startActivity(intent, options.toBundle());
        }
    }

    public void shareTheDetail(Event event) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, event.getDescription());
        sendIntent.setType("text/plain");
        mContext.startActivity(Intent.createChooser(sendIntent, mContext.getResources().getText(R.string.send_to)));
    }
}