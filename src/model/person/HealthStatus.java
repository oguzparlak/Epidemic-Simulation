package model.person;

/**
 * Created by Oguz on 20/12/2016.
 */
public interface HealthStatus {
    void print();
    int getNumDaysInfected();
    void incrementNumDaysInfected();
    boolean visiblyInfected();
}