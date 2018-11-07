package robfernandes.xyz.moodtracker.Controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import robfernandes.xyz.moodtracker.R;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

        private ImageView addNoteImage;

        private GestureDetector mGestureDetector;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mGestureDetector = new GestureDetector(this, this   );

            findViewById(android.R.id.content).setOnTouchListener(this);

            addNoteImage = findViewById(R.id.activity_main_note_image);

            //show alert dialog
            addNoteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View mView =inflater.inflate(R.layout.alert_dialog, null);
                final EditText alertDialogNote = mView.findViewById(R.id.alert_dialog_note_EditText);
                //Reminder: If there is a note alertDialogNote.setText(note) here;

                // Inflate and set the layout for the dialog
                builder.setView(mView)
                        // Add action buttons
                        .setPositiveButton(R.string.Add_note, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // store note to memory...
                            }
                        })
                        .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.create().show();
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

        }

        private void previousMood() {

        }
}

