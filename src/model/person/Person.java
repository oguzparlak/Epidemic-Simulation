package model.person;

import constants.Constants;
import model.Country;
import model.Direction;
import model.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Oguz on 29/11/2016.
 */

public class Person {

    private HealthStatus healthStatus;
    private Country country;
    private int moveDay;

    private boolean vaccinated; //If a person vaccinated, he becomes immune for life

    private Random random = new Random();

    //A person can only be initialized as Healthy
    public Person() {
        healthStatus = new Healthy();
        setMoveDay(Constants.MOVE_DAY_UPPER_LIMIT); //Assign a move day at initialization
    }

    //A person can only move to neigboring country by default
    public boolean moveTo(Country c) {
        if (c != null){
            boolean canMove = Map.areNeighbors(country, c); //Check if the current country and target country are neighbors
            if (canMove) {
                move(c);
                return true;
            } else {
                throw new IllegalArgumentException("Attempt to move non-neighboring country");
            }
        }
        return false;
    }

    private void move(Country c) {
        country.getPersons().remove(this); //Remove the reference from the old country
        c.getPersons().add(this); //Add the reference to new country
        setCountry(c);
    }

    //A person can move with the airlines, then there is no restrictions for neighboring country
    public void flyTo(Country c) {
        move(c);
    }

    public boolean vaccinate() {
        if (healthStatus instanceof Healthy && !isVaccinated()) {
            setHealthStatus(new Immune(0));
            vaccinated = true;
            return true;
        }
        return false;
    }

    public Country pickMoveCountry() {
        List<Country> possibleCountries = new ArrayList<>(country.getNeighbours().values());
        for (Country c : possibleCountries) {
            int visibleSick = c.getCalculator().calculateVisibleSickPersonCount();
            if (visibleSick == 0) {
                return c;
            }
        }
        return null;
    }

    public void setMoveDay(int limit) {
        moveDay += random.nextInt(limit) + 1;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public int getMoveDay() {
        return moveDay;
    }

    public boolean canMove() {
        return !(isDead());
    }

    //We may add rules, for ex: a transition from Healthy to Sick is not possible
    //There are specific conditions that can be never changed again, such as
    //Dead, Super or Vaccinated
    public void setHealthStatus(HealthStatus status) {
        if (!isDead() && !isSuper() && !vaccinated)
            this.healthStatus = status;
    }

    public boolean isSuper() {
        return healthStatus instanceof Super;
    }

    public boolean isHealthy() {
        return healthStatus instanceof Healthy;
    }

    public boolean isDead() {
        return healthStatus instanceof Dead;
    }

    public boolean isImmune() {
        return healthStatus instanceof Immune;
    }

    public boolean isInfectious() {
        return healthStatus instanceof Infectious;
    }

    public boolean isSick() {
        return healthStatus instanceof Sick;
    }

    public boolean isInfected() {
        return healthStatus instanceof SickStatus;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public int getNumDaysInfected() {
        return healthStatus.getNumDaysInfected();
    }

    public void incrementNumDaysInfected() {
        healthStatus.incrementNumDaysInfected();
    }

    public void print() {
        healthStatus.print();
    }

    public boolean visiblyInfected() {
        return healthStatus.visiblyInfected();
    }

    public void setCountry(Country c) {
        this.country = c;
    }

    public Country getCountry() {
        return country;
    }
}
