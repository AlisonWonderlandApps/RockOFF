package com.example.csit321sl01a.cardtaskr;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by csit321sl01a on 30/05/16.
 */
public class TasksAssignedFragment extends Fragment
{
    private ArrayList<TaskModel> taskListArray; //NEED THIS PASSED
    private Context context;

    public static TasksAssignedFragment newInstance() { return new TasksAssignedFragment();}

    //default
    public TasksAssignedFragment()
    {

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;

        getListData(); //TODO: GET ACTUAL DATA
    }

    public void getListData()
    {
        taskListArray = new ArrayList<>();
/*
        String [] names = getResources().getStringArray(R.array.tasks);
        String [] desc = getResources().getStringArray(R.array.groups);
        TypedArray images = getResources().obtainTypedArray(R.array.groupTypeIcons);
        for(int i=0; i<7; i++)
        {
            //String nm, String type, int icon, ArrayList<TaskModel> tasksA, ArrayList<TaskModel> tasksUA, ArrayList<String> mem
            TaskModel tmp = new TaskModel(names[i], desc[i], images.getResourceId(i, 0), null, false);
            taskListArray.add(tmp);
        } */
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = null;

        if (!taskListArray.isEmpty())
        {
            view = inflater.inflate(R.layout.recycler_list_base_view, container, false);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            tasksListAdapter adapter = new tasksListAdapter();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        //DEAL WITH EMPTY LIST CASE
        else
        {
            view = inflater.inflate(R.layout.emptylist, container, false);
        }

        return view;
    }

    class tasksListAdapter extends RecyclerView.Adapter<ViewHolder>
    {

        private LayoutInflater inflater;

        public tasksListAdapter()
        {
            //inflater = LayoutInflater.from(context);
        }

        //Adapter method
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            //Inflate the layout, initialize the View Holder
            //TODO this list_item layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_list_item, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        //Adapter method
        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            TaskModel mytask = taskListArray.get(position);
            holder.setData(mytask.getName(), mytask.getDescription(), mytask.getImageId(), position);

            //TODO: animate(holder);
        }

        //Adapter method
        @Override
        public int getItemCount()
        {
            return taskListArray.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView)
        {
            super.onAttachedToRecyclerView(recyclerView);
        }

        // Insert a new item to the RecyclerView on a predefined position
        public void insert(int position, TaskModel data)
        {
            taskListArray.add(position, data);
            notifyItemInserted(position);
        }

        // Remove a RecyclerView item containing a specified Data object
        public void remove(TaskModel data)
        {
            int position = taskListArray.indexOf(data);
            taskListArray.remove(position);
            notifyItemRemoved(position);
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView taskName;
        private TextView taskDesc;
        private ImageView taskIcon;
        private CardView thisCard;
        private int position;

        public ViewHolder(View itemView)
        {
            super(itemView);
            thisCard = (CardView) itemView.findViewById(R.id.cardView);
            thisCard.setOnClickListener(this);
            taskName = (TextView) itemView.findViewById(R.id.cardPickText);
            taskDesc = (TextView) itemView.findViewById(R.id.taskDescription);
            taskIcon = (ImageView) itemView.findViewById(R.id.cardPickImage);
        }

        private void setData(String name, String desc, int image, int pos)
        {
            position = pos;
            taskName.setText(name);
            taskDesc.setText(desc);
            taskIcon.setImageResource(image);
        }

        @Override
        public void onClick(View v)
        {
            //send TaskModel object to next view
            Intent intent = new Intent(context, TaskProfileActivity.class);
            intent.putExtra("name", taskName.getText().toString());
            intent.putExtra("desc", taskDesc.getText().toString());
            intent.putExtra("icon", taskIcon.getId());
            startActivity(intent);
/*
            Snackbar.make(v, "Go to group profile of #:" + position+ " here", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show(); */
            //TODO:GOTO GROUP PROFILE
        }
    }


}
