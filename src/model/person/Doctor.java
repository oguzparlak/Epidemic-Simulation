package model.person;

/**
 * Created by Oguz on 02/01/2017.
 */
public class Doctor extends Person {

    private int vaccinateCounter;

    public void vaccinate(Person p) {
        if (!isDead()) {//If this doctor is alive, so that he/she can vaccinate other people
            boolean vaccinated = p.vaccinate();
            if (vaccinated)
                vaccinateCounter++;
        }
    }

    public void reset() {
        vaccinateCounter = 0;
    }

    public int getVaccinateCounter() {
        return vaccinateCounter;
    }

}
