package com.vsay.demo.mvpdatabinding.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.vsay.demo.mvpdatabinding.R;
import com.vsay.demo.mvpdatabinding.databinding.ActivityDetailBinding;
import com.vsay.demo.mvpdatabinding.models.Event;
import com.vsay.demo.mvpdatabinding.utils.Constants;
import com.vsay.demo.mvpdatabinding.utils.LocalUtils;
/**
 * Created by vsaya on 2/11/17.
 */

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class DetailActivity extends AppCompatActivity {

    private Event mEvent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mEvent = new Gson().fromJson(getIntent().getStringExtra(Constants.EVENT_EXTRA), Event.class);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.setEvent(mEvent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            case R.id.action_call:
                callIntent(mEvent);
                return true;
            case R.id.action_share:
                shareTheDetail(mEvent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareTheDetail(Event city) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, city.getDescription());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }

    private void callIntent(Event city) {
        String phoneNumber = getString(R.string.defaultPhoneNumber);
        if (city.getPhone() != null && !city.getPhone().equals("")) { //some mEvent doesn't have phone number return back
            phoneNumber = city.getPhone();
        }
        Uri callUri = Uri.parse("tel:" + phoneNumber);
        Intent callIntent;
        if (LocalUtils.isTelephonyEnabled(this)) {
            callIntent = new Intent(Intent.ACTION_DIAL, callUri);
        } else {
            callIntent = new Intent(Intent.ACTION_VIEW, callUri);
        }
        startActivity(callIntent);
    }
}