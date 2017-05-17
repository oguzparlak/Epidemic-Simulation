package model.person;

/**
 * Created by Oguz on 02/01/2017.
 */
public class Super implements HealthStatus {

    @Override
    public void print() {
        System.out.println("I'm a super people, I never get infected");
    }

    @Override
    public int getNumDaysInfected() {
        return 0;
    }

    @Override
    public void incrementNumDaysInfected() {

    }

    @Override
    public boolean visiblyInfected() {
        return false;
    }

}
