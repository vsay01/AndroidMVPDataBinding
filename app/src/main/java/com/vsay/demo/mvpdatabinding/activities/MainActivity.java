package com.vsay.demo.mvpdatabinding.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.vsay.demo.mvpdatabinding.MyApplication;
import com.vsay.demo.mvpdatabinding.R;
import com.vsay.demo.mvpdatabinding.adapters.EventFeedAdapter;
import com.vsay.demo.mvpdatabinding.models.Event;
import com.vsay.demo.mvpdatabinding.presenters.EventPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content_swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.content_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    EventFeedAdapter mEventFeedAdapter;
    EventPresenter mEventPresenter;
    boolean isLoading = true;
    @Inject
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getNetComponent().inject(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        mRecyclerView.setHasFixedSize(true);
        showProgress();
        initializeAdapter();

        mEventPresenter = new EventPresenter(this, mRetrofit);
        mEventPresenter.loadPosts(false);
        initializeRefreshLayout();
    }

    public void initializeAdapter() {
        mEventFeedAdapter = new EventFeedAdapter(this, new ArrayList<Event>());
        mRecyclerView.setAdapter(mEventFeedAdapter);
    }

    public void initializeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mEventPresenter.loadPosts(true);
            }
        });
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setVisibility(View.INVISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    public void displayErrorSnackbar() {
        final View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                mEventPresenter.loadPosts(false);
            }
        };
        final View coordinatorLayoutView = findViewById(R.id.snackbar_position);
        Snackbar
                .make(coordinatorLayoutView, R.string.error_load_post_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snackbar_action_retry, clickListener)
                .show();
    }

    public void addPosts(List<Event> posts) {
        mEventFeedAdapter.addAllData(posts);
        mEventFeedAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    public void refreshPosts(List<Event> posts) {
        mEventFeedAdapter.clearData();
        mEventFeedAdapter.addAllData(posts);
        mEventFeedAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
        isLoading = false;
    }
}