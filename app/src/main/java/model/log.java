package model;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * Created by jaricthorning on 20/05/16.
 *
 * Log Class
 *     Logs workout data
 *
 *
 */
public class Log {

    private String startTime;
    private String startDate;
    private Workout workout;

    public Log( String startTime, String startDate, Workout workout){
        this.startTime = startTime;
        this.startDate = startDate;
        this.workout = workout;
    }

    /**
     *
     * @return start time of log
     */
    public String getStartTime(){
        return this.startTime;
    }

    /**
     *
     * @return start date of log
     */
    public String getStartDate(){
        return this.startDate;
    }

    public void write(Context ctx, String filename){
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(startTime.getBytes());
            outputStream.write(startDate.getBytes());
            outputStream.write(workout.String().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
