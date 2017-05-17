package model.person;

/**
 * Created by Oguz on 20/12/2016.
 */
public class Immune extends SickStatus implements HealthStatus {

    public Immune(int numDaysInfected) {
        super(numDaysInfected);
    }

    @Override
    public void print() {
        System.out.println("I'm immune, since " + getNumDaysInfected() + " days.");
    }

    @Override
    public boolean visiblyInfected() {
        return false;
    }

}
