package com.sundevs.events;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Latitude on 20/12/2016.
 */

public class Util {

    public static AlertDialog showAlert(Context context, String Message, final DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog)
                .setMessage(Message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .show();

    }
    public static void showDialogError(Context activityContext, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext,  android.R.style.Theme_DeviceDefault_Dialog);
        builder.setCancelable(true);
        builder.setTitle(R.string.app_name);
        builder.setMessage(message);
        builder.setPositiveButton(activityContext.getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public static String convertIndexToHour(int index) {
        String hour = "";
        switch (index) {
            case 0:
                hour = "7:00 am";
                break;
            case 1:
                hour = "8:00 am";
                break;
            case 2:
                hour = "9:00 am";
                break;
            case 3:
                hour = "10:00 am";
                break;
            case 4:
                hour = "11:00 am";
                break;
            case 5:
                hour = "12:00 m";
                break;
            case 6:
                hour = "1:00 pm";

                break;
            case 7:
                hour = "2:00 pm";

                break;
            case 8:
                hour = "3:00 pm";

                break;
            case 9:
                hour = "4:00 pm";

                break;
            case 10:
                hour = "5:00 pm";
                break;

            case 11:
                hour = "6:00 pm";
                break;
            case 12:
                hour = "7:00 pm";
                break;

        }
        return hour;
    }

}
