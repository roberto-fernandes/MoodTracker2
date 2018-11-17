package robfernandes.xyz.moodtracker.Model;

/**
 * Created by Roberto Fernandes on 17/11/2018.
 */
public class Mood {
    private int moodID;
    private String Note;

    public Mood(int moodID, String note) {
        this.moodID = moodID;
        Note = note;
    }

    public int getMoodID() {
        return moodID;
    }

    public String getNote() {
        return Note;
    }
}
