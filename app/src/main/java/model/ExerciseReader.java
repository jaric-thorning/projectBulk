package model;

import android.content.Context;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import projectbulk.benchpress.MainActivity;

/**
 * Created by jaricthorning on 22/05/16.
 */
public class ExerciseReader {

    Set<Exercise> exercise_list;

    public ExerciseReader(){
        this.exercise_list = new HashSet<Exercise>();

    }

    public Set<Exercise> read(Context ctx, int resId) throws FileNotFoundException, IOException {
        System.out.println("GETTING EXERCISES");

        InputStream inputStream = ctx.getResources().openRawResource(resId);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);

        String line;
        Exercise e1 = null;

        try {
            while ((line = bufferedreader.readLine()) != null) {
                String[] retval = line.split(",");
                if(retval != null) {
                    e1 = new Exercise(retval[0]);
                    this.exercise_list.add(e1);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        System.out.println("GOT EXERCISES");
        return exercise_list;
    }
}
