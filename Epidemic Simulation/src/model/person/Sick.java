package model.person;

/**
 * Created by Oguz on 20/12/2016.
 */
public class Sick extends SickStatus implements HealthStatus {

    public Sick(int numDaysInfected) {
        super(numDaysInfected);
    }

    @Override
    public void print() {
        System.out.println("I'm Sick, infected since " + getNumDaysInfected() + " days.");
    }

    @Override
    public boolean visiblyInfected() {
        return true;
    }
}