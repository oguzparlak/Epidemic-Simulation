package view;

import constants.Constants;
import controller.MainViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oguz on 02/01/2017.
 */
public class InfoView extends JPanel {

    private JLabel mPersonCount = new JLabel("Person Count: ");
    private JLabel mHealthy = new JLabel("Healthy: ");
    private JLabel mInfectious = new JLabel("Infectious: ");
    private JLabel mSick = new JLabel("Sick: ");
    private JLabel mImmune = new JLabel("Immune: ");
    private JLabel mDead = new JLabel("Dead: ");
    private JLabel mSuper = new JLabel("Super: ");
    private JLabel mVaccinated = new JLabel("Vaccinated: ");

    public InfoView() {
        setLayout(new GridLayout(8, 1));

        mPersonCount.setFont(Constants.getPlainFont());
        mHealthy.setFont(Constants.getPlainFont());
        mInfectious.setFont(Constants.getPlainFont());
        mSick.setFont(Constants.getPlainFont());
        mImmune.setFont(Constants.getPlainFont());
        mDead.setFont(Constants.getPlainFont());
        mSuper.setFont(Constants.getPlainFont());
        mVaccinated.setFont(Constants.getPlainFont());

        add(mPersonCount);
        add(mHealthy);
        add(mInfectious);
        add(mSick);
        add(mImmune);
        add(mDead);
        add(mSuper);
        add(mVaccinated);

    }

    public JLabel getPersonCountLabel() {
        return mPersonCount;
    }

    public JLabel getHealthyLabel() {
        return mHealthy;
    }

    public JLabel getInfectiousLabel() {
        return mInfectious;
    }

    public JLabel getSickLabel() {
        return mSick;
    }

    public JLabel getImmuneLabel() {
        return mImmune;
    }

    public JLabel getDeadLabel() {
        return mDead;
    }

    public JLabel getSuperLabel() {
        return mSuper;
    }

    public JLabel getVaccinatedLabel() {
        return mVaccinated;
    }
}
