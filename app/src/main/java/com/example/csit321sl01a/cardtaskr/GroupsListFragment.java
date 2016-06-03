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


//https://www.sitepoint.com/mastering-complex-lists-with-the-android-recyclerview/
public class GroupsListFragment extends Fragment
{
    private ArrayList<GroupModel> groupListArray; //NEED THIS PASSED
    private Context context;

    public static GroupsListFragment newInstance() { return new GroupsListFragment();}

    //default
    public GroupsListFragment()
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
        groupListArray = new ArrayList<>();

        String [] names = getResources().getStringArray(R.array.tasks);
        String [] desc = getResources().getStringArray(R.array.groups);
        TypedArray images = getResources().obtainTypedArray(R.array.groupTypeIcons);
        for(int i=0; i<7; i++)
        {
            //String nm, String type, int icon, ArrayList<TaskModel> tasksA, ArrayList<TaskModel> tasksUA, ArrayList<String> mem
            GroupModel tmp = new GroupModel(names[i], desc[i], images.getResourceId(i, 0), null, null, null);
            groupListArray.add(tmp);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = null;

        if (!groupListArray.isEmpty())
        {
            view = inflater.inflate(R.layout.recycler_list_base_view, container, false);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            groupsListAdapter adapter = new groupsListAdapter();
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

    class groupsListAdapter extends RecyclerView.Adapter<ViewHolder>
    {

        private LayoutInflater inflater;

        public groupsListAdapter()
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
            GroupModel mygroup = groupListArray.get(position);
            holder.setData(mygroup.getGroupName(), mygroup.getGroupType(), mygroup.getGroupIcon(), position);

            //TODO: animate(holder);
        }

        //Adapter method
        @Override
        public int getItemCount()
        {
            return groupListArray.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView)
        {
            super.onAttachedToRecyclerView(recyclerView);
        }

        // Insert a new item to the RecyclerView on a predefined position
        public void insert(int position, GroupModel data)
        {
            groupListArray.add(position, data);
            notifyItemInserted(position);
        }

        // Remove a RecyclerView item containing a specified Data object
        public void remove(GroupModel data)
        {
            int position = groupListArray.indexOf(data);
            groupListArray.remove(position);
            notifyItemRemoved(position);
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView groupName;
        private TextView groupType;
        private ImageView groupIcon;
        private CardView thisCard;
        private int position;

        public ViewHolder(View itemView)
        {
            super(itemView);
            thisCard = (CardView) itemView.findViewById(R.id.cardView);
            thisCard.setOnClickListener(this);
            groupName = (TextView) itemView.findViewById(R.id.cardPickText);
            groupType = (TextView) itemView.findViewById(R.id.taskDescription);
            groupIcon = (ImageView) itemView.findViewById(R.id.cardPickImage);
        }

        private void setData(String name, String desc, int image, int pos)
        {
            position = pos;
            groupName.setText(name);
            groupType.setText(desc);
            groupIcon.setImageResource(image);
        }

        @Override
        public void onClick(View v)
        {
            String name = groupName.getText().toString();
            Intent intent = new Intent(context, GroupProfileActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);



            //Snackbar.make(v, "Go to group profile of #:" + position+ " here", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
            //TODO:GOTO GROUP PROFILE
        }
    }


}
