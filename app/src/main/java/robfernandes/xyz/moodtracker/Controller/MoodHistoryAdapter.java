package robfernandes.xyz.moodtracker.Controller;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import robfernandes.xyz.moodtracker.Model.Mood;
import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.R;
import robfernandes.xyz.moodtracker.Utils.Constants;
import robfernandes.xyz.moodtracker.Utils.MoodType;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodHistoryAdapter extends RecyclerView.Adapter<MoodHistoryAdapter.ViewHolder> {
    private Context mContext;
    private List<Mood> mMoodList;

    public MoodHistoryAdapter(Context context, List<Mood> moodList) {
        mContext = context;
        mMoodList = moodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mood_history_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mMoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView noteImage;
        private View rowCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.mood_history_row_title_text_view);
            noteImage = itemView.findViewById(R.id.mood_history_row_note_image_view);
            rowCardView = itemView.findViewById(R.id.mood_history_row_card_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            Mood mood = mMoodList.get(position);
            if (mood.hasNote()) {
                Toast.makeText(mContext, mood.getNote(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Mood mood = mMoodList.get(i);
        int numberOfDaysAgo = mMoodList.size() - i;
        String text;

        switch (numberOfDaysAgo) {
            case 7:
                text = mContext.getString(R.string.one_week_ago);
                break;
            case 6:
                text = mContext.getString(R.string.six_days_ago);
                break;
            case 5:
                text = mContext.getString(R.string.five_days_ago);
                break;
            case 4:
                text = mContext.getString(R.string.four_days_ago);
                break;
            case 3:
                text = mContext.getString(R.string.three_days_ago);
                break;
            case 2:
                text = mContext.getString(R.string.two_days_ago);
                break;
            case 1:
                text = mContext.getString(R.string.yesterday);
                break;
            default:
                text = mContext.getString(R.string.one_week_ago);
                break;
        }

        MoodType moodType = MoodHistory.getMoodTypeFromID(mood.getMoodID());
        if (moodType.getMoodTypeID() == Constants.EMPTY_MOOD_TYPE.getMoodTypeID()) {
            text += ", no entry on this day";
        }

        viewHolder.title.setText(text);
        if (mood.hasNote()) {
            viewHolder.noteImage.setVisibility(View.VISIBLE);
        } else {
            viewHolder.noteImage.setVisibility(View.INVISIBLE);
        }

        setMoodBackground(viewHolder, moodType);
        setMoodColor(viewHolder, moodType);
    }

    private void setMoodColor(@Nullable ViewHolder viewHolder, MoodType moodType) {
        int width = moodType.getWidthPercentage();
        viewHolder.rowCardView.getLayoutParams().width = getItemWidth(width);
        viewHolder.rowCardView.getLayoutParams().height = getItemHeight();
    }

    private void setMoodBackground(@Nullable ViewHolder viewHolder, MoodType moodType) {
        int backgroundColor = moodType.getBackgroundColor();
        viewHolder.rowCardView
                .setBackgroundColor(mContext.getResources()
                        .getColor(backgroundColor));
    }

    private int getItemWidth(int percentage) {
        int width;
        Point size = getScreenSize();
        width = size.x * percentage / 100;
        return width;
    }

    private int getItemHeight() {
        int height;
        Point size = getScreenSize();
        height = (size.y - geStatusBarHeight()) / Constants.MAX_NUM_OF_DAYS;
        return height;
    }

    private Point getScreenSize() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }

    private int geStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

}
