package robfernandes.xyz.moodtracker.Model;

/**
 * Created by Roberto Fernandes on 07/11/2018.
 */
public class MoodType {

    private int widthPercentage;
    private int backgroundColor;
    private int faceImage;

    public MoodType(int widthPercentage, int backgroundColor, int faceImage) {
        this.widthPercentage = widthPercentage;
        this.backgroundColor = backgroundColor;
        this.faceImage = faceImage;
    }


    public int getWidthPercentage() {
        return widthPercentage;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getFaceImage() {
        return faceImage;
    }
}
