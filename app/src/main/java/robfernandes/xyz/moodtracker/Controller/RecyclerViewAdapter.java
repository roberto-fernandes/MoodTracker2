package robfernandes.xyz.moodtracker.Controller;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
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

import robfernandes.xyz.moodtracker.Model.Day;
import robfernandes.xyz.moodtracker.Model.MoodHistory;
import robfernandes.xyz.moodtracker.R;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<Day> mDayList;

    public RecyclerViewAdapter(Context context, List<Day> listMoods) {
        mContext = context;
        mDayList = listMoods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mood_history_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mDayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView noteImage;
        private View rowCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.mood_history_row_title_textView);
            noteImage = itemView.findViewById(R.id.mood_history_row_note_imageView);
            rowCardView = itemView.findViewById(R.id.mood_history_row_cardiView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            Day day = mDayList.get(position);
            if (day.hasNote()) {
                Toast.makeText(mContext, day.getNote(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Day day = mDayList.get(i);
        int numberOfDaysAgo= mDayList.size()-i;
        String text;

        switch (numberOfDaysAgo) {
            case 7: text = mContext.getString(R.string.OneWeekAgo);
                    break;
            case 6: text = mContext.getString(R.string.SixDaysAgo);
                break;
            case 5: text = mContext.getString(R.string.FiveDaysAgo);
                break;
            case 4: text = mContext.getString(R.string.FourDaysAgo);
                break;
            case 3: text = mContext.getString(R.string.ThreeDaysAgo);
                break;
            case 2: text = mContext.getString(R.string.TwoDaysAgo);
                break;
            case 1: text = mContext.getString(R.string.Yesterday);
                break;
            default: text = mContext.getString(R.string.OneWeekAgo);
                break;
        }

        viewHolder.title.setText(text);
        if (day.hasNote()) {
            viewHolder.noteImage.setVisibility(View.VISIBLE);
        } else {
            viewHolder.noteImage.setVisibility(View.INVISIBLE);
        }
        //set the background color
        viewHolder.rowCardView.setBackgroundColor(mContext.getResources().getColor(day.getMoodType().getBackgroundColor()));
        // set the width and height
        viewHolder.rowCardView.getLayoutParams().width=getItemWidth(day.getMoodType().getWidthPercentage());
        viewHolder.rowCardView.getLayoutParams().height=getItemHeight();
    }

    private int getItemWidth (int percentage) {
        int width;
        Point size = getScreenSize ();
        width = size.x*percentage/100;
        return width;
    }

    private int getItemHeight () {
        int height;
        Point size = getScreenSize ();
        //the recyclerView height is the full height minus the status bar height and then for each item it is divided for the number of items
        height = (size.y-geStatusBarHeight()) /MoodHistory.MAX_NUM_OF_DAYS;
        return height;
    }

    private Point getScreenSize () {
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
