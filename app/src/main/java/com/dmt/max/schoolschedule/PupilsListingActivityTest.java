package com.dmt.max.schoolschedule;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import io.reactivex.android.schedulers.AndroidSchedulers;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.adapters.PupilsRecyclerViewAdapter;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.pupils.views.PupilsListingView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PupilsListingActivityTest extends AppCompatActivity implements PupilsListingView {

    @Inject
    SchoolSystemWebService schoolSystemWebService;

    private RecyclerView pupilsListing;
    private List<Pupil> pupils;
    private RecyclerView.Adapter adapter;
    private Callback callback;

    Resources resources;

    ProgressBar listingProgressBar;
    PupilsRecyclerViewAdapter pupilsRecyclerViewAdapter;


    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        findViews();

        ((SchoolApplication) getApplication()).getSchoolComponent().inject(this);

//        resources = getResources();
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        pupilsListing.setLayoutManager(linearLayoutManager);
//
//        pupilsRecyclerViewAdapter = new PupilsRecyclerViewAdapter();
//        pupilsListing.setAdapter(pupilsRecyclerViewAdapter);
//
//        createObservable();
    }

    private void createObservable(){
        Observable<List<Pupil>> listObservable = Observable.fromCallable(() -> {
            SystemClock.sleep(5000);
            return getPupils();
        });

        disposable = listObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pupils -> {
                    pupilsRecyclerViewAdapter.setPupilResponses(pupils);
                    listingProgressBar.setVisibility(View.GONE);
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposable!=null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void findViews() {
        pupilsListing = findViewById(R.id.main_recycler_view);
        listingProgressBar = findViewById(R.id.progressBarListing);
    }

    private List<Pupil> getPupils() {
        List<Pupil> resultPupilsList = new ArrayList<>();

        if (schoolSystemWebService == null) {
            Toast.makeText(getApplicationContext(), resources.getString(R.string.resolve_web_service_error), Toast.LENGTH_SHORT).show();
        } else {
            String accessToken = getAccessToken();

//            Call<PupilsResponse> pupilsResponse = schoolSystemWebService.getPupils(accessToken);

//            try {
//                resultPupilsList.clear();
//                resultPupilsList.addAll(pupilsResponse.execute().body().getPupils());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        return resultPupilsList;
    }

    private String getAccessToken() {

        SharedPreferences sharedPreferences = getSharedPreferences(resources.getString(R.string.shared_preferences_key), MODE_PRIVATE);

        String accessToken = sharedPreferences.getString(resources.getString(R.string.access_token_key), "access token");

        return accessToken;
    }

    @Override
    public void loadingStarted() {
        Snackbar.make(pupilsListing, R.string.loading_pupils, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showPupils(List<Pupil> pupils) {
        this.pupils.clear();
        this.pupils.addAll(pupils);
        pupilsListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        callback.onPupilsLoaded(pupils.get(0));
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Snackbar.make(pupilsListing, errorMessage, Snackbar.LENGTH_INDEFINITE).show();
    }

    public interface Callback {
        void onPupilsLoaded(Pupil pupil);

        void onPupilClicked(Pupil pupil);
    }
}
