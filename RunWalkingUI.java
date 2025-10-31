package fitrak3.ui.basic;

import javax.swing.*;

public class RunWalkingUI {
    public static void main (String [] args){
       SwingUtilities.invokeLater(() -> new WalkingWorkoutCalculator());
    }
}
