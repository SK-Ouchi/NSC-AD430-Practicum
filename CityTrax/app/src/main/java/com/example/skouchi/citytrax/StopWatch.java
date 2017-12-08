package com.example.skouchi.citytrax;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

public class StopWatch extends AppCompatActivity {

    private static final String TAG = StopWatch.class.getSimpleName();

    TextView textView ;

    Button start, pause, reset, save ;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    Handler handler;

    int Seconds, Minutes, MilliSeconds, Hours ;



    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(StopWatch.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Closing Activity")
                        .setMessage("Are you sure you want to close this activity? Time will not be saved.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                stopService(new Intent(getBaseContext(),MyService.class));
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state");
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available");
            /* initialize app */
        }

        textView = (TextView)findViewById(R.id.time);
        start = (Button)findViewById(R.id.start);
        pause = (Button)findViewById(R.id.pause);
        reset = (Button)findViewById(R.id.reset);
        save = (Button)findViewById(R.id.save) ;

        handler = new Handler() ;


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);
                start.setEnabled(false);

               // intent = new Intent(StopWatch.this,MyService.class);


            }
        });



        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);
                start.setEnabled(true);



            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                textView.setText("00:00:00");



            }
        });


    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Hours   = Minutes / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            Minutes = Minutes % 60;

            textView.setText("" + Hours + ":"
                    + String.format("%02d", Minutes) + ":"
                    +  String.format("%02d", Seconds));

            handler.postDelayed(this, 0);
        }

    };

    private void updateUI(Intent intent) {
        int time = intent.getIntExtra("time", 0);


        textView.setText("" + Minutes + ":"
                + String.format("%02d", Seconds));

    }

    public void sendTime(View view) {
        Intent timeIntent = new Intent(StopWatch.this, VolunteerCreationPage.class);
        TextView timeText = (TextView) findViewById(R.id.time);
        String message = timeText.getText().toString();
        timeIntent.putExtra("EXTRA_MESSAGE", message);
        stopService(intent);
        startActivity (timeIntent);

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        startService(intent);
//
//    }

}
