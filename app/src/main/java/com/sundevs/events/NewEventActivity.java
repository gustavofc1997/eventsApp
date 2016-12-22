package com.sundevs.events;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edmodo.rangebar.RangeBar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Latitude on 07/12/2016.
 */

public class NewEventActivity extends Activity implements View.OnClickListener {


    private RangeBar mRange;
    private Button mBtnCreateEvent;
    private EditText mTxtName, mTxtDescription;
    private TextView mLabInitialHour, mLabFinalHour;
    private DBHelper mDbHelper;
    //    private CalendarView mCalendar;
    private MaterialCalendarView mCalendar;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        mCalendar = (MaterialCalendarView) findViewById(R.id.calendar);
        mDbHelper = new DBHelper(this);
        mLabInitialHour = (TextView) findViewById(R.id.lab_initial_time);
        mLabFinalHour = (TextView) findViewById(R.id.lab_final_time);
        mBtnCreateEvent = (Button) findViewById(R.id.btn_createEvent);
        mBtnCreateEvent.setOnClickListener(this);
        mTxtName = (EditText) findViewById(R.id.txt_name_event);
        mTxtDescription = (EditText) findViewById(R.id.txt_description_event);
        mRange = (RangeBar) findViewById(R.id.rangebar);
        mRange.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int left, int right) {
                mLabInitialHour.setText(Util.convertIndexToHour(left));
                mLabFinalHour.setText(Util.convertIndexToHour(right));
                System.out.println("left=" + left + "right=" + right);
            }
        });

    }

    private void validateData() {
        if (!mTxtName.getText().toString().equals("")) {
            if (!mTxtDescription.getText().toString().equals("")) {
                checkIfExists();
            } else {
                mProgress.dismiss();
                mTxtDescription.setError("Please set a description for your event");
            }
        } else {
            mProgress.dismiss();
            mTxtName.setError("Please set a name for your event");
        }
    }

    private void checkIfExists() {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        CalendarDay date = mCalendar.getSelectedDate();
        if (date != null) {
            Date dateSelected = date.getDate();
            boolean exits = false;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dates = sdf.format(dateSelected);
            Cursor cursor = mDbHelper.getData(dates);
            if (cursor.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    int start = cursor.getInt(cursor.getColumnIndex("start"));
                    if (start == (mRange.getLeftIndex() + 7)) {
                        exits = true;
                        break;
                    }

                } while (cursor.moveToNext());
            }
            if (exits) {
                mProgress.dismiss();

                Util.showAlert(NewEventActivity.this, "An event already exists ", onClickListener);
            } else {
                createEvent();
            }
        } else {
            mProgress.dismiss();
            Util.showAlert(NewEventActivity.this, "Please select a date ", onClickListener);

        }
    }

    private void createEvent() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        CalendarDay date = mCalendar.getSelectedDate();
        Date dateSelected = date.getDate();
        String name = mTxtName.getText().toString();
        String description = mTxtDescription.getText().toString();
        Event newEvent = new Event();
        newEvent.setDate(sdf.format(dateSelected));
        newEvent.setName(name);
        newEvent.setDescription(description);
        newEvent.setEnd(mRange.getRightIndex() + 7);
        newEvent.setStart(mRange.getLeftIndex() + 7);
        boolean test = mDbHelper.addEvent(newEvent);
        if (test) {
            mProgress.dismiss();
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            };
            Util.showAlert(NewEventActivity.this, "Event created succesfully", onClickListener);
        } else {
            mProgress.dismiss();
            Util.showDialogError(getApplicationContext(), "Could not create event,Please Try again");
        }

    }


    @Override
    public void onClick(View view) {
        mProgress = new ProgressDialog(NewEventActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
        mProgress.setMessage("Loading..");
        mProgress.setCancelable(true);
        mProgress.show();
        validateData();
    }
}
