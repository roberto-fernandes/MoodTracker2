package robfernandes.xyz.moodtracker.Model;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodDay {
    private MoodType moodType;
    private String note;

    public MoodDay(MoodType moodType) {
        this.moodType = moodType;
        setNote("");
    }

    public MoodDay(MoodType moodType, String note) {
        this(moodType);
        this.note = note;
    }

    public MoodType getMoodType() {
        return moodType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
