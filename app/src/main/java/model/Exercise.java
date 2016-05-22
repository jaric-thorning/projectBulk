package model;

/**
 * Created by jaricthorning on 22/05/16.
 *
 * Stores exercise infomation
 *
 * Single identifier string name of exercise
 *
 * Holds reps, sets, weight per set, 1rm,
 */
public class Exercise {

    private String name;
    private int reps;
    private int sets;
    private int weight;


    public Exercise(String name){
        this.name = name;
        this.reps = 0;
        this.sets = 0;

        this.weight = 0;


    }

    public void set_reps(int reps){
        this.reps = reps;
        return;
    }

    public void set_sets(int sets){
        this.sets = sets;
        return;
    }

    public void set_weight(int weight){
        this.weight = weight;
        return;
    }


    @Override
    public String toString(){

        return this.name + ": " + Integer.toString(this.sets) + " sets of " + Integer.toString(this.reps) + " reps at " +  Integer.toString(this.weight)
                + "kg.";
    }
}
