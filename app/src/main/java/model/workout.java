package model;

/**
 * Created by jaricthorning on 20/05/16.
 *
 * Workout class
 *
 *
 */
public class Workout {

    private Log logFile;
    public Workout(Log logFile){
        this.logFile = logFile;
    }

    /**
     *
     * @return the logfile of the Workout
     */
    public Log getLogFile(){
        return this.logFile;
    }
}
