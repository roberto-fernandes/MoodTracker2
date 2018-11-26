package robfernandes.xyz.moodtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import robfernandes.xyz.moodtracker.Model.Mood;
import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.R;

public class MoodHistoryActivity extends AppCompatActivity {

    private MoodHistory mMoodHistory;
    private List<Mood> mMoodList;
    private RecyclerView recyclerView;
    private MoodHistoryAdapter mMoodHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);

        mMoodHistory = new MoodHistory(this);
        mMoodList = mMoodHistory.loadHistoryFromMemory();

        recyclerView = findViewById(R.id.activity_mood_recycler_view);
        recyclerView.setHasFixedSize(true);
        mMoodHistoryAdapter = new MoodHistoryAdapter(this, mMoodList);
        recyclerView.setAdapter(mMoodHistoryAdapter);
    }
}
