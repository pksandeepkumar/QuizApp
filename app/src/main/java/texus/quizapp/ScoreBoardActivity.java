package texus.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

import texus.app.model.PieChartData;

public class ScoreBoardActivity extends BaseAppCompatActivity {

    private PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        setUpToolbar();
        initViews();

    }

    public void setUpToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.toolbar_quiz_activity);
            View customView = actionBar.getCustomView();
            if(customView != null) {
                TextView tvTitle = (TextView) customView.findViewById(R.id.tvTitle);
                tvTitle.setText("Score Board");
            }
        }
    }

    @Override
    public void initViews() {
        mPieChart = (PieChart) this.findViewById(R.id.pieChart);
        setValues(12, 34, 145);

    }


    public void setValues(int correct, int wrong, int unAnswered) {
        ArrayList<PieChartData> list = new ArrayList<PieChartData>();
        list.add( new PieChartData("", correct, ResourcesCompat.getColor(getResources(),
                R.color.ans_correct, null)));
        list.add( new PieChartData("", wrong, ResourcesCompat.getColor(getResources(),
                R.color.ans_wrong, null)));
        list.add( new PieChartData("", unAnswered, ResourcesCompat.getColor(getResources(),
                R.color.un_answered, null)));
        drawChartWithoutValue(mPieChart, "", list, true);

    }



    private void drawChartWithoutValue(PieChart chart,String chartName,
                                       ArrayList<PieChartData> objects,
                                       boolean animate) {
        if( chart == null || objects == null) return;
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for( PieChartData object : objects) {
            entries.add(new PieEntry(object.value, object.label));
            colors.add(object.color);
        }
        PieDataSet dataSet = new PieDataSet(entries, chartName);
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(0f);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.TRANSPARENT);


        //Set Chart description
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);




        chart.setData(data);
        chart.invalidate();

        if(animate) {
            chart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        }

        Legend legend = chart.getLegend();

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setTextSize(0f);

        legend.setXEntrySpace(0f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);
        legend.setFormSize(0f);
    }


}
