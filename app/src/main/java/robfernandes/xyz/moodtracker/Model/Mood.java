package robfernandes.xyz.moodtracker.Model;

/**
 * Created by Roberto Fernandes on 17/11/2018.
 */
public class Mood {
    private int moodID;
    private String note;

    public Mood(int moodID, String note) {
        this.moodID = moodID;
        this.note = note;
    }


    public int getMoodID() {
        return moodID;
    }

    public String getNote() {
        return note;
    }

    public boolean hasNote() {
        if (!note.equals("")) {
            return true;
        }
        return false;
    }
}
