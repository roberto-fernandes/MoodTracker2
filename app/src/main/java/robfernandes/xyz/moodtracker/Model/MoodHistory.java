package robfernandes.xyz.moodtracker.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import robfernandes.xyz.moodtracker.R;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodHistory {
    private static final List<MoodType> MOOD_TYPES = new ArrayList<MoodType>() {{
        add(new MoodType(40, R.color.faded_red, R.drawable.smiley_sad, 0));
        add(new MoodType(55, R.color.warm_grey, R.drawable.smiley_disappointed, 1));
        add(new MoodType(70, R.color.cornflower_blue_65, R.drawable.smiley_normal, 2));
        add(new MoodType(85, R.color.light_sage, R.drawable.smiley_happy, 3));
        add(new MoodType(100, R.color.banana_yellow, R.drawable.smiley_super_happy, 4));
    }};
    public static final Day DEFAULT_DAY = new Day(MOOD_TYPES.get(4));
    public static final int MAX_NUM_OF_DAYS =7;
    private static final String MOOD_STRING_KEY = "Mood";
    private static final String NOTE_STRING_KEY = "Note";
    public static final int CURRENT_DAY_INDEX = -47;

    private Context context;
    private List<Day> dayHistory = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public MoodHistory() {
    }

    public MoodHistory(Context context) {
        this.context=context;
        sharedPreferences= context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        dayHistory= loadHistoryFromMemory();
    }

    public static List<MoodType> getMoodTypes() {
        return MOOD_TYPES;
    }


    public void updateDays () {
        Day currentDay =loadCurrentDay();
        if (dayHistory.size()>= MAX_NUM_OF_DAYS) {
            dayHistory.remove(0);
        }
        dayHistory.add(currentDay);
        saveHistoryToMemory();
        saveCurrentDay(DEFAULT_DAY);
    }

    private void saveHistoryToMemory() {
        while (dayHistory.size()>MAX_NUM_OF_DAYS) {
            dayHistory.remove(0);
        }
        for (int i=0; i<dayHistory.size(); i++) {
            saveDayToMemory(dayHistory.get(i), i);
        }
    }


    public List<Day> loadHistoryFromMemory() {
        List<Day> listDays = new ArrayList<>();
        Day day;
        for (int i=0; i<MAX_NUM_OF_DAYS;i++) {
            day = loadDayFromMemory(i);
            if (day!=null) {
                listDays.add(day);
            }
        }
        return listDays;
    }



    public void saveCurrentDay (Day day) {
        saveDayToMemory(day, CURRENT_DAY_INDEX);
    }

    public Day loadCurrentDay () {
        Day day = loadDayFromMemory(CURRENT_DAY_INDEX);
        if (day==null) {
            day=DEFAULT_DAY;
        }
        return day;
    }

    private void saveDayToMemory (Day day, int index) {
        saveMoodToMemory(index, day.getMoodType().getMoodTypeID());
        saveNoteToMemory(index, day.getNote());
    }


    private Day loadDayFromMemory (int index) {
        int moodTypeID;
        String note;
        String key =MOOD_STRING_KEY+index;

        moodTypeID= sharedPreferences.getInt(key, -1);

        if (moodTypeID == -1) {
            return null;
        }

        MoodType moodType = getMoodTypeFromID(moodTypeID);
        note = getNoteFromMemory(index);

        Day day = new Day(moodType, note);

        return day;
    }

    public MoodType getMoodTypeFromID (int id) {
        for (MoodType moodType: MOOD_TYPES) {
            if (moodType.getMoodTypeID()==id) {
                return moodType;
            }
        }
        return DEFAULT_DAY.getMoodType();
    }

    private String getNoteFromMemory (int index) {
        String note;
        String key=NOTE_STRING_KEY+index;
        note=sharedPreferences.getString(key, "");
        return note;
    }


    private void saveMoodToMemory (int index, int moodID) {
        String key= MOOD_STRING_KEY + index;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, moodID);
        editor.apply();
    }

    private void saveNoteToMemory (int index, String note) {
        String key= NOTE_STRING_KEY + index;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, note);
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
