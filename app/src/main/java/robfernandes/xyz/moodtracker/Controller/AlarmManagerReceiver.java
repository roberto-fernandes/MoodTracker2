package robfernandes.xyz.moodtracker.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import robfernandes.xyz.moodtracker.Model.MoodHistory;

/**
 * Created by Roberto Fernandes on 08/11/2018.
 */
public class AlarmManagerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MoodHistory moodHistory = new MoodHistory(context);
        moodHistory.updateDays();
    }
}
