package com.dmt.max.schoolschedule.pupils.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dmt.max.schoolschedule.model.pupil.Pupil;

import java.util.List;

/**
 * Created by Max on 13.05.2018.
 */

public class PupilListAdapter extends RecyclerView.Adapter<PupilListAdapter.ViewHolder> {
    private List<Pupil> pupils;
    private Context context;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
