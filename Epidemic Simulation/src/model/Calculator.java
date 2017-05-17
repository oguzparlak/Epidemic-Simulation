package model;

import model.person.HealthStatus;
import model.person.Person;

import java.util.List;

/**
 * Created by Oguz on 05/01/2017.
 */

public class Calculator {

    private List<Person> personList;

    private int counter;

    public Calculator(List<Person> persons) {
        personList = persons;
    }

    public int calculateHealthyPeopleCount() {
        counter = 0;
        personList.stream().filter(Person::isHealthy).forEach(this::incrementCounter);
        return counter;
    }

    private void incrementCounter(Person p) {
        counter++;
    }

    public int calculateInfectedPersonCount() {
        counter = 0;
        personList.stream().filter(Person::isInfectious).forEach(this::incrementCounter);
        return counter;
    }

    public int calculateSickPersonCount() {
        counter = 0;
        personList.stream().filter(Person::isSick).forEach(this::incrementCounter);
        return counter;
    }

    public int calculateDeadPersonCount() {
        counter = 0;
        personList.stream().filter(Person::isDead).forEach(this::incrementCounter);
        return counter;
    }

    public int calculateUnhealthyPersonCount() {
        counter = 0;
        personList.stream().filter(Person::isInfected).forEach(this::incrementCounter);
        return counter;
    }

    public int calculateVisibleSickPersonCount() {
        counter = 0;
        personList.stream().filter(Person::visiblyInfected).forEach(this::incrementCounter);
        return counter;
    }

    public int calculateImmunePersonCount() {
        counter = 0;
        personList.stream().filter(Person::isImmune).forEach(this::incrementCounter);
        return counter;
    }

    public int calculateVaccinatedPersonCount() {
        counter = 0;
        personList.stream().filter(Person::isVaccinated).forEach(this::incrementCounter);
        return counter;
    }

    public int calculateSuperPersonCount() {
        counter = 0;
        personList.stream().filter(Person::isSuper).forEach(this::incrementCounter);
        return counter;
    }

}
