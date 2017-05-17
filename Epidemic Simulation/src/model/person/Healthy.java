package model.person;

/**
 * Created by Oguz on 20/12/2016.
 */
public class Healthy implements HealthStatus {

    @Override
    public void print() {
        System.out.println("I'm Healthy");
    }

    //Return 0, because the person is healthy
    @Override
    public int getNumDaysInfected() {
        return 0;
    }

    @Override
    public void incrementNumDaysInfected() {
        //Do nothing
    }

    public boolean visiblyInfected() {
        return false;
    }

}