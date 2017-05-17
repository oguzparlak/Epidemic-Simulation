package model;

import model.person.Person;

import java.util.*;
import java.util.Map;

/**
 * Created by Oguz on 29/11/2016.
 */

public class Country {

    private Map<Direction, Country> neighbours;
    private List<Person> persons;

    private Random random; //Get random person from this country

    private Calculator calculator;

    public Country() {
        random = new Random();
        neighbours = new HashMap<>();
        persons = new ArrayList<>();
        calculator = new Calculator(persons);
    }

    public Person getRandomPerson() {
        try {
            int randIndex = random.nextInt(persons.size());
            return persons.get(randIndex);
        }catch(IllegalArgumentException ex) {
            return null;
        }
    }

    public void addNeighbour(Direction to, Country country) {
        neighbours.put(to, country);
    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public int getNeighborCount() {
        return neighbours.size();
    }

    public Map<Direction, Country> getNeighbours() {
        return neighbours;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public int getPersonCount() {
        return persons.size();
    }

    public Calculator getCalculator() {
        return calculator;
    }

    @Override
    public String toString() {
        return "Country{" +
                "personCount=" + getPersonCount() +
                '}';
    }
}

