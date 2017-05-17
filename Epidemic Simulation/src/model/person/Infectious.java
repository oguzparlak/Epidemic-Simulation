package model.person;

/**
 * Created by Oguz on 20/12/2016.
 */
public class Infectious extends SickStatus implements HealthStatus {

    public Infectious(int numDaysInfected) {
        super(numDaysInfected);
    }

    //Only Healthy and Infectious Status can be initialized with empty constructor
    public Infectious() {
        super(0);
    }

    @Override
    public void print() {
        System.out.println("I'm Infectious, infected since " + getNumDaysInfected() + " days.");
    }

    @Override
    public boolean visiblyInfected() {
        return false;
    }

}