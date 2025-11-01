package fitrak3.ui.basic;

import javax.swing.*;

public class RunWalkingUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // ✅ Set Look & Feel first
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ignored) {}

            // Create and show frame
            WalkingWorkoutCalculator frame = new WalkingWorkoutCalculator();
            frame.setTitle("Walking Workout Calculator");
            frame.setSize(500, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }
}
