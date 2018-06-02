package com.dmt.max.schoolschedule.teachers.views.listing;

import com.dmt.max.schoolschedule.model.teachers.Teacher;

import java.util.List;

/**
 * Created by Max on 02.06.2018.
 */

public interface TeachersListingView {
    void loadingStarted();

    void showTeachers(List<Teacher> teachers);

    void loadingFailed(String errorMessage);

    void deleteTeacher(String teacherId);

    void onDeleteSuccess();

    void onDeleteFailed(String message);

    void onTeacherClick(String teacherId);
}
