package fitrak3.ui.basic;

import fitrak3.basic.WalkingWorkout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WalkingWorkoutCalculator extends JFrame {
    private JPanel MainPanel;
    private JPanel JPanel2;
    private JLabel TitleLabel;
    private JTextField DurationField;
    private JLabel DurationLabel;
    private JTextField WeightField;
    private JLabel WeightLabel;
    private JTextField DateField;
    private JLabel DateLabel;
    private JTextField StepsField;
    private JLabel StepsLabel;
    private JLabel IntensityLabel;
    private JComboBox IntensityComboB;
    private JLabel SexLabel;
    private JComboBox SexComboB;
    private JTextField HeightField;
    private JLabel HeightLabel;
    private JButton calculateButton;

    private JFrame outputFrame;
    private JTextArea txtOutput;
    private JButton btnOkay;


    public WalkingWorkoutCalculator() {
        setContentPane(MainPanel);

        calculateButton.addActionListener(e -> {
            try {
                // Parse input values
                double duration = Double.parseDouble(DurationField.getText());
                double weight = Double.parseDouble(WeightField.getText());
                String date = DateField.getText();
                int steps = Integer.parseInt(StepsField.getText());
                String intensity = (String) IntensityComboB.getSelectedItem();
                String sex = (String) SexComboB.getSelectedItem();
                double height = Double.parseDouble(HeightField.getText());

                // Create logic object
                WalkingWorkout walk = new WalkingWorkout(duration, weight, date, steps, intensity, sex, height);

                double calories = walk.getCaloriesBurned();
                double distance = walk.getDistanceKM();

                StringBuilder output = new StringBuilder();
                output.append("Calories burned: ").append(String.format("%.2f", calories)).append("\n");
                output.append("Distance walked: ").append(String.format("%.2f", distance)).append(" km\n");

                outputFrame = new JFrame("Workout Output");
                outputFrame.setSize(400, 250);
                outputFrame.setLocationRelativeTo(null);
                outputFrame.setResizable(false);

                txtOutput = new JTextArea(8, 30);
                txtOutput.setEditable(false);
                txtOutput.setFont(new Font("Arial", Font.PLAIN, 14));
                txtOutput.setText(output.toString());  // ✅ now works


                JScrollPane scrollPane = new JScrollPane(txtOutput);

                btnOkay = new JButton("Okay");
                btnOkay.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        outputFrame.dispose();
                        // Optional: clear input fields if desired
                        DurationField.setText("");
                        WeightField.setText("");
                        DateField.setText("");
                        StepsField.setText("");
                        HeightField.setText("");
                    }
                });

                JPanel outputPanel = new JPanel(new BorderLayout(10, 10));
                outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                outputPanel.add(scrollPane, BorderLayout.CENTER);
                outputPanel.add(btnOkay, BorderLayout.SOUTH);

                outputFrame.add(outputPanel);
                outputFrame.setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MainPanel,
                        "Error: Please fill all fields correctly.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}



