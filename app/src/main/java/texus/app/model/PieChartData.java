package texus.app.model;

import android.graphics.Color;

/**
 * Created by sandeep on 2/9/2017.
 */

public class PieChartData {

    public static final int COLOR_RED = Color.parseColor("#F44336");
    public static final int DEEP_PURPLE = Color.parseColor("#673AB7");
    public static final int INDIGO = Color.parseColor("#3F51B5");
    public static final int TEAL = Color.parseColor("#009688");
    public static final int GREEN = Color.parseColor("#4CAF50");
    public static final int BROWN = Color.parseColor("#795548");
    public static final int BLUE_GREY = Color.parseColor("#607D8B");

    public static final int INVALID_GREY = Color.parseColor("#e0e0e0");

    public PieChartData(String label, float value, int color ) {
        this.label = label;
        this.value = value;
        this.color = color;
    }

    public String label;
    public float value;
    public int color;


}
