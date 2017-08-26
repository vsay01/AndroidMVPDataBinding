package com.vsay.demo.mvpdatabinding.api;

import com.vsay.demo.mvpdatabinding.models.Event;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by vsaya on 2/11/17.
 */
public interface EventApi {
    @GET("vsay01/AndroidMVPDataBinding/master/feed.json")
    Observable<List<Event>>
    getFeeds();
}