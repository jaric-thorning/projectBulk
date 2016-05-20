package model;

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

    public Log( String startTime, String startDate){
        this.startTime = startTime;
        this.startDate = startDate;
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
}
