package fitrak3.ui.basic;

import fitrak3.basic.WalkingWorkout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WalkingWorkoutCalculator extends JFrame {
    private JPanel MainPanel;
    private JPanel Jpanel2;
    private JTextField durationMinutes;
    private JLabel durationLabel;
    private JTextField date;
    private JLabel dateLabel;
    private JTextField weight;
    private JLabel weightLabel;
    private JTextField steps;
    private JLabel stepsLabel;
    private JTextField intensity;
    private JTextField sex;
    private JLabel sexLabel;
    private JTextField height;
    private JLabel heightLabel;
    private JLabel Output;
    private JButton OutputButton;
    private JLabel TitleLabel;
    private JComboBox IntensityDropDown;
    private JLabel errorLabel;  // Add this for error messages

    public WalkingWorkoutCalculator() {

        setContentPane(MainPanel);
        setVisible(true);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear previous error
                if (errorLabel != null) {
                    errorLabel.setText("");
                    errorLabel.setVisible(false);
                }

                try {
                    // Get user input
                    double duration = Double.parseDouble(durationMinutes.getText().trim());
                    String dateStr = date.getText().trim();
                    double userWeight = Double.parseDouble(weight.getText().trim());
                    int userSteps = Integer.parseInt(steps.getText().trim());
                    String userIntensity = intensity.getText().trim();
                    String userSex = sex.getText().trim();
                    double userHeight = Double.parseDouble(height.getText().trim());

                    // Validate inputs
                    if (duration <= 0) {
                        showError("Duration must be greater than 0!");
                        return;
                    }
                    if (userWeight <= 0 || userWeight > 500) {
                        showError("Weight must be between 1-500 kg!");
                        return;
                    }
                    if (userHeight <= 0 || userHeight > 300) {
                        showError("Height must be between 1-300 cm!");
                        return;
                    }
                    if (userSteps < 0) {
                        showError("Steps cannot be negative!");
                        return;
                    }
                    if (dateStr.isEmpty()) {
                        showError("Please enter a date!");
                        return;
                    }
                    if (!userSex.equalsIgnoreCase("male") && !userSex.equalsIgnoreCase("female")) {
                        showError("Sex must be 'Male' or 'Female'!");
                        return;
                    }
                    if (userIntensity.isEmpty()) {
                        showError("Please enter intensity!");
                        return;
                    }

                    // Create workout object
                    WalkingWorkout walk = new WalkingWorkout(
                            duration, userWeight, dateStr, userSteps, userIntensity, userSex, userHeight
                    );

                    // Output results
                    String result = String.format(
                            "<html>Date: %s<br>Distance: %.2f km<br>Calories: %.2f kcal</html>",
                            walk.getDate(), walk.getDistanceKM(), walk.getCaloriesBurned()
                    );
                    Output.setText(result);
                    Output.setForeground(Color.BLACK);

                } catch (NumberFormatException ex) {
                    showError("Please enter valid numbers!");
                } catch (Exception ex) {
                    showError("Error: " + ex.getMessage());
                }
            }
        });
    }

    // Method to show error in red label
    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setForeground(Color.RED);
            errorLabel.setVisible(true);
        } else {
            // Fallback to dialog if errorLabel doesn't exist in form
            JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}