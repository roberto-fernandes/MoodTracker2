package robfernandes.xyz.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

import robfernandes.xyz.moodtracker.Model.Mood;
import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.R;
import robfernandes.xyz.moodtracker.Utils.Constants;
import robfernandes.xyz.moodtracker.Utils.MoodType;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener,
        GestureDetector.OnGestureListener {

    private ImageView addNoteImage;
    private ImageView moodHistoryImage;
    private ImageView faceImage;
    private View background;

    private MediaPlayer mMediaPlayer;
    private GestureDetector mGestureDetector;
    private int mCurrentMoodTypeID;
    private MoodHistory mMoodHistory;
    private String mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureDetector = new GestureDetector(this, this);
        findViewById(android.R.id.content).setOnTouchListener(this);
        mMoodHistory = new MoodHistory(this);
        mMediaPlayer = new MediaPlayer();

        addNoteImage = findViewById(R.id.activity_main_note_image);
        moodHistoryImage = findViewById(R.id.activity_main_history_image);
        background = findViewById(R.id.activity_main_background);
        faceImage = findViewById(R.id.activity_main_face_image);

        setCurrentMood();
        setViewClicks();
        startAlarmManager();
    }

    private void setViewClicks() {
        //show alert dialog
        addNoteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                @SuppressLint("InflateParams") View mAlertDialogView = inflater.inflate(R.layout.alert_dialog, null);
                final EditText alertDialogNote = mAlertDialogView
                        .findViewById(R.id.alert_dialog_note_edit_text);
                //If there is a note it shows
                if (!alertDialogNote.getText().toString().equals("")) {
                    alertDialogNote.setText(mNote);
                    saveCurrentMood();
                }
                // Inflate and set the layout for the dialog
                builder.setView(mAlertDialogView)
                        .setPositiveButton(R.string.add_note, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                mNote = alertDialogNote.getText().toString();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create().show();
            }
        });

        moodHistoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this
                        , MoodHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        saveCurrentMood();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentMood();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        double mDistance = e1.getY() - e2.getY();
        if (mDistance > 0) {
            goToNextMood();
        } else if (mDistance < 0) {
            goToPreviousMood();
        }
        return true;
    }

    private void goToNextMood() {
        int lastItem = Constants.MOOD_TYPES.size() - 1;
        if (mCurrentMoodTypeID >= lastItem) {
            mCurrentMoodTypeID = 0;
        } else {
            mCurrentMoodTypeID++;
        }
        changeMood();
    }

    private void goToPreviousMood() {
        int lastItem = Constants.MOOD_TYPES.size() - 1;
        if (mCurrentMoodTypeID <= 0) {
            mCurrentMoodTypeID = lastItem;
        } else {
            mCurrentMoodTypeID--;
        }
        if (mCurrentMoodTypeID == 4) {
            mMediaPlayer.start();
        }
        changeMood();
    }

    private void changeMood() {
        setUI();
        playSound();
    }

    private void setCurrentMood() {
        Mood currentMood = mMoodHistory.loadCurrentMood();
        if (currentMood.getMoodID() == Constants.EMPTY_MOOD.getMoodID()) {
            currentMood = Constants.DEFAULT_MOOD;
        }
        mCurrentMoodTypeID = currentMood.getMoodID();
        mNote = currentMood.getNote();
        setUI();
    }

    private void setUI() {
        MoodType moodType = MoodHistory.getMoodTypeFromID(mCurrentMoodTypeID);
        background.setBackgroundColor(getResources().getColor(moodType.getBackgroundColor()));
        faceImage.setImageResource(moodType.getFaceImage());

    }

    private void playSound() {
        releaseMediaPlayer();
        switch (mCurrentMoodTypeID) {
            case 0:
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sad);
                break;
            case 1:
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.disappointed);
                break;
            case 2:
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.normal);
                break;
            case 3:
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.happy);
                break;
            case 4:
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.super_happy);
                break;
        }
        mMediaPlayer.start();
    }

    private void startAlarmManager() {
        Calendar midnight = Calendar.getInstance(); //gets right now
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.add(Calendar.DATE, 1);    //tomorrow

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmManagerReceiver.class);
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(getApplicationContext(), 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP
                , midnight.getTimeInMillis()
                , AlarmManager.INTERVAL_DAY
                , pendingIntent);
    }

    private void saveCurrentMood() {
        Mood mCurrentMood = new Mood(mCurrentMoodTypeID, mNote);
        mMoodHistory.saveCurrentMood(mCurrentMood);
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mMediaPlayer.release();
        }
    }

    @Override
    protected void onDestroy() {
        saveCurrentMood();
        releaseMediaPlayer();
        super.onDestroy();
    }
}

