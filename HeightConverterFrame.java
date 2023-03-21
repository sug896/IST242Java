import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
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
   final static int IN_PER_FT = 12;      // Inches per foot

   /* Constructor creates GUI components and adds GUI components
      using a GridBagLayout. */
   HeightConverterFrame() {
      int feetMin = 0;    // Feet slider min value
      int feetMax = 10;   // Feet slider max value
      int feetInit = 5;   // Feet slider initial value
      int inchesMin = 0;  // Inches slider min value
      int inchesMax = 11; // Inches slider max value
      int inchesInit = 8; // Inches sldier initial value
      GridBagConstraints layoutConst = null; // GUI component layout

      // Set frame's title
      setTitle("Height converter");

      // Create labels
      feetLabel = new JLabel("Enter feet:");
      inchesLabel = new JLabel("Enter inches:");
      cmLabel = new JLabel("Centimeters:");

      heightCmField = new JTextField(10);
      heightCmField.setEditable(false);

      convertButton = new JButton("Convert");
      convertButton.addActionListener(this);

      // Create slider that enables user to enter height in feet
      heightFtSlider = new JSlider(feetMin, feetMax, feetInit);
      heightFtSlider.addChangeListener(this); // Use HeightConverter's stateChanged()
      heightFtSlider.setMajorTickSpacing(10);
      heightFtSlider.setMinorTickSpacing(1);
      heightFtSlider.setPaintTicks(true);
      heightFtSlider.setPaintLabels(true);

      heightFtField = new JTextField(10);
      heightFtField.setEditable(false);
      heightFtField.setText("5");

      // Creates slider that enables user to enter height in inches
      heightInSlider = new JSlider(inchesMin, inchesMax, inchesInit);
      heightInSlider.addChangeListener(this); // Use HeightConverter's stateChanged()
      heightInSlider.setMajorTickSpacing(10);
      heightInSlider.setMinorTickSpacing(1);
      heightInSlider.setPaintTicks(true);
      heightInSlider.setPaintLabels(true);

      heightInField = new JTextField(10);
      heightInField.setEditable(false);
      heightInField.setText("8");

      // Create frame and add components using GridBagLayout
      setLayout(new GridBagLayout());

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 1, 1);
      layoutConst.anchor = GridBagConstraints.LINE_START;
      layoutConst.gridx = 0;
      layoutConst.gridy = 0;
      layoutConst.gridwidth = 1;
      add(feetLabel, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 1, 1);
      layoutConst.anchor = GridBagConstraints.LINE_START;
      layoutConst.gridx = 2;
      layoutConst.gridy = 0;
      layoutConst.gridwidth = 1;
      add(inchesLabel, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 1, 1, 10);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 1;
      layoutConst.gridy = 0;
      layoutConst.gridwidth = 1;
      add(heightFtField, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 1, 10);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 3;
      layoutConst.gridy = 0;
      layoutConst.gridwidth = 1;
      add(heightInField, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(1, 10, 10, 10);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 0;
      layoutConst.gridy = 1;
      layoutConst.gridwidth = 2;
      add(heightFtSlider, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(1, 10, 10, 10);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 2;
      layoutConst.gridy = 1;
      layoutConst.gridwidth = 2;
      add(heightInSlider, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 10, 5);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 2;
      layoutConst.gridwidth = 1;
      add(convertButton, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 10, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx = 1;
      layoutConst.gridy = 2;
      layoutConst.gridwidth = 1;
      add(cmLabel, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 1, 10, 10);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 2;
      layoutConst.gridy = 2;
      layoutConst.gridwidth = 2;
      add(heightCmField, layoutConst);
   }

   /* Converts a height in feet/inches to centimeters. */
   public static double HeightFtInToCm(int ft, int in) {
      int totIn;              // Total inches input by user
      double cmHeight;        // Corresponding height in cm

      totIn = (ft * IN_PER_FT) + in; // Total inches
      cmHeight = totIn * CM_PER_IN;  // Conver to cm
      return cmHeight;
   }

   /* Called as slider value changes. Updates fields to display
      the numerical representation of the slider settings. */
   @Override
   public void stateChanged(ChangeEvent event) {
      int sliderVal;            // Slider value (int)
      String strSliderVal;      // Slider value (string)

      // Get source of event (2 sliders in GUI)
      JSlider sourceEvent = (JSlider) event.getSource();

      if (sourceEvent == heightFtSlider) {
         sliderVal = heightFtSlider.getValue();      // Get slider value
         strSliderVal = Integer.toString(sliderVal); // Conver to int
         heightFtField.setText(strSliderVal);        // Update display
      }
      else if (sourceEvent == heightInSlider) {
         sliderVal = heightInSlider.getValue();
         strSliderVal = Integer.toString(sliderVal);
         heightInField.setText(strSliderVal);
      }

   }

   
   

   /* Creates a HeightConverterFrame and makes it visible */
   public static void main(String[] args) {
      // Creates HeightConverterFrame and its components
      HeightConverterFrame myFrame = new HeightConverterFrame();

      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.pack();
      myFrame.setVisible(true);
   }
}
