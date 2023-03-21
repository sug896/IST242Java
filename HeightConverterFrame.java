package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HeightConverterFrame extends JFrame implements ActionListener, ChangeListener {

    private JTextField heightCmField;  // Holds height output value in cm
    private JTextField heightFtField;  // Holds height input value in feet
    private JTextField heightInField;  // Holds heigth input value in inches
    private JLabel feetLabel;          // Label for heigth input in feet
    private JLabel inchesLabel;        // Label for height input in inches
    private JLabel cmLabel;            // Label for heigth in cm
    private JButton convertButton;     // Triggers heigth conversion
    private JSlider heightFtSlider;    // Slider for feet input
    private JSlider heightInSlider;    // Slider for inches input

    final static double CM_PER_IN = 2.54; // Centimeters per inch
    final static int IN_PER_FT = 12; //inch per foot

    /*
    constructor creates GUI components. It adds GUI components
    using a GridBagLayout
     */

    HeightConverterFrame(){
        int feetMin = 0; //feet slider min value
        int feetMax = 10; //feet slider max value
        int feetInch = 5; //feet slider initial value
        int feetInit = 5; // feet slider intial value
        int inchesMin = 0;
        int inchesMax = 11;
        int inchesInit = 8; // inches slider intial value
        GridBagConstraints layoutConst = null; // GUI component layout

        //set frame title
        setTitle("Height converter");

        //Create labels
        feetLabel = new JLabel("enter feet");
        inchesLabel = new JLabel("enter inches");
        cmLabel = new JLabel("centimeters");

        heightCmField = new JTextField(10);
        heightCmField.setEnabled(false);

        convertButton = new JButton("convert");
        convertButton.addActionListener(this);

        //create slider that enables user to enter height in feet
        heightFtSlider = new JSlider(feetMin, feetMax, feetInit);
        heightFtSlider.addChangeListener(this);
        heightFtSlider.setMajorTickSpacing(10);
        heightFtSlider.setMinorTickSpacing(1);
        heightFtSlider.setPaintTicks(true);
        heightFtSlider.setPaintLabels(true);

        heightFtField = new JTextField(10);
        heightFtField.setEditable(false);
        heightFtField.setText("5");

        //create slider that enables user to enter height in inches
        heightInSlider = new JSlider(inchesMin, inchesMax, inchesInit);
        heightInSlider.addChangeListener(this);
        heightInSlider.setMajorTickSpacing(10);
        heightInSlider.setMinorTickSpacing(1);
        heightInSlider.setPaintTicks(true);
        heightInSlider.setPaintLabels(true);

        heightInField = new JTextField(10);
        heightInField.setEditable(false);
        heightInField.setText("8");

        // create frame and add components using GridBaglayout
        setLayout(new GridBagLayout());

        //01
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 1);
        layoutConst.anchor = GridBagConstraints.LINE_START;
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(feetLabel, layoutConst);

        //02
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 1);
        layoutConst.anchor = GridBagConstraints.LINE_START;
        layoutConst.gridx = 2;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(inchesLabel, layoutConst);

        //03
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 1, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(heightFtField, layoutConst);

        //04
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 3;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(heightInField, layoutConst);

        //05
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.gridwidth = 2;
        add(heightFtSlider, layoutConst);

        //06
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 2;
        layoutConst.gridy = 1;
        layoutConst.gridwidth = 2;
        add(heightInSlider, layoutConst);

        //07
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 5);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 1;
        add(convertButton, layoutConst);

        //08
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 1;
        add(cmLabel, layoutConst);

        //09
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 2;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 2;
        add(heightCmField, layoutConst);



    }

    /* converts a height in feet/ inches to centimeters*/
    public static double HeightFtInToCm(int ft, int in){
        int totIn;
        double cmHeight;

        totIn = (ft * IN_PER_FT) + in; //total inches
        cmHeight = totIn * CM_PER_IN; // convert to cm
        return cmHeight;
    }

    //called as slider value changes. Update field to display
    //conversion of numerical value

    @Override
    public void stateChanged(ChangeEvent event){
        int sliderVal;
        String strSliderVal;

        JSlider sourceEvent = (JSlider) event.getSource();
        if(sourceEvent == heightFtSlider){
            sliderVal = heightFtSlider.getValue();
            strSliderVal = Integer.toString(sliderVal);
             heightFtField.setText(String.valueOf(sliderVal));
        } else if(sourceEvent == heightInSlider){
            sliderVal = heightInSlider.getValue();
            strSliderVal = Integer.toString(sliderVal);
            heightInField.setText(strSliderVal);
        }

    }



    public static void main(String[] args) {
        HeightConverterFrame myFrame = new HeightConverterFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
