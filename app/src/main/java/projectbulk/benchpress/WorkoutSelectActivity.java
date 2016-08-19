package projectbulk.benchpress;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Workout;
import model.WorkoutReader;

public class WorkoutSelectActivity extends ListActivity {

    ArrayList<String> workoutsList = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    WorkoutReader workoutReader = new WorkoutReader();

    int clickCounter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_select);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, workoutsList);
        setListAdapter(adapter);

        workoutReader.loadWorkouts(this);



    }
    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        Intent myIntent = new Intent(WorkoutSelectActivity.this, WorkoutAddActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        WorkoutSelectActivity.this.startActivityForResult(myIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_CANCELED) {
                // user pressed back from 2nd activity to go to 1st activity. code here
                System.out.println("Result Cancelled.");
            }
            else if (resultCode == 1){
                System.out.println("Detected return result from workout add");
                workoutsList.clear();
                workoutReader.loadWorkouts(this);
                List<Workout> workouts = workoutReader.getWorkouts();
                System.out.println("Size: " + workouts.size());

                for(int i = 0; i < workouts.size(); i++){
                    workoutsList.add(workouts.get(i).getName());
                    System.out.println("Adding to list: " + workouts.get(i).getName());
                }
                //workoutsList.add("Clicked : "+clickCounter++);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
