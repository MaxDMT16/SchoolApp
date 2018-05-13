package com.dmt.max.schoolschedule.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.pupil.Pupil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 12.05.2018.
 */

public class PupilsRecyclerViewAdapter extends RecyclerView.Adapter<PupilsRecyclerViewAdapter.PupilViewHolder> {
    List<Pupil> pupilResponses = new ArrayList<>();

    public void setPupilResponses(List<Pupil> pupilResponses) {
        this.pupilResponses.clear();
        this.pupilResponses.addAll(pupilResponses);
        notifyDataSetChanged();
    }

    @Override
    public PupilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View pupilLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.pupil_view_holder, parent, false);
        PupilViewHolder pupilViewHolder = new PupilViewHolder(pupilLayout);
        return pupilViewHolder;
    }

    @Override
    public void onBindViewHolder(PupilViewHolder holder, int position) {
        Pupil pupilResponse = pupilResponses.get(position);

        holder.pupilFirstName.setText(pupilResponse.getFirstName());
        holder.pupilLastName.setText(pupilResponse.getLastName());
    }

    @Override
    public int getItemCount() {
        return pupilResponses.size();
    }

    public static class PupilViewHolder extends RecyclerView.ViewHolder {

        TextView pupilFirstName;
        TextView pupilLastName;

        public PupilViewHolder(View itemView) {
            super(itemView);

            pupilFirstName = itemView.findViewById(R.id.pupilFirstName);
            pupilLastName = itemView.findViewById(R.id.pupilLastName);
        }
    }
}
