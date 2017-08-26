package com.vsay.demo.mvpdatabinding.components;

import android.content.SharedPreferences;

import com.vsay.demo.mvpdatabinding.modules.SharedPreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vsaya on 2/12/17.
 */

@Singleton
@Component(modules = {SharedPreferenceModule.class})
public interface SharedPreferenceComponent {
    SharedPreferences getSharedPreference();
}