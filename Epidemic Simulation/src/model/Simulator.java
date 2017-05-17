package model;

import constants.Constants;
import model.person.*;

import javax.print.Doc;
import java.util.*;

/**
 * Created by Oguz on 30/11/2016.
 */
public class Simulator {

    //Singleton instance
    private static Simulator simulator;

    private boolean initialized; //Variable created to make sure init method called just once
    private int days; //Current day

    private List<Person> persons; //All persons in the world
    private Map map; //Map object

    private Random random;

    private Calculator calculator;

    private Simulator(){
        days = 0;
        random = new Random();
        persons = new ArrayList<>();
    }

    public static Simulator getInstance(){
        if(simulator==null){
            simulator = new Simulator();
        }
        return simulator;
    }

    //The parameters P, N and X
    public void init(int personCount, int mapSize, int infectedPercent, int superPercent, int doctorPercent) {
        if (infectedPercent > 100 || infectedPercent < 0) {
            throw new IllegalArgumentException("Infected Percent can't be less than 0 or more than 100");
        }
        if (superPercent + infectedPercent > 100) {
            throw new IllegalArgumentException("Check the percentages !");
        }
        if (!initialized) {
            initialized = true;
            map = new Map(mapSize);
            initWorld(personCount, infectedPercent, superPercent, doctorPercent);
        } else {
            throw new IllegalArgumentException("Init can only be called once");
        }
    }

    //Init Persons, Countries, assign them according to infected ratio
    private void initWorld(int personCount, int infectedPercent, int superPercent, int doctorPercent) {
        int infectedCount = personCount * infectedPercent / 100;
        int superCount = personCount * superPercent / 100;
        int doctorCount = personCount * doctorPercent / 100;

        //Init People
        for (int i = 0; i < personCount; i++) {
            Person p;
            if (i < doctorCount)
                p = new Doctor();
             else
                p = new Person();
            map.addPersonTo(map.getRandomCountry(), p);
        }

        //Assign superPeople
        int superCounter = 0;
        while (superCounter < superCount) {
            Country c = map.getRandomCountry();
            Person p = c.getRandomPerson();
            if (p != null) {
                p.setHealthStatus(new Super());
                superCounter++;
            }
        }

        //Assign infectious people
        int infectedCounter = 0;
        while (infectedCounter < infectedCount) {
            Country c = map.getRandomCountry();
            Person p = c.getRandomPerson();
            if (p != null && p.isHealthy()) {
                p.setHealthStatus(new Infectious());
                infectedCounter++;
            }
        }

        updatePersonList();

        calculator = new Calculator(persons);

    }


    private void updatePersonList() {
        persons.clear();
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap().length; j++) {
                Country c = map.getMap()[i][j];
                //Add all the information to the persons list
                //So that Simulator will be able to control whole people in the world
                persons.addAll(c.getPersons());
            }
        }
    }

    //Simulate for one day
    public void simulate() {
       if (!initialized) {
            System.out.println("You need to call init with specific parameters");
            return;
        }

        for (Person p : persons) {
            if (p.isInfected())
                updateInfectedStatus(p);

            if (p.canMove())
                updateMoves(p);

            performVaccinates(p);
        }

        //Increment the current day
        days++;
    }

    private void updateInfectedStatus(Person p) {
        //Increment number of days infected of unhealthy people
        p.incrementNumDaysInfected();
        //Special days: 6, 14, 16, 18
        int numDaysInfected = p.getNumDaysInfected();
        switch (numDaysInfected) {
            case 6:
                //After 6 days of becoming infected, a person becomes sick and is therefore visibly infectious
                p.setHealthStatus(new Sick(p.getNumDaysInfected()));
                break;
            case 14:
                //After 14 days of becoming infected, a person dies with a probability of 25%.
                //Dead people do not move, but stay visibly infectious.
                boolean dead = random.nextInt(4)==0;
                if (dead)
                    p.setHealthStatus(new Dead(p.getNumDaysInfected()));
                break;
            case 16:
                //After 16 days of becoming infected, a person becomes immune and is no longer visibly infectious,
                //but remains infectious.
                p.setHealthStatus(new Immune(p.getNumDaysInfected()));
                break;
            case 18:
                //After 18 days of becoming infected, a person turns healthy.
                p.setHealthStatus(new Healthy());
                break;
        }
    }

    private void updateMoves(Person p) {
        // On the day of each move, a person will pick one of the countries available to her with equal probability and will move to it.
        // The set of available countries is defined as the neighboring countries that contain no visibly infectious people
        int moveDay = p.getMoveDay();
        if (moveDay == days) { // If it is time to move
            boolean air = random.nextFloat() <= Constants.MOVE_WITH_AIRLINES_PROBABILITY;
            Country c = p.getCountry(); // Current country of person
            if (air)
                p.flyTo(map.getRandomCountry()); // Move via air-traffic
             else
                p.moveTo(p.pickMoveCountry()); // Move one of the neighbouring countries with no visibly infected people
            p.setMoveDay(Constants.MOVE_DAY_UPPER_LIMIT); // Assign new move day for person
            if (!p.getCountry().equals(c)) { // If the country changed
                if (p.getCountry().getCalculator().calculateUnhealthyPersonCount() > 0) { // If anyone is infected in the new country
                    if (p.isHealthy()) { // If p is healthy, there is a %40 chance to get infected due to transmissibility rate
                        boolean transmitted = random.nextFloat() <= 0.4;
                        if (transmitted) {
                            p.setHealthStatus(new Infectious());
                        }
                    }
                }
            }
        }
    }

    private void performVaccinates(Person p) {
        if (p instanceof Doctor) {
            List<Person> persons = p.getCountry().getPersons(); //The list of people in same country with the doctor
            for (Person person : persons) {
                //Try to vaccinate people until reach the limit
                int counter = ((Doctor) p).getVaccinateCounter();
                if (counter == Constants.VACCINATE_LIMIT) {
                    break;
                } else {
                    if (!(person == p)) { // The person is not self
                        ((Doctor) p).vaccinate(person);
                    }
                }
            }
            //Reset
            ((Doctor) p).reset();
        }
    }

    //Simulate for many days
    public void simulateFor(int days) {
        for (int i = 0; i < days; i++) {
            simulate();
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Map getMap() {
        return map;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public int getCurrentDay() {
        return days;
    }
    
}
