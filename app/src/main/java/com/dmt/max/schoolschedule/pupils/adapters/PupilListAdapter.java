package com.dmt.max.schoolschedule.pupils.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.pupils.views.PupilsListingView;

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
        holder.pupilId = pupils.get(position).getId();
        holder.deleteButton.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return pupils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pupilFirstName;
        TextView pupilLastName;
        Button deleteButton;
        String pupilId;

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(View itemView){
            pupilFirstName = itemView.findViewById(R.id.pupilFirstName);
            pupilLastName = itemView.findViewById(R.id.pupilLastName);
            deleteButton = itemView.findViewById(R.id.buttonDelete);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Delete pupil with id: " + pupilId, Toast.LENGTH_SHORT).show();
        }
    }
}
