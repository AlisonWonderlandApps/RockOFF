package com.example.csit321sl01a.cardtaskr;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


//https://www.sitepoint.com/mastering-complex-lists-with-the-android-recyclerview/
public class DashboardFragment extends Fragment
{
    private ArrayList<TaskModel> taskModelArray;
    private Context context;

    public static DashboardFragment newInstance() { return new DashboardFragment();}

    public DashboardFragment()
    {

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.context = context;

        initDummyData();
    }

    public void initDummyData()
    {
        taskModelArray = new ArrayList<>();
        String [] names = getResources().getStringArray(R.array.tasks);
        String [] desc = getResources().getStringArray(R.array.groups);
        TypedArray images = getResources().obtainTypedArray(R.array.groupTypeIcons);
        for(int i=0; i<6; i++)
        {
            TaskModel tmp = new TaskModel(names[i], desc[i], images.getResourceId(i, 0), null, false);
            taskModelArray.add(tmp);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.dash_list_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dashTaskRecyclerView);
        taskListAdapter adapter = new taskListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    class taskListAdapter extends RecyclerView.Adapter<ViewHolder>
    {

        private LayoutInflater inflater;

        public taskListAdapter()
        {
            //inflater = LayoutInflater.from(context);
        }

        //Adapter method
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            //Inflate the layout, initialize the View Holder
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_list_item, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        //Adapter method
        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            TaskModel tm = taskModelArray.get(position);
            holder.setData(tm.getName(), tm.getDescription(), tm.getImageId());

            //animate(holder);
        }

        //Adapter method
        @Override
        public int getItemCount()
        {
            return taskModelArray.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView)
        {
            super.onAttachedToRecyclerView(recyclerView);
        }

        // Insert a new item to the RecyclerView on a predefined position
        public void insert(int position, TaskModel data)
        {
            taskModelArray.add(position, data);
            notifyItemInserted(position);
        }

        // Remove a RecyclerView item containing a specified Data object
        public void remove(TaskModel data)
        {
            int position = taskModelArray.indexOf(data);
            taskModelArray.remove(position);
            notifyItemRemoved(position);
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView cv;
        private TextView taskName;
        private TextView taskDesc;
        private ImageView taskType;

        public ViewHolder(View itemView)
        {
            super(itemView);
            taskName = (TextView) itemView.findViewById(R.id.cardPickText);
            taskDesc = (TextView) itemView.findViewById(R.id.taskDescription);
            taskType = (ImageView) itemView.findViewById(R.id.cardPickImage);
        }

        private void setData(String name, String desc, int image)
        {
            taskName.setText(name);
            taskDesc.setText(desc);
            taskType.setImageResource(image);
        }
    }


}
