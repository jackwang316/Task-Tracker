package ca.cmpt213.a4.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskGUI extends JFrame{
    public final static int WINDOW_WIDTH = 700;
    public final static int WINDOW_HEIGHT = 720;
    public final static int BUTTON_PREFER_WIDTH = 100;
    public final static int BUTTON_PREFER_HEIGHT = 25;
    JFrame taskFrame;
    JPanel topButtonPanel;
    JPanel taskPanel;
    JPanel bottomButtonPanel;
    JButton allButton;
    JButton overdueButton;
    JButton upcomingButton;
    JButton addNewButton;

    public TaskGUI() {
        taskFrame = new JFrame("My To-Do List");
        taskFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        initAllButton();
        initOverdueButton();
        initUpcomingButton();

        initTopPanel();
        initBottomPanel();

        taskFrame.setVisible(true);
        taskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initAllButton() {
        allButton = new JButton("All");
        allButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Todo: add stuff related to all button.
            }
        });
    }

    public void initOverdueButton() {
        overdueButton = new JButton("Overdue");
        overdueButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        overdueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Todo: add stuff related to overdue button.
            }
        });
    }

    public void initUpcomingButton() {
        upcomingButton = new JButton("Upcoming");
        upcomingButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        upcomingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Todo: add stuff related to upcoming button.
            }
        });
    }

    public void initTopPanel() {
        topButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topButtonPanel.add(allButton);
        topButtonPanel.add(overdueButton);
        topButtonPanel.add(upcomingButton);
        taskFrame.add(topButtonPanel, BorderLayout.NORTH);
    }

    public void initBottomPanel() {
        bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addNewButton = new JButton("Add Task");
        addNewButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        bottomButtonPanel.add(addNewButton);
        taskFrame.add(bottomButtonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskGUI();
            }
        });
    }
}
