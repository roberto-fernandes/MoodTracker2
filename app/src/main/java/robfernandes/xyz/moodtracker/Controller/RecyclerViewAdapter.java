package robfernandes.xyz.moodtracker.Controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import robfernandes.xyz.moodtracker.Model.MoodDay;
import robfernandes.xyz.moodtracker.R;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<MoodDay> mMoodDayList;

    public RecyclerViewAdapter(Context context, List<MoodDay> listMoods) {
        mContext = context;
        mMoodDayList = listMoods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mood_history_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mMoodDayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView noteImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.mood_history_row_title_textView);
            noteImage = itemView.findViewById(R.id.mood_history_row_note_imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            MoodDay moodDay = mMoodDayList.get(position);

            //display toast
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MoodDay moodDay = mMoodDayList.get(i);
        int numberOfDaysAgo=mMoodDayList.size()-i;
        String text="title";
        viewHolder.title.setText(text);
    }


}
