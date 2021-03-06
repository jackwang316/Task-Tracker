package ca.cmpt213.a4;

import ca.cmpt213.a4.view.TaskGUI;

import javax.swing.*;

/**
 * The main class is responsible for starting the UI on program launch.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskGUI();
            }
        });
    }
}
