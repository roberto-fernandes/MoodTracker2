package robfernandes.xyz.moodtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import robfernandes.xyz.moodtracker.Model.Day;
import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.R;

public class MoodHistoryActivity extends AppCompatActivity {

    private MoodHistory mMoodHistory;
    private List<Day> mListOfDays;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);

        mMoodHistory=new MoodHistory();
        mListOfDays =mMoodHistory.loadMoodHistoryFromMemory();

        recyclerView = findViewById(R.id.activity_mood_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new RecyclerViewAdapter(this, mListOfDays);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
