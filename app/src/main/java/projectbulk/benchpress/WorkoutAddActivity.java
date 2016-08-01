package projectbulk.benchpress;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import model.Exercise;
import model.ExerciseReader;
import model.Workout;

public class WorkoutAddActivity extends ListActivity {

    ArrayList<String> exerciseList = new ArrayList<String>();

    ArrayAdapter<String> activity_adapter;
    ArrayAdapter<String> spinner_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_add);

        final Button button = (Button) findViewById(R.id.addExerciseBtn);
        final Workout new_workout = new Workout();
        ExerciseReader exerciseReader = new ExerciseReader();
        Set<Exercise> exercise_list = null;

        activity_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exerciseList);
        setListAdapter(activity_adapter);

        try {
            exercise_list = exerciseReader.read(this, R.raw.exercise_definitions);
        }
        catch (Exception e) {
            System.out.println("FAILED TO LOAD EXERCISES" + e);
        }

        final Spinner spinner = (Spinner) findViewById(R.id.new_exercise_spinner);

        if(exercise_list != null) {
            final List<String> list=new ArrayList<String>();

            for (Exercise e : exercise_list) {
                System.out.println(e);
                list.add(e.toString());

            }
            Collections.sort(list);
            // Create an ArrayAdapter using the string array and a default spinner layout
            spinner_adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);

            // Specify the layout to use when the list of choices appears
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(spinner_adapter);
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Exercise new_exercise = new Exercise(spinner.getSelectedItem().toString());
                new_workout.addExercise(new_exercise);
                for (Exercise e : new_workout.getExercises()) {
                    System.out.println(e);
                }
                exerciseList.add(spinner.getSelectedItem().toString());
                System.out.println("Adding: " + spinner.getSelectedItem().toString());
                activity_adapter.notifyDataSetChanged();

            }
        });
    }
}
