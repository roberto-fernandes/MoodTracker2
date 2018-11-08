package robfernandes.xyz.moodtracker.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import robfernandes.xyz.moodtracker.Model.Day;
import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.Model.MoodType;
import robfernandes.xyz.moodtracker.R;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

        private ImageView addNoteImage;
        private ImageView moodHistoryImage;
        private ImageView faceImage;
        private View background;

        private GestureDetector mGestureDetector;
        private List<MoodType> mMoodTypes;
        private Day mLastDay;
        private int mCurrentMoodTypeID;
        private MoodHistory mMoodHistory;
        private String mNote;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mGestureDetector = new GestureDetector(this, this   );
            findViewById(android.R.id.content).setOnTouchListener(this);
            mMoodHistory = new MoodHistory(this);
            mMoodTypes = MoodHistory.getMoodTypes();
            mLastDay = mMoodHistory.loadCurrentDay();
            mCurrentMoodTypeID =mLastDay.getMoodType().getMoodTypeID();
            mNote = mLastDay.getNote();


            addNoteImage = findViewById(R.id.activity_main_note_image);
            moodHistoryImage = findViewById(R.id.activity_main_history_image);
            background  = findViewById(R.id.activity_main_background);
            faceImage = findViewById(R.id.activity_main_face_image);

            setUI();

            //show alert dialog
            addNoteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View mAlertDialogView =inflater.inflate(R.layout.alert_dialog, null);
                final EditText alertDialogNote = mAlertDialogView.findViewById(R.id.alert_dialog_note_EditText);
                //If there is a note it shows
                if (!alertDialogNote.getText().equals("")) {
                    alertDialogNote.setText(mNote);
                }
                // Inflate and set the layout for the dialog
                builder.setView(mAlertDialogView)
                        // Add action buttons
                        .setPositiveButton(R.string.Add_note, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //add note
                                mNote=alertDialogNote.getText().toString();
                            }
                        })
                        .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create().show();
            }
        });

        moodHistoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoodHistoryActivity.class);
                startActivity(intent);
            }
        });

        }

        //region detection sliding up and down
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
            double mDistance = e1.getY()-e2.getY();
            if(mDistance>100) {
                nextMood ();
            } else if(mDistance<-100) {
                previousMood ();
            }
            return true;
        }
        //endregion

        private void nextMood () {
            int lastItem=mMoodTypes.size()-1;
            if (mCurrentMoodTypeID >=lastItem) {
                mCurrentMoodTypeID =0;
            }
            else {
                mCurrentMoodTypeID++;
            }
            setUI();
        }

        private void previousMood() {
            int lastItem=mMoodTypes.size()-1;
            if (mCurrentMoodTypeID <=0) {
                mCurrentMoodTypeID =lastItem;
            }
            else {
                mCurrentMoodTypeID--;
            }
            setUI();
        }

        private void setUI() {
            MoodType moodType = mMoodTypes.get(mCurrentMoodTypeID);

            background.setBackgroundColor(getResources().getColor(moodType.getBackgroundColor()));
            faceImage.setImageResource(moodType.getFaceImage());
        }

    @Override
    protected void onDestroy() {
        Day mCurrentDay = new Day(mMoodTypes.get(mCurrentMoodTypeID), mNote)  ;
        mMoodHistory.saveCurrentDay(mCurrentDay);
        super.onDestroy();
    }
}

