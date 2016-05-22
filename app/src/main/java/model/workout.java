package model;

import java.util.HashSet;
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

    private Log logFile;
    private Set<Exercise> exercises;

    public Workout(Log logFile){
        this.logFile = logFile;
        this.exercises = new HashSet<Exercise>();

    }

    /**
     *
     * @return the logfile of the Workout
     */
    public Log getLogFile(){
        return this.logFile;
    }


    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }

    public Set<Exercise> getExercises(){
        return this.exercises;
    }
}
