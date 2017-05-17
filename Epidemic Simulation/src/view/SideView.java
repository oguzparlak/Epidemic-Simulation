package view;

import constants.Constants;
import controller.MainViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oguz on 20/12/2016.
 */
public class SideView extends JPanel {

    private InfoView mCountryInfoView;
    private WorldInfoView mWorldInfoView;


    public SideView(MainViewController controller) {
        setLayout(new GridLayout(3, 1));

        mCountryInfoView = new InfoView();
        mWorldInfoView = new WorldInfoView(controller);

        add(mCountryInfoView);
        add(new JLabel("--------------------"));
        add(mWorldInfoView);

    }

    public InfoView getCountryInfoView() {
        return mCountryInfoView;
    }

    public WorldInfoView getWorldInfoView() {
        return mWorldInfoView;
    }

}
