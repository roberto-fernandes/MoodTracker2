package robfernandes.xyz.moodtracker.Model;

import java.util.ArrayList;
import java.util.List;

import robfernandes.xyz.moodtracker.R;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodHistory {
    private List<MoodType> moodTypes;
    private List<MoodDay> moodHistory;

    public MoodHistory() {
        moodTypes = new ArrayList<>();
        moodTypes.add(new MoodType(20, R.color.faded_red, R.drawable.smiley_sad));
        moodTypes.add(new MoodType(40, R.color.warm_grey, R.drawable.smiley_disappointed));
        moodTypes.add(new MoodType(60, R.color.cornflower_blue_65, R.drawable.smiley_normal));
        moodTypes.add(new MoodType(80, R.color.light_sage, R.drawable.smiley_happy));
        moodTypes.add(new MoodType(100, R.color.banana_yellow, R.drawable.smiley_super_happy));
    }

    public List<MoodType> getMoodTypes() {
        return moodTypes;
    }

    public List<MoodDay> getMoodHistory() {
        return moodHistory;
    }

    public List<MoodDay> loadMoodHistoryFromMemory () {
        moodHistory = generateDumbData ();
        return moodHistory;
    }

    private List<MoodDay> generateDumbData () {
        List<MoodDay> moodDayList = new ArrayList<>();

        moodDayList.add(new MoodDay(moodTypes.get(0), "Some note"));
        moodDayList.add(new MoodDay(moodTypes.get(1)));
        moodDayList.add(new MoodDay(moodTypes.get(2), "Another note"));
        moodDayList.add(new MoodDay(moodTypes.get(3)));
        moodDayList.add(new MoodDay(moodTypes.get(4)));
        moodDayList.add(new MoodDay(moodTypes.get(2), "Some other note"));
        moodDayList.add(new MoodDay(moodTypes.get(0)));
        return moodDayList;
    }
}
