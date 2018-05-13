package com.dmt.max.schoolschedule.pupils.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.adapters.PupilsRecyclerViewAdapter;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.pupils.presenters.PupilsListingPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PupilsListingFragment extends Fragment implements PupilsListingView {
    @Inject
    PupilsListingPresenter pupilsListingPresenter;

    RecyclerView pupilsListing;
    RecyclerView.Adapter adapter;

    private List<Pupil> pupils = new ArrayList<>();
    private Callback callback;

    public PupilsListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ((SchoolApplication) getActivity().getApplication()).createPupilsListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pupils_listing, container, false);

        findViews(rootView);

        initLayoutReferences();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String accessToken = getAccessToken();

        pupilsListingPresenter.setView(this, accessToken);
    }

    private void findViews(View view){
        pupilsListing = view.findViewById(R.id.pupilsRecyclerView);
    }

    private void initLayoutReferences() {
        pupilsListing.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        pupilsListing.setLayoutManager(layoutManager);
        adapter = new PupilsRecyclerViewAdapter();
        pupilsListing.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pupilsListingPresenter.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SchoolApplication)getActivity().getApplication()).releasePupilsListingComponent();
    }
    //todo: miss onSaveInstanceState

    @Override
    public void loadingStarted() {
        Toast.makeText(getContext(), R.string.loading_pupils, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private String getAccessToken(){
        Resources resources = getResources();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(resources.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(resources.getString(R.string.access_token_key), "access token");

        return accessToken;
    }


    public interface Callback {
        void onPupilsLoaded(Pupil pupil);

        void onPupilClicked(Pupil pupil);
    }
}
