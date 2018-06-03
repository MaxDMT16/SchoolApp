package com.dmt.max.schoolschedule.lessons.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.lessons.views.listing.LessonsListingView;
import com.dmt.max.schoolschedule.model.lesson.Lesson;

import java.util.List;

/**
 * Created by Max on 03.06.2018.
 */

public class LessonsListAdapter extends RecyclerView.Adapter<LessonsListAdapter.ViewHolder> {
    private List<Lesson> lessons;
    private Context context;
    private LessonsListingView view;

    public LessonsListAdapter(List<Lesson> lessons, LessonsListingView view) {
        this.lessons = lessons;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View rootView = LayoutInflater.from(context).inflate(R.layout.lesson_view_holder, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);

        holder.teacherId.setText(lesson.getTeacherId());
        holder.groupId.setText(lesson.getGroupId());
        holder.subject.setText(lesson.getSubject());
        holder.lesson = lesson;

        holder.subject.setOnClickListener(holder);
        holder.teacherId.setOnClickListener(holder);
        holder.groupId.setOnClickListener(holder);
        holder.imageButtonDeleteLesson.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView teacherId;
        TextView groupId;
        TextView subject;

        ImageView imageButtonDeleteLesson;

        Lesson lesson;

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
            initialieLesson();
        }

        private void findViews(View view) {
            teacherId = view.findViewById(R.id.lessonTeacherId);
            groupId = view.findViewById(R.id.lessonGroupId);
            subject = view.findViewById(R.id.lessonSubject);
            imageButtonDeleteLesson = view.findViewById(R.id.imageButtonDeleteLesson);
        }

        private void initialieLesson() {
            lesson = new Lesson();

            lesson.setTeacherId(teacherId.getText().toString());
            lesson.setGroupId(groupId.getText().toString());
            lesson.setSubject(subject.getText().toString());
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageButtonDeleteLesson:
                    LessonsListAdapter.this.view.deleteLesson(lesson.getId());
                    return;
                case R.id.lessonTeacherId:
                case R.id.lessonGroupId:
                case R.id.lessonSubject:
                    LessonsListAdapter.this.view.onLessonClick(lesson.getId());
            }
        }
    }
}
