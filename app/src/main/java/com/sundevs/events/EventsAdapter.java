package com.sundevs.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by SunDevs on 14/11/2016.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.updateViewHolder> {

    private ArrayList<Event> mArrayEvents;
    private Context mContext;

    public EventsAdapter(Context context) {
        mContext = context;
        mArrayEvents = new ArrayList<>();
    }

    @Override
    public updateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_event, parent, false);
        updateViewHolder holder = new updateViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(updateViewHolder holder, int position) {
        holder.mLabDescription.setText(mArrayEvents.get(position).getDescription());
        holder.mLabName.setText(mArrayEvents.get(position).getName().toUpperCase());
        holder.mLabSchedule.setText(Util.convertIndexToHour(mArrayEvents.get(position).start - 7) + " - " + Util.convertIndexToHour(mArrayEvents.get(position).end - 7));
//        holder.mLabSchedule.setText();

    }

    @Override
    public int getItemCount() {
        return mArrayEvents.size();
    }


    public void setItems(ArrayList<Event> campaigns) {
        this.mArrayEvents.clear();
        this.mArrayEvents = campaigns;
        notifyDataSetChanged();
    }

    public class updateViewHolder extends RecyclerView.ViewHolder {


        TextView mLabDescription, mLabName, mLabSchedule;

        public updateViewHolder(View itemView) {
            super(itemView);
            mLabDescription = (TextView) itemView.findViewById(R.id.lab_description_event);
            mLabName = (TextView) itemView.findViewById(R.id.lab_name_event);
            mLabSchedule = (TextView) itemView.findViewById(R.id.lab_hour_event);

        }
    }

}
