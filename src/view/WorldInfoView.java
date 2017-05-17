package view;

import constants.Constants;
import controller.MainViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oguz on 02/01/2017.
 */
public class WorldInfoView extends InfoView {

    private JLabel mMapSize;
    private JLabel mDays;
    private JButton mSimulate;

    public WorldInfoView(MainViewController controller) {

        setLayout(new GridLayout(11, 1));

        mMapSize = new JLabel("Map Size: ");
        mDays = new JLabel("Day: ");
        mSimulate = new JButton("Simulate");

        mMapSize.setFont(Constants.getPlainFont());
        mDays.setFont(Constants.getPlainFont());

        mMapSize.setForeground(Color.RED);
        mDays.setForeground(Color.RED);

        add(mMapSize);
        add(mDays);
        add(mSimulate);

        mSimulate.addActionListener(e -> controller.simulateBtnClicked());
    }

    public JLabel getMapSizeLabel() {
        return mMapSize;
    }

    public JLabel getDaysLabel() {
        return mDays;
    }
}
