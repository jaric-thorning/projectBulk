package model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaricthorning on 19/08/2016.
 */
public class WorkoutReader {

    List<Workout> saved_workouts;
    String filename = "workoutList.txt";
    public WorkoutReader(){
        saved_workouts = new ArrayList<Workout>();

    }

    public List<Workout> getWorkouts(){
        return saved_workouts;
    }
    public void loadWorkouts(Context context) {
        System.out.println("Loading workouts...");
        try {

            InputStream inputStream = context.openFileInput(this.filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                String[] workoutStrings;
                Workout found_workout;
                Exercise exercise1;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    int nameEnd = receiveString.indexOf(':');
                    workoutStrings = receiveString.substring(nameEnd + 1).split(" ");
                    found_workout = new Workout(receiveString.substring(0,nameEnd));
                    for(int i = 0; i < workoutStrings.length; i++){
                        exercise1 = new Exercise(workoutStrings[i]);
                        found_workout.addExercise(exercise1);
                    }
                    saved_workouts.add(found_workout);
                    stringBuilder.append(receiveString);

                }
                System.out.println(stringBuilder);
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.toString());
            this.createFile(context);
        } catch (IOException e) {
            System.out.println("Can not read file: " + e.toString());
        }
        catch (Exception e){
            System.out.println("Yeah... it failed:" + e.toString());
        }

        return;
    }

    public void createFile(Context context){
        System.out.println("Creating file...");
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(this.filename, Context.MODE_PRIVATE);
            outputStream.write("".getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_workout(Workout workout){
        this.saved_workouts.add(workout);
    }

    public void save_workouts(Context context){
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(this.filename, Context.MODE_PRIVATE);
            for(int i = 0; i < this.saved_workouts.size(); i++){
                System.out.println("Writing: " + this.saved_workouts.get(i).String());
                outputStream.write(this.saved_workouts.get(i).String().getBytes());
                outputStream.write(System.getProperty("line.separator").getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSize(){
        return saved_workouts.size();
    }
}
