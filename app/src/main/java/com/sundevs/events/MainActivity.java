package com.sundevs.events;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView mTxtName, mTxtDescription, mTxtSchedule;
    private DBHelper mDbHelper;
    private Button mBtnNewEvent;
    private ViewPager mPager;
    private ArrayList<Event> mArrayEventsToday = new ArrayList<>();
    private ArrayList<Event> mArrayEvents1 = new ArrayList<>();
    private ArrayList<Event> mArrayEvents2 = new ArrayList<>();
    private ArrayList<Event> mArrayEvents3 = new ArrayList<>();
    private ArrayList<Event> mArrayEvents4 = new ArrayList<>();
    private ArrayList<ArrayList<Event>> allEvents = new ArrayList<>();
    private pagerAdapter mPagerAdapter;
    private LinearLayout mPanelNow, mPanelNoEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mPanelNow = (LinearLayout) findViewById(R.id.panel_now);
        mPanelNoEvents = (LinearLayout) findViewById(R.id.panel_no_events);
        mPagerAdapter = new pagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mBtnNewEvent = (Button) findViewById(R.id.btn_new_event);
        mBtnNewEvent.setOnClickListener(this);
        mTxtName = (TextView) findViewById(R.id.lab_name_event);
        mTxtDescription = (TextView) findViewById(R.id.lab_description_event);
        mTxtSchedule = (TextView) findViewById(R.id.lab_hour_event);
        mDbHelper = new DBHelper(this);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        mBtnNewEvent.setTransformationMethod(null);
        mPager.setAdapter(mPagerAdapter);
        indicator.setViewPager(mPager);
        schduleAlarm();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onresume");
        load();
    }

    private class pagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<ArrayList<Event>> mEvents;

        public pagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            switch (position) {
                case 0:
                    return new NextEventsFragment(mEvents.get(0), "Today");
                case 1:
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date()); // Now use today date.
                    c.add(Calendar.DATE, 1); // Addi
                    String date = sdf.format(c.getTime());
                    return new NextEventsFragment(mEvents.get(1), date);
                case 2:
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date()); // Now use today date.
                    cal.add(Calendar.DATE, 2); // Addi
                    String dates1 = sdf.format(cal.getTime());
                    return new NextEventsFragment(mEvents.get(2), dates1);
                case 3:
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(new Date()); // Now use today date.
                    cal2.add(Calendar.DATE, 3); // Addi
                    String dates2 = sdf.format(cal2.getTime());
                    return new NextEventsFragment(mEvents.get(3), dates2);
                case 4:
                    Calendar cal3 = Calendar.getInstance();
                    cal3.setTime(new Date()); // Now use today date.
                    cal3.add(Calendar.DATE, 4); // Addi
                    String dates3 = sdf.format(cal3.getTime());
                    return new NextEventsFragment(mEvents.get(4), dates3);
                default:
                    return new NextEventsFragment(mEvents.get(0), "Today");
            }
        }

        public void update(ArrayList<ArrayList<Event>> events) {
            this.mEvents = events;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    private void loadEventsToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = new Date();
        String dates = sdf.format(date1);
        Cursor cursor = mDbHelper.getData(dates);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int start = cursor.getInt(cursor.getColumnIndex("start"));
                int end = cursor.getInt(cursor.getColumnIndex("end"));
                Event event = new Event();
                event.setDescription(description);
                event.setName(name);
                event.setEnd(end);
                event.setStart(start);
                mArrayEventsToday.add(event);

            } while (cursor.moveToNext());
        }
    }

    private void loadEvents1() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 1); // Addi
        String date = sdf.format(c.getTime());
        Cursor cursor = mDbHelper.getData(date);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int start = cursor.getInt(cursor.getColumnIndex("start"));
                int end = cursor.getInt(cursor.getColumnIndex("end"));
                Event event = new Event();
                event.setDescription(description);
                event.setName(name);
                event.setEnd(end);
                event.setStart(start);
                mArrayEvents1.add(event);

            } while (cursor.moveToNext());
        }
    }

    private void loadEvents2() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 2); // Addi
        String date = sdf.format(c.getTime());
        Cursor cursor = mDbHelper.getData(date);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int start = cursor.getInt(cursor.getColumnIndex("start"));
                int end = cursor.getInt(cursor.getColumnIndex("end"));
                Event event = new Event();
                event.setDescription(description);
                event.setName(name);
                event.setEnd(end);
                event.setStart(start);
                mArrayEvents2.add(event);

            } while (cursor.moveToNext());
        }
    }

    private void loadEvents3() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 3); // Addi
        String date = sdf.format(c.getTime());
        Cursor cursor = mDbHelper.getData(date);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int start = cursor.getInt(cursor.getColumnIndex("start"));
                int end = cursor.getInt(cursor.getColumnIndex("end"));
                Event event = new Event();
                event.setDescription(description);
                event.setName(name);
                event.setEnd(end);
                event.setStart(start);
                mArrayEvents3.add(event);

            } while (cursor.moveToNext());
        }
    }

    private void loadEvents4() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 4); // Addi
        String date = sdf.format(c.getTime());
        Cursor cursor = mDbHelper.getData(date);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int start = cursor.getInt(cursor.getColumnIndex("start"));
                int end = cursor.getInt(cursor.getColumnIndex("end"));
                Event event = new Event();
                event.setDescription(description);
                event.setName(name);
                event.setEnd(end);
                event.setStart(start);
                mArrayEvents4.add(event);

            } while (cursor.moveToNext());
        }
    }

    private void goToNewEvent() {
        Intent goToNewEvent = new Intent(MainActivity.this, NewEventActivity.class);
        startActivityForResult(goToNewEvent, 1);
    }


    private void schduleAlarm() {
        Calendar calendar = Calendar.getInstance();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, EventsBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_HOUR, pendingIntent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        load();
    }

    public void load() {
        mArrayEventsToday.clear();
        mArrayEvents1.clear();
        mArrayEvents2.clear();
        mArrayEvents3.clear();
        mArrayEvents4.clear();
        loadEventsToday();
        loadEvents1();
        loadEvents2();
        loadEvents3();
        loadEvents4();
        allEvents.add(mArrayEventsToday);
        allEvents.add(mArrayEvents1);
        allEvents.add(mArrayEvents2);
        allEvents.add(mArrayEvents3);
        allEvents.add(mArrayEvents4);
        mPagerAdapter.update(allEvents);
        loadEventRightNow();
    }

    private void loadEventRightNow() {
        boolean events = false;
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        // gets hour in 24h format
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        for (int i = 0; i < mArrayEventsToday.size(); i++) {
            Event ev = mArrayEventsToday.get(i);
            if (ev.getEnd() > hour && ev.getStart() <= hour) {
                events = true;
                mTxtName.setText(ev.getName());
                mTxtDescription.setText(ev.getDescription());
                mTxtSchedule.setText(Util.convertIndexToHour(ev.start - 7) + "-" + Util.convertIndexToHour(ev.end - 7));
                break;
            }
        }
        if (!events) {
            mPanelNow.setVisibility(View.GONE);
            mPanelNoEvents.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        goToNewEvent();
    }
}
