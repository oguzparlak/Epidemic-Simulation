package main;

import controller.SetupViewController;
import model.Country;
import model.Direction;
import model.Simulator;
import model.person.*;

import javax.swing.*;

/**
 * Created by Oguz on 22/11/2016.
 */

public class Main {

    public static JFrame mainFrame;

    public static void main(String[] args) {

        mainFrame = new JFrame("Epidemic Simulation");
        mainFrame.setSize(1920, 1080);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        SetupViewController firstViewController = new SetupViewController();
        mainFrame.add(firstViewController.getView());

        mainFrame.setVisible(true);

    }
}
