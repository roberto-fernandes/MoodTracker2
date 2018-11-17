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
    private List<Day> dayHistory = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public MoodHistory() {
    }

    public MoodHistory(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        dayHistory = loadHistoryFromMemory();
    }

    public static List<MoodType> getMoodTypes() {
        return Constants.MOOD_TYPES;
    }

    public void updateDays() {
        Day currentDay = loadCurrentDay();
        if (dayHistory.size() >= Constants.MAX_NUM_OF_DAYS) {
            dayHistory.remove(0);
        }
        dayHistory.add(currentDay);
        saveHistoryToMemory();
        saveCurrentDay(Constants.DEFAULT_DAY);
    }

    private void saveHistoryToMemory() {
        for (int i = 0; i < dayHistory.size(); i++) {
            saveDayToMemory(dayHistory.get(i), i);
        }
    }

    public List<Day> loadHistoryFromMemory() {
        List<Day> listDays = new ArrayList<>();
        Day day;
        for (int i = 0; i < Constants.MAX_NUM_OF_DAYS; i++) {
            day = loadDayFromMemory(i);
            if (day != null) {
                listDays.add(day);
            }
        }
        return listDays;
    }

    public void saveCurrentDay(Day day) {
        saveDayToMemory(day, Constants.CURRENT_DAY_INDEX);
    }

    public Day loadCurrentDay() {
        Day day = loadDayFromMemory(Constants.CURRENT_DAY_INDEX);
        if (day == null) {
            day = Constants.DEFAULT_DAY;
        }
        return day;
    }

    private void saveDayToMemory(Day day, int index) {
        saveMoodToMemory(index, day.getMoodType().getMoodTypeID());
        saveNoteToMemory(index, day.getNote());
    }


    private Day loadDayFromMemory(int index) {
        int moodTypeID;
        String note;
        String key = Constants.MOOD_STRING_KEY + index;

        moodTypeID = Integer.valueOf(sharedPreferences.getString(key, "-1"));
        if (moodTypeID == -1) {
            return null;
        }

        MoodType moodType = getMoodTypeFromID(moodTypeID);
        note = getNoteFromMemory(index);

        Day day = new Day(moodType, note);

        return day;
    }

    public MoodType getMoodTypeFromID(int id) {
        for (MoodType moodType : Constants.MOOD_TYPES) {
            if (moodType.getMoodTypeID() == id) {
                return moodType;
            }
        }
        return Constants.DEFAULT_DAY.getMoodType();
    }

    private String getNoteFromMemory(int index) {
        String note;
        String key = Constants.NOTE_STRING_KEY + index;
        note = sharedPreferences.getString(key, "");
        return note;
    }

    private void saveMoodToMemory(int index, int moodID) {
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

/*//just for test
    private List<Day> generateDumbData () {
        List<Day> dayList = new ArrayList<>();

        dayList.add(new Day(MOOD_TYPES.get(0), "Just some data example"));
        dayList.add(new Day(MOOD_TYPES.get(1)));
        dayList.add(new Day(MOOD_TYPES.get(2), "Another note"));
        dayList.add(new Day(MOOD_TYPES.get(3)));
        dayList.add(new Day(MOOD_TYPES.get(4)));
        dayList.add(new Day(MOOD_TYPES.get(2), "Some other note"));
        dayList.add(new Day(MOOD_TYPES.get(0)));
        return dayList;
    }*/
}
