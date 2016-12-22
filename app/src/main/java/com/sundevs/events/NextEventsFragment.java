package com.sundevs.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Latitude on 19/12/2016.
 */

public class NextEventsFragment extends Fragment {

    private ArrayList<Event> mArrayEvents;
    private String mDate;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerEvents;
    private EventsAdapter mEventsAdapter;

    public NextEventsFragment(ArrayList<Event> events, String date) {
        this.mArrayEvents = events;
        this.mDate = date;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.next_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerEvents = (RecyclerView) view.findViewById(R.id.recycler_reservations);
        TextView mLabDate = (TextView) view.findViewById(R.id.lab_date);
        mLabDate.setText(mDate);
        mEventsAdapter = new EventsAdapter(getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerEvents.setLayoutManager(mLayoutManager);
        mRecyclerEvents.setItemAnimator(new DefaultItemAnimator());
        mRecyclerEvents.setAdapter(mEventsAdapter);
        mEventsAdapter.setItems(mArrayEvents);

    }
}

