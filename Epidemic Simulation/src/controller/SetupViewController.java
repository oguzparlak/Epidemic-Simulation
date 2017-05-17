package controller;

import main.Main;
import model.Simulator;
import view.SetupView;
import view.SimulatorView;

import javax.swing.*;

/**
 * Created by Oguz on 18/12/2016.
 */
public class SetupViewController {

    private SetupView view;

    public SetupViewController() {
        view = new SetupView(this);
    }

    public void goButtonClicked() {
        Simulator simulator = Simulator.getInstance();
        String personCount = view.getPersonCount();
        String mapSize = view.getMapSize();
        String infectedRatio = view.getInfectedRatio();
        String superRatio = view.getSuperPeopleRatio();
        String doctorRatio = view.getDoctorRatio();
        try {
            int size = Integer.parseInt(mapSize);
            simulator.init(Integer.parseInt(personCount), size, Integer.parseInt(infectedRatio), Integer.parseInt(superRatio), Integer.parseInt(doctorRatio)); //Change this !!
            //Create new view and new controller
            MainViewController mainController = new MainViewController();
            SimulatorView simulatorView = new SimulatorView(size, mainController);
            //Set the reference
            mainController.setView(simulatorView);
            mainController.setGridSize(size);
            //Update UI
            mainController.updateUI();
            //Update the frame
            Main.mainFrame.setContentPane(simulatorView);
            Main.mainFrame.setSize(1920, 1080);
            Main.mainFrame.pack();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Must be an integer !");
        }
    }

    public SetupView getView() {
        return view;
    }

}
