package robfernandes.xyz.moodtracker.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import robfernandes.xyz.moodtracker.Utils.Constants;
import robfernandes.xyz.moodtracker.Utils.MoodType;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodHistory {

    private Context context;
    private List<Mood> moodHistory = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public MoodHistory() {
    }

    public MoodHistory(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        moodHistory = loadHistoryFromMemory();
    }

    public static List<MoodType> getMoodTypes() {
        return Constants.MOOD_TYPES;
    }

    public void updateDays() {
        Mood currentMood = loadCurrentMood();
        if (moodHistory.size() >= Constants.MAX_NUM_OF_DAYS) {
            moodHistory.remove(0);
        }
        moodHistory.add(currentMood);
        saveHistoryToMemory();
        saveCurrentMood(Constants.DEFAULT_MOOD);
    }

    private void saveHistoryToMemory() {
        for (int i = 0; i < moodHistory.size(); i++) {
            saveMoodToMemory(moodHistory.get(i), i);
        }
    }

    public List<Mood> loadHistoryFromMemory() {
        List<Mood> moodList = new ArrayList<>();
        Mood mood;
        for (int i = 0; i < Constants.MAX_NUM_OF_DAYS; i++) {
            mood = loadMoodFromMemory(i);
            if (mood != null) {
                moodList.add(mood);
            }
        }
        return moodList;
    }

    public void saveCurrentMood(Mood mood) {
        saveMoodToMemory(mood, Constants.CURRENT_DAY_INDEX);
    }

    public Mood loadCurrentMood() {
        Mood mood = loadMoodFromMemory(Constants.CURRENT_DAY_INDEX);
        if (mood == null) {
            mood = Constants.DEFAULT_MOOD ;
        }
        return mood;
    }

    private void saveMoodToMemory(Mood mood, int index) {
        saveMoodIDToMemory(index, mood.getMoodID());
        saveNoteToMemory(index, mood.getNote());
    }


    private Mood loadMoodFromMemory(int index) {
        int moodTypeID;
        String note;
        String key = Constants.MOOD_STRING_KEY + index;

        moodTypeID = Integer.valueOf(sharedPreferences.getString(key, "-1"));
        if (moodTypeID == -1) {
            return null;
        }

        note = getNoteFromMemory(index);

        Mood mood = new Mood(moodTypeID, note);
        return mood;
    }

    public static MoodType getMoodTypeFromID(int id) {
        for (MoodType moodType : Constants.MOOD_TYPES) {
            if (moodType.getMoodTypeID() == id) {
                return moodType;
            }
        }
        return Constants.DEFAULT_MOOD_TYPE;
    }

    private String getNoteFromMemory(int index) {
        String note;
        String key = Constants.NOTE_STRING_KEY + index;
        note = sharedPreferences.getString(key, "");
        return note;
    }

    private void saveMoodIDToMemory(int index, int moodID) {
        String key = Constants.MOOD_STRING_KEY + index;
        String moodIDString = Integer.toString(moodID);
        saveDataInMemory(key, moodIDString);
    }

    private void saveNoteToMemory(int index, String note) {
        String key = Constants.NOTE_STRING_KEY + index;
        saveDataInMemory(key, note);
    }

    private void saveDataInMemory(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
