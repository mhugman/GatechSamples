package edu.gatech.seclass.sdpscramble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScrambleStatsActivity extends NavigationActivity {

    private TableLayout mainTableScramble;

    List<ScrambleStatistics> scrambleStatList = new ArrayList<ScrambleStatistics>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scramble_stats);

        Player currentPlayer = GameManager.getInstance().getCurrentPlayer();

        scrambleStatList = StatisticsProcessor.getScrambleStatistics();

        mainTableScramble = (TableLayout)findViewById(R.id.mainTableScramble);

        for (int i = 0; i < scrambleStatList.size(); i++){

            ScrambleStatistics scrambleStat = scrambleStatList.get(i);

            TableRow newRow = new TableRow(this);

            TextView scrambleId = new TextView(this);
            TextView wasSolved = new TextView(this);
            TextView wasCreated = new TextView(this);
            TextView timesSolved = new TextView(this);

            scrambleId.setId(View.generateViewId());
            wasSolved.setId(View.generateViewId());
            wasCreated.setId(View.generateViewId());
            timesSolved.setId(View.generateViewId());

            newRow.addView(scrambleId);
            newRow.addView(wasSolved);
            newRow.addView(wasCreated);
            newRow.addView(timesSolved);

            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            scrambleId.setLayoutParams(params);
            wasSolved.setLayoutParams(params);
            wasCreated.setLayoutParams(params);
            timesSolved.setLayoutParams(params);

            scrambleId.setGravity(Gravity.CENTER);
            wasSolved.setGravity(Gravity.CENTER);
            wasCreated.setGravity(Gravity.CENTER);
            timesSolved.setGravity(Gravity.CENTER);

            scrambleId.setText(scrambleStat.getScrambleId());

            if (scrambleStat.playerSolvedScramble(currentPlayer)) {
                wasSolved.setText("Solved");
            }
            if (scrambleStat.playerAddedScramble(currentPlayer)) {
                wasCreated.setText("Added");
            }

            timesSolved.setText(String.valueOf(scrambleStat.getTimesSolved()));

            mainTableScramble.addView(newRow);

        }

    }
}
