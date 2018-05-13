package com.dmt.max.schoolschedule.module;

import android.content.res.Resources;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Max on 29.04.2018.
 */

@Module(includes = AppModule.class)
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit retrofit(Resources resources) {
        return new Retrofit.Builder()
                .baseUrl(resources.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public SchoolSystemWebService schoolSystemWebService(Retrofit retrofit){
        return retrofit.create(SchoolSystemWebService.class);
    }
}
