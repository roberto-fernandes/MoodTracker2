package robfernandes.xyz.moodtracker.Utils;

import java.util.ArrayList;
import java.util.List;

import robfernandes.xyz.moodtracker.Model.Mood;
import robfernandes.xyz.moodtracker.R;

/**
 * Created by Roberto Fernandes on 17/11/2018.
 */
public final class Constants {
    public static final List<MoodType> MOOD_TYPES = new ArrayList<MoodType>() {{
        add(new MoodType(40, R.color.faded_red, R.drawable.smiley_sad, 0));
        add(new MoodType(55, R.color.warm_grey, R.drawable.smiley_disappointed, 1));
        add(new MoodType(70, R.color.cornflower_blue_65, R.drawable.smiley_normal, 2));
        add(new MoodType(85, R.color.light_sage, R.drawable.smiley_happy, 3));
        add(new MoodType(100, R.color.banana_yellow, R.drawable.smiley_super_happy, 4));
    }};
    public static final MoodType DEFAULT_MOOD_TYPE = MOOD_TYPES.get(4);
    public static final MoodType EMPTY_MOOD_TYPE = new MoodType(Integer.MAX_VALUE, 100, R.color.backgroundHistoryMood);
    public static final Mood EMPTY_MOOD = new Mood(EMPTY_MOOD_TYPE.getMoodTypeID(), "");
    public static final Mood DEFAULT_MOOD = new Mood(DEFAULT_MOOD_TYPE.getMoodTypeID(), "");
    public static final int MAX_NUM_OF_DAYS = 7;
    public static final String MOOD_STRING_KEY = "Mood";
    public static final String NOTE_STRING_KEY = "Note";
    public static final int CURRENT_DAY_INDEX = Integer.MIN_VALUE;

    private Constants() {
    }
}
