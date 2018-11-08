package robfernandes.xyz.moodtracker.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import robfernandes.xyz.moodtracker.R;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodHistory {
    private List<Day> moodHistory;
    private Context context;
    private static final List<MoodType> sMoodTypes= new ArrayList<MoodType>() {{
        add(new MoodType(40, R.color.faded_red, R.drawable.smiley_sad, 0));
        add(new MoodType(55, R.color.warm_grey, R.drawable.smiley_disappointed, 1));
        add(new MoodType(70, R.color.cornflower_blue_65, R.drawable.smiley_normal, 2));
        add(new MoodType(85, R.color.light_sage, R.drawable.smiley_happy, 3));
        add(new MoodType(100, R.color.banana_yellow, R.drawable.smiley_super_happy, 4));
    }};
    private SharedPreferences sharedPreferences;
    private static final Day sDefaultDay = new Day(sMoodTypes.get(4));

    public MoodHistory(Context context) {
        this.context=context;
        sharedPreferences= context.getSharedPreferences("Data", Context.MODE_PRIVATE);
    }

    public static List<MoodType> getMoodTypes() {
        return sMoodTypes;
    }



    public void saveDayHistoryToMemory () {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(moodHistory);
        prefsEditor.putString("DayHistory", json);
        prefsEditor.apply();
    }

    public List<Day> loadDayHistoryFromMemory() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("DayHistory", null);

        //if there is data loads data from memory
        if (json!=null) {
            Type type = new TypeToken<ArrayList<Day>>() {}.getType();
            moodHistory = gson.fromJson(json, type);
        } else { //if the data is empty generates dumb data
            moodHistory = generateDumbData();
        }
        return moodHistory;
    }


    public void saveCurrentDay (Day day) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(day);
        prefsEditor.putString("CurrentDay", json);
        prefsEditor.apply();
    }

    public Day loadCurrentDay () {
        Day day;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("CurrentDay", null);
        if (json != null) {
            day = gson.fromJson(json, Day.class);
        } else {
            day = sDefaultDay;
        }
        return day;
    }


    private List<Day> generateDumbData () {
        List<Day> dayList = new ArrayList<>();

        dayList.add(new Day(sMoodTypes.get(0), "Some note"));
        dayList.add(new Day(sMoodTypes.get(1)));
        dayList.add(new Day(sMoodTypes.get(2), "Another note"));
        dayList.add(new Day(sMoodTypes.get(3)));
        dayList.add(new Day(sMoodTypes.get(4)));
        dayList.add(new Day(sMoodTypes.get(2), "Some other note"));
        dayList.add(new Day(sMoodTypes.get(0)));
        return dayList;
    }
}
