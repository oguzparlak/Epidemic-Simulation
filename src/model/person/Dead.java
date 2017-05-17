package model.person;

/**
 * Created by Oguz on 20/12/2016.
 */

public class Dead extends SickStatus implements HealthStatus {

    public Dead(int numDaysInfected) {
        super(numDaysInfected);
    }

    @Override
    public void print() {
        System.out.println("I'm dead");
    }

    @Override
    public boolean visiblyInfected() {
        return true;
    }

}
