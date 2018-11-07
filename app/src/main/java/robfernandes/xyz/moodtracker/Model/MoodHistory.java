package robfernandes.xyz.moodtracker.Model;

import java.util.ArrayList;
import java.util.List;

import robfernandes.xyz.moodtracker.R;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodHistory {
    private List<Day> moodHistory;
    private static final List<MoodType> sMoodTypes= new ArrayList<MoodType>() {{
        add(new MoodType(40, R.color.faded_red, R.drawable.smiley_sad));
        add(new MoodType(55, R.color.warm_grey, R.drawable.smiley_disappointed));
        add(new MoodType(70, R.color.cornflower_blue_65, R.drawable.smiley_normal));
        add(new MoodType(85, R.color.light_sage, R.drawable.smiley_happy));
        add(new MoodType(100, R.color.banana_yellow, R.drawable.smiley_super_happy));
    }};

    public MoodHistory() {
    }

    public static List<MoodType> getMoodTypes() {
        return sMoodTypes;
    }

    public List<Day> loadMoodHistoryFromMemory () {
        moodHistory = generateDumbData ();
        return moodHistory;
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
