package view;

import controller.SetupViewController;

import constants.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Oguz on 18/12/2016.
 */
public class SetupView extends JPanel {

    private Field mPersonCountField;
    private Field mMapSizeField;
    private Field mInfectedRatioField;
    private Field mSuperRatioField;
    private Field mDoctorRatioField;

    public SetupView(SetupViewController controller) {
        setLayout(new GridLayout(6, 1));

        mPersonCountField = new Field("Enter Person Count");
        mMapSizeField = new Field("Enter Map Size");
        mInfectedRatioField = new Field("Enter Infected Ratio");
        mSuperRatioField = new Field("Enter Super People Ratio");
        mDoctorRatioField = new Field("Enter Doctor Ratio");

        add(mPersonCountField);
        add(mMapSizeField);
        add(mInfectedRatioField);
        add(mSuperRatioField);
        add(mDoctorRatioField);

        JButton goButton = new JButton("Go !");
        goButton.setFont(Constants.getPlainFont());
        goButton.addActionListener(e -> controller.goButtonClicked());

        add(goButton);
    }

    public String getPersonCount() {
        return mPersonCountField.getTextField().getText();
    }

    public String getMapSize() {
        return mMapSizeField.getTextField().getText();
    }

    public String getInfectedRatio() {
        return mInfectedRatioField.getTextField().getText();
    }

    public String getSuperPeopleRatio() {
        return mSuperRatioField.getTextField().getText();
    }

    public String getDoctorRatio() {
        return mDoctorRatioField.getTextField().getText();
    }

    public class Field extends JPanel {

        private JTextField mTextField;

        public Field(String info) {
            setLayout(new FlowLayout());

            mTextField = new JTextField(25);

            JLabel label = new JLabel(info);
            label.setFont(Constants.getPlainFont());

            add(label);
            add(mTextField);

        }

        public JTextField getTextField() {
            return mTextField;
        }

    }

}

