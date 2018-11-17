package robfernandes.xyz.moodtracker.Model;

import robfernandes.xyz.moodtracker.Utils.MoodType;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class Day {
    private MoodType moodType;
    private String note;

    public Day(MoodType moodType) {
        this.moodType = moodType;
        setNote("");
    }

    public Day(MoodType moodType, String note) {
        this(moodType);
        this.note = note;
    }

    public MoodType getMoodType() {
        return moodType;
    }

    public boolean hasNote() {
        if (note.equals("")) {
            return false;
        }

        return true;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
