package com.dmt.max.schoolschedule.schedule_cells.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;
import com.dmt.max.schoolschedule.schedule_cells.views.listing.ScheduleCellsListingView;

import java.util.List;

/**
 * Created by Max on 09.06.2018.
 */

public class ScheduleCellsListAdapter extends RecyclerView.Adapter<ScheduleCellsListAdapter.ViewHolder> {
    private List<ScheduleCell> scheduleCells;
    private Context context;
    private ScheduleCellsListingView view;

    public ScheduleCellsListAdapter(List<ScheduleCell> scheduleCells, ScheduleCellsListingView view) {
        this.scheduleCells = scheduleCells;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View rootView = LayoutInflater.from(context).inflate(R.layout.schedule_cell_view_holder, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScheduleCell scheduleCell = scheduleCells.get(position);

        holder.lessonNumberTextView.setText(scheduleCell.getLessonNumber().toString());
        holder.dayOfTheWeekTextView.setText(scheduleCell.getDay());
        holder.roomTextView.setText(scheduleCell.getRoom().toString());
        holder.scheduleCell = scheduleCell;

        holder.lessonNumberTextView.setOnClickListener(holder);
        holder.dayOfTheWeekTextView.setOnClickListener(holder);
        holder.roomTextView.setOnClickListener(holder);
        holder.imageViewDeleteCell.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return scheduleCells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView lessonNumberTextView;
        TextView dayOfTheWeekTextView;
        TextView roomTextView;

        ImageView imageViewDeleteCell;

        ScheduleCell scheduleCell;

        public ViewHolder(View itemView) {
            super(itemView);

            findView(itemView);
            initializeCell();
        }

        private void findView(View view) {
            lessonNumberTextView = view.findViewById(R.id.cellLessonNumber);
            dayOfTheWeekTextView = view.findViewById(R.id.cellDay);
            roomTextView = view.findViewById(R.id.cellRoom);

            imageViewDeleteCell = view.findViewById(R.id.imageButtonDeleteCell);
        }

        private void initializeCell() {
            scheduleCell = new ScheduleCell();

            scheduleCell.setDay(dayOfTheWeekTextView.getText().toString());
            scheduleCell.setLessonNumber(Integer.valueOf(lessonNumberTextView.getText().toString()));
            scheduleCell.setDay(roomTextView.getText().toString());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageButtonDeleteCell:
                    ScheduleCellsListAdapter.this.view.deleteScheduleCell(scheduleCell.getId());
                    return;
                case R.id.cellLessonNumber:
                case R.id.cellDay:
                case R.id.cellRoom:
                    ScheduleCellsListAdapter.this.view.onScheduleCellClick(scheduleCell.getId());
            }
        }
    }
}
