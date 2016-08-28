package com.alarm.sak.clock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class AlarmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;



    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        final Calendar calendar = Calendar.getInstance();
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        int min=1;
        int max=7;

        Random r = new Random();
        final int random_number = r.nextInt(max - min + 1) + min;
        Log.e("random number is ", String.valueOf(random_number));


        final Intent my_intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        Button Alarm_ON = (Button) findViewById(R.id.bu1);
        Alarm_ON.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                int hour = 0;
                int minute = 0;
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {

                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());

                    hour = alarmTimePicker.getHour();
                    minute = alarmTimePicker.getMinute();
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

                    hour = alarmTimePicker.getCurrentHour();
                    minute = alarmTimePicker.getCurrentMinute();
                }


                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);


                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }

                if (minute < 10) {

                    minute_string = "0" + String.valueOf(minute);
                }
                //setAlarmText("Alarm set to: " + hour_string + ":" + minute_string);
                Toast.makeText(getApplicationContext(), "Alarm set to: " + hour_string + ":" + minute_string, Toast.LENGTH_LONG).show();
                my_intent.putExtra("extra", "alarm on");

                pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                        my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pendingIntent);

            }
        });
        alarmManager.cancel(pendingIntent);


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.mydialog);
        final EditText ans = (EditText) dialog.findViewById(R.id.t1);
        final TextView tv = (TextView)dialog.findViewById(R.id.textD);

        Button Turn_OFF = (Button) findViewById(R.id.bu2);
        Turn_OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        Button stop = (Button) dialog.findViewById(R.id.b1);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(ans.getText().toString()) == 0 ) {
                        dialog.dismiss();
                        System.exit(0);
                    } else
                    if (random_number == 1) {
                        tv.setText("Count these !@#$%^&*()");
                         if (Integer.parseInt(ans.getText().toString()) == 10) {
                        dialog.dismiss();
                        System.exit(0);
                        }
                    }else
                    if (random_number == 2) {
                        tv.setText("What is 0x10");
                            if (Integer.parseInt(ans.getText().toString()) == 0) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    }else
                    if (random_number == 3) {
                        tv.setText("What is -90+92");
                            if (Integer.parseInt(ans.getText().toString()) == 2) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    }else
                    if (random_number == 4) {
                        tv.setText("how much is 1$");
                            if (Integer.parseInt(ans.getText().toString()) == 70) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    }else
                    if (random_number == 5) {
                        tv.setText("What is 4^3");
                            if (Integer.parseInt(ans.getText().toString()) == 64 ) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    }else
                    if (random_number == 6) {
                        tv.setText("What is (-24 - 47 + 183)?");
                        if (Integer.parseInt(ans.getText().toString()) == 112) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    }
                    else
                    if (random_number == 7) {
                        tv.setText("What is L");
                             if (Integer.parseInt(ans.getText().toString()) == 50) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }

        });
        }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}