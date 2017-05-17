package controller;

import model.Country;
import model.Map;
import model.Simulator;
import view.InfoView;
import view.SimulatorView;
import view.WorldInfoView;

import java.awt.*;

/**
 * Created by Oguz on 07/12/2016.
 */
public class MainViewController {

    private Simulator simulator;
    private int size;

    private SimulatorView simulatorView;

    public MainViewController() {
        simulator = Simulator.getInstance();
    }

    public void setView(SimulatorView simulatorView) {
        this.simulatorView = simulatorView;
    }

    public void setGridSize(int size) {
        this.size = size;
    }

    public void countryClicked(int i, int j) {
        Map map = simulator.getMap();
        Country c = map.getMap()[i][j];

        //Update UI
        InfoView infoView = simulatorView.getSideView().getCountryInfoView();
        infoView.getPersonCountLabel().setText("Person Count: " + c.getPersonCount());
        infoView.getHealthyLabel().setText("Healthy: " + c.getCalculator().calculateHealthyPeopleCount());
        infoView.getInfectiousLabel().setText("Infectious: " + c.getCalculator().calculateInfectedPersonCount());
        infoView.getDeadLabel().setText("Dead: " + c.getCalculator().calculateDeadPersonCount());
        infoView.getSickLabel().setText("Sick: " + c.getCalculator().calculateSickPersonCount());
        infoView.getImmuneLabel().setText("Immune: " + c.getCalculator().calculateImmunePersonCount());
        infoView.getSuperLabel().setText("Super: " + c.getCalculator().calculateSuperPersonCount());
        infoView.getVaccinatedLabel().setText("Vaccinated: " + c.getCalculator().calculateVaccinatedPersonCount());
    }

    public void displayWorldStats() {
        WorldInfoView view = simulatorView.getSideView().getWorldInfoView();
        view.getPersonCountLabel().setText("Person Count: " + simulator.getPersons().size());
        view.getHealthyLabel().setText("Healthy: " + simulator.getCalculator().calculateHealthyPeopleCount());
        view.getInfectiousLabel().setText("Infectious: " + simulator.getCalculator().calculateInfectedPersonCount());
        view.getDeadLabel().setText("Dead: " + simulator.getCalculator().calculateDeadPersonCount());
        view.getSickLabel().setText("Sick: " + simulator.getCalculator().calculateSickPersonCount());
        view.getImmuneLabel().setText("Immune: " + simulator.getCalculator().calculateImmunePersonCount());
        view.getSuperLabel().setText("Super: " + simulator.getCalculator().calculateSuperPersonCount());
        view.getVaccinatedLabel().setText("Vaccinated: " + simulator.getCalculator().calculateVaccinatedPersonCount());

        view.getDaysLabel().setText("Day: " + simulator.getCurrentDay());
        view.getMapSizeLabel().setText("Map Size: " + String.valueOf(size) + " * " + String.valueOf(size));

    }

    public void simulateBtnClicked() {
        simulator.simulate();
        updateUI();
    }

    protected void updateUI() {
        displayWorldStats();
        countryClicked(0, 0);
    }

    public SimulatorView getView() {
        return simulatorView;
    }



}
