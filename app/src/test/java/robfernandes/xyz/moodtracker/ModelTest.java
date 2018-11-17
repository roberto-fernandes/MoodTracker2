package robfernandes.xyz.moodtracker;

import org.junit.Test;

import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.Utils.MoodType;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ModelTest {

    @Test
    public void creatingMoodType_enterID_ReturnMoodTypeID() {
        int moodTypeID=3;
        MoodType moodType = new MoodType(0, 0,0,moodTypeID);

        assertEquals(moodTypeID, moodType.getMoodTypeID());
    }

    @Test
    public void moodHistory_numberOfMoodTypes_Returned5() {
        int expectedNumberOfMoodTypes =5;
        int actualNumberOfMoodTypes =MoodHistory.getMoodTypes().size();

        assertEquals(expectedNumberOfMoodTypes, actualNumberOfMoodTypes);
    }

    @Test
    public void getMoodTypeFromID_validID_getMoodType() {
        MoodHistory moodHistory = new MoodHistory();
        int id;

        id=0;
        assertEquals(id, moodHistory.getMoodTypeFromID(id).getMoodTypeID());
        id=2;
        assertEquals(id, moodHistory.getMoodTypeFromID(id).getMoodTypeID());
        id=4;
        assertEquals(id, moodHistory.getMoodTypeFromID(id).getMoodTypeID());
    }

    @Test
    public void getMoodTypeFromID_invalidID_getDefaultType() {
        MoodHistory moodHistory = new MoodHistory();
        int id;
        int defaultMoodTypeID = moodHistory.DEFAULT_DAY.getMoodType().getMoodTypeID();

        id=-1;

        assertEquals(defaultMoodTypeID, moodHistory.getMoodTypeFromID(id).getMoodTypeID());
    }
}