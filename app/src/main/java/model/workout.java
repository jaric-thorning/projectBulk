package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jaricthorning on 20/05/16.
 *
 * Workout class
 *
 *
 *
 */
public class Workout {

    private Set<Exercise> exercises;

    private String workoutName; //unique identifier

    public Workout(String workoutName){
        this.workoutName = workoutName;
        this.exercises = new HashSet<Exercise>();

    }

    public void addExercise(Exercise exercise){
        if (!this.exercises.contains(exercise)){
            this.exercises.add(exercise);
        }
    }

    public Set<Exercise> getExercises(){
        return this.exercises;
    }

    public String String(){
        String constructor = workoutName + ":";
        List<Exercise> list = new ArrayList<Exercise>(this.exercises);
        for(int i = 0; i <  this.exercises.size(); i++){
            constructor = constructor + " " + list.get(i);
        }
        return constructor;
    }
    public void updateName(String name){
        this.workoutName = name;
    }
    public String getName(){
        return workoutName;
    }
}
