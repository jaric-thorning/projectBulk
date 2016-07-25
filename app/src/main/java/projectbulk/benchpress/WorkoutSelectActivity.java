package projectbulk.benchpress;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class WorkoutSelectActivity extends ListActivity {

    ArrayList<String> workoutsList = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    int clickCounter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_select);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, workoutsList);
        setListAdapter(adapter);

    }
    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        workoutsList.add("Clicked : "+clickCounter++);
        adapter.notifyDataSetChanged();

        Intent myIntent = new Intent(WorkoutSelectActivity.this, WorkoutAddActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        WorkoutSelectActivity.this.startActivity(myIntent);
    }

}
