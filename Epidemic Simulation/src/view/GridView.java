package view;

import controller.MainViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oguz on 20/12/2016.
 */
public class GridView extends JPanel {

    public GridView(MainViewController controller, int size) {
        JButton[][] buttons = new JButton[size][size];

        setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                final int finalI = i;
                final int finalJ = j;
                buttons[i][j].addActionListener(e -> controller.countryClicked(finalI, finalJ));
                add(buttons[i][j]);
            }
        }

    }

}
