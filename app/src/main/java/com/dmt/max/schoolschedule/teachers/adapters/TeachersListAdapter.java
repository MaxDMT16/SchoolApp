package com.dmt.max.schoolschedule.teachers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.teachers.views.listing.TeachersListingView;

import java.util.List;

/**
 * Created by Max on 03.06.2018.
 */

public class TeachersListAdapter extends RecyclerView.Adapter<TeachersListAdapter.ViewHolder> {
    private List<Teacher> teachers;
    private Context context;
    private TeachersListingView view;

    public TeachersListAdapter(List<Teacher> teachers, TeachersListingView view) {
        this.teachers = teachers;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View rootView = LayoutInflater.from(context).inflate(R.layout.teacher_view_holder, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Teacher teacher = teachers.get(position);

        holder.teacherFirstName.setText(teacher.getFirstName());
        holder.teacherLastName.setText(teacher.getLastName());
        holder.teacher = teacher;
        holder.teacherFirstName.setOnClickListener(holder);
        holder.teacherLastName.setOnClickListener(holder);
        holder.imageButtonDelete.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView teacherFirstName;
        private TextView teacherLastName;
        private ImageView imageButtonDelete;

        private Teacher teacher;

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
            initializeTeacher();
        }

        private void findViews(View itemView) {
            teacherFirstName = itemView.findViewById(R.id.teacherFirstName);
            teacherLastName = itemView.findViewById(R.id.teacherLastName);
            imageButtonDelete = itemView.findViewById(R.id.imageButtonDeleteTeacher);
        }

        private void initializeTeacher(){
            teacher = new Teacher();
            teacher.setFirstName(teacherFirstName.getText().toString());
            teacher.setLastName(teacherLastName.getText().toString());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageButtonDeleteTeacher:
                    TeachersListAdapter.this.view.deleteTeacher(teacher.getId());
                    return;
                case R.id.teacherFirstName:
                case R.id.teacherLastName:
                    TeachersListAdapter.this.view.onTeacherClick(teacher.getId());
            }
        }
    }
}
