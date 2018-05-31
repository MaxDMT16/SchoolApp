package com.dmt.max.schoolschedule.groups.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.groups.views.listing.GroupsListingView;
import com.dmt.max.schoolschedule.model.group.Group;

import java.util.List;

/**
 * Created by Max on 20.05.2018.
 */

public class GroupsListAdapter extends RecyclerView.Adapter<GroupsListAdapter.ViewHolder> {
    private List<Group> groups;
    private Context context;
    private GroupsListingView view;

    public GroupsListAdapter(List<Group> groups, GroupsListingView view) {
        this.groups = groups;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.group_view_holder, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.groupName.setText(groups.get(position).getName());
        holder.group = groups.get(position);

        holder.imageButtonDelete.setOnClickListener(holder);
        holder.groupName.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView groupName;
        private ImageView imageButtonDelete;

        private Group group;

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
            initializaGroup();
        }

        private void findViews(View view) {
            groupName = view.findViewById(R.id.groupName);
            imageButtonDelete = view.findViewById(R.id.imageButtonDeleteGroup);
        }

        private void initializaGroup() {
            group = new Group();
            group.setName(groupName.getText().toString());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageButtonDeleteGroup:
                    GroupsListAdapter.this.view.deleteGroup(group.getId());
                    return;
                case R.id.groupName:
                    GroupsListAdapter.this.view.onGroupClick(group.getId());
            }
        }
    }
}
