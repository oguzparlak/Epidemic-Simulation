package model.person;

/**
 * Created by Oguz on 20/12/2016.
 */
public class SickStatus {

    private int numDaysInfected;

    public SickStatus(int numDaysInfected) {
        this.numDaysInfected = numDaysInfected;
    }

    public void incrementNumDaysInfected() {
        numDaysInfected++;
    }

    public int getNumDaysInfected() {
        return numDaysInfected;
    }

}