package robfernandes.xyz.moodtracker;

import org.junit.Test;

import robfernandes.xyz.moodtracker.Model.MoodHistory;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ModelTest {

    @Test
    public void numberOfMoodTypes() {
        int expectedNumberOfMoodTypes =5;
        int actualNumberOfMoodTypes =MoodHistory.getMoodTypes().size();

        assertEquals(expectedNumberOfMoodTypes, actualNumberOfMoodTypes);
    }

}