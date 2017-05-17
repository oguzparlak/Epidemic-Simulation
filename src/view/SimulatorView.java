package view;

import controller.MainViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oguz on 20/12/2016.
 */
public class SimulatorView extends JPanel {

    private GridView mGridView;
    private SideView mSideView;

    public SimulatorView(int size, MainViewController controller) {

        setLayout(new BorderLayout());

        mGridView = new GridView(controller, size);
        mSideView = new SideView(controller);

        add(mGridView, BorderLayout.CENTER);
        add(mSideView, BorderLayout.EAST);

    }

    public GridView getGridView() {
        return mGridView;
    }

    public SideView getSideView() {
        return mSideView;
    }
}

