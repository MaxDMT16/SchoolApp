package com.dmt.max.schoolschedule.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmt.max.schoolschedule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 01.05.2018.
 */

public class RecyclerViewSimpleAdapter extends RecyclerView.Adapter<RecyclerViewSimpleAdapter.SimpleViewHolder> {
    ArrayList<String> testData;

    public RecyclerViewSimpleAdapter(ArrayList<String> testData) {
        this.testData = testData;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View textView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_view_holder, parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.textView.setText(testData.get(position));
    }

    @Override
    public int getItemCount() {
        return testData.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }

}

