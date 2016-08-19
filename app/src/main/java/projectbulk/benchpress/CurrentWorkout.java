package projectbulk.benchpress;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Exercise;
import model.ExerciseReader;
import model.Log;
import model.Workout;

public class CurrentWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_workout);
        setupActionBar();


        final Spinner editText_name = (Spinner) findViewById(R.id.new_workout_exercise_name_spinner);
        final EditText editText_reps = (EditText) findViewById(R.id.new_workout_exercise_reps_entry);
        final EditText editText_sets = (EditText) findViewById(R.id.new_workout_exercise_sets_entry);
        final EditText editText_weight = (EditText) findViewById(R.id.new_workout_exercise_weight_entry);


        final Button button = (Button) findViewById(R.id.new_workout_submit_button);

        final Workout new_workout = new Workout(editText_name.toString());

        ExerciseReader exerciseReader = new ExerciseReader();

        Set<Exercise> exercise_list = null;

        try {
            exercise_list = exerciseReader.read(this, R.raw.exercise_definitions);
        }
        catch (Exception e) {
            System.out.println("FAILED TO LOAD EXERCISES" + e);
        }

        final Spinner spinner = (Spinner) findViewById(R.id.new_workout_exercise_name_spinner);

        if(exercise_list != null) {
            final List<String> list=new ArrayList<String>();

            for (Exercise e : exercise_list) {
                System.out.println(e);
                list.add(e.toString());

            }
            Collections.sort(list);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Exercise new_exercise = new Exercise(spinner.getSelectedItem().toString());
                new_exercise.set_reps(Integer.parseInt(editText_reps.getText().toString()));
                new_exercise.set_sets(Integer.parseInt(editText_sets.getText().toString()));
                new_exercise.set_weight(Integer.parseInt(editText_weight.getText().toString()));
                new_workout.addExercise(new_exercise);

                for (Exercise e : new_workout.getExercises()) {
                    System.out.println(e);
                }
            }
        });

    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(CurrentWorkout.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}