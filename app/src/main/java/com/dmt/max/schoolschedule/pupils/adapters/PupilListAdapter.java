package com.dmt.max.schoolschedule.pupils.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.pupils.views.details.PupilDetailsActivity;
import com.dmt.max.schoolschedule.pupils.views.listing.PupilsListingView;

import java.util.List;

/**
 * Created by Max on 13.05.2018.
 */

public class PupilListAdapter extends RecyclerView.Adapter<PupilListAdapter.ViewHolder> {
    private List<Pupil> pupils;
    private Context context;
    private PupilsListingView view;

    public PupilListAdapter(List<Pupil> pupils, PupilsListingView view) {
        this.pupils = pupils;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.pupil_view_holder, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pupilFirstName.setText(pupils.get(position).getFirstName());
        holder.pupilLastName.setText(pupils.get(position).getLastName());
        holder.pupil = pupils.get(position);
        holder.imageButtonDelete.setOnClickListener(holder);
        holder.pupilFirstName.setOnClickListener(holder);
        holder.pupilLastName.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return pupils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView pupilFirstName;
        private TextView pupilLastName;
        private ImageView imageButtonDelete;

        private Pupil pupil;

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
            initializePupil();
        }

        private void initializePupil() {
            Pupil pupil = new Pupil();
            pupil.setFirstName(pupilFirstName.getText().toString());
            pupil.setFirstName(pupilFirstName.getText().toString());
        }

        private void findViews(View itemView) {
            pupilFirstName = itemView.findViewById(R.id.pupilFirstName);
            pupilLastName = itemView.findViewById(R.id.pupilLastName);
            imageButtonDelete = itemView.findViewById(R.id.imageButtonDelete);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageButtonDelete:
                    PupilListAdapter.this.view.deletePupil(pupil.getId());
                case R.id.pupilFirstName:
                case R.id.pupilLastName:
                    PupilListAdapter.this.view.onPupilClick(pupil.getId());
            }
        }
    }
}