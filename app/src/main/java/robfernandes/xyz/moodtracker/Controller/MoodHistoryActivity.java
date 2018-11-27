package robfernandes.xyz.moodtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import robfernandes.xyz.moodtracker.Model.Mood;
import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.R;

public class MoodHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);

        MoodHistory moodHistory = new MoodHistory(this);
        List<Mood> moodList = moodHistory.loadHistoryFromMemory();

        RecyclerView recyclerView = findViewById(R.id.activity_mood_recycler_view);
        recyclerView.setHasFixedSize(true);
        MoodHistoryAdapter moodHistoryAdapter = new MoodHistoryAdapter(this, moodList);
        recyclerView.setAdapter(moodHistoryAdapter);
    }
}
