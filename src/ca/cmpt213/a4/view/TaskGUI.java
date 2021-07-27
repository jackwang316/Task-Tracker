package ca.cmpt213.a4.view;

import ca.cmpt213.a4.control.TaskManager;
import ca.cmpt213.a4.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TaskGUI extends JFrame{
    public final static int WINDOW_WIDTH = 700;
    public final static int WINDOW_HEIGHT = 720;
    public final static int BUTTON_PREFER_WIDTH = 100;
    public final static int BUTTON_PREFER_HEIGHT = 25;
    JFrame taskFrame;
    JPanel topButtonPanel;
    JPanel midPanel;
    JPanel bottomButtonPanel;
    JButton allButton;
    JButton overdueButton;
    JButton upcomingButton;
    JButton addNewButton;
    TaskManager manager = new TaskManager();

    public TaskGUI() {
        taskFrame = new JFrame("My To-Do List");
        taskFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        initAllButton();
        initOverdueButton();
        initUpcomingButton();

        initTopPanel();
        initMidPanel();
        initBottomPanel();
        displayAllTask();

        taskFrame.setVisible(true);
        taskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initOnClose();
    }

    private void displayAllTask() {
        ArrayList<Task> toDisplay = manager.getAllTasks();
        JPanel taskBackGround = new JPanel();
        taskBackGround.setLayout(new BoxLayout(taskBackGround, BoxLayout.Y_AXIS));
        if(toDisplay.isEmpty()) {
            JTextField emptyBox = new JTextField("No task to show");
            emptyBox.setEnabled(false);
            emptyBox.setDisabledTextColor(Color.BLACK);
            taskBackGround.add(emptyBox);
            midPanel.add(emptyBox);
            return;
        }
        for(int i = 0; i < toDisplay.size(); i++) {
            initTaskPanel(toDisplay.get(i), taskBackGround, i);
        }
        JScrollPane scrollPane = new JScrollPane(taskBackGround, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        midPanel.add(scrollPane);
    }

    private void initTaskPanel(Task t, JPanel panel, int i) {
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setSize(new Dimension(400, 100));
        JLabel titleBox = new JLabel("Task #" + (i + 1));
        taskPanel.add(titleBox, BorderLayout.NORTH);

        JPanel infoPane = new JPanel(new BorderLayout());
        JLabel nameBox = new JLabel("Name: " + t.getName());
        infoPane.add(nameBox, BorderLayout.NORTH);

        JLabel notesBox = new JLabel("Notes: " + t.getNotes());
        infoPane.add(notesBox, BorderLayout.CENTER);

        JLabel dateBox = new JLabel("Due date: " + t.getDateFormatted());
        infoPane.add(dateBox, BorderLayout.SOUTH);
        taskPanel.add(infoPane, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JCheckBox completeCheck = new JCheckBox("Completed", t.isComplete());
        initCheckButton(completeCheck, t, taskPanel);
        buttons.add(completeCheck, BorderLayout.AFTER_LAST_LINE);

        JButton deleteButton = new JButton("Remove Task");
        initDeleteButton(t, deleteButton, taskPanel, panel);
        buttons.add(deleteButton);
        taskPanel.add(buttons, BorderLayout.SOUTH);
        panel.add(taskPanel);
    }

    private void initDeleteButton(Task t, JButton deleteButton, JPanel taskPanel, JPanel backGroundPanel) {
        deleteButton.addActionListener(e -> {
            manager.delete(t);
            backGroundPanel.remove(taskPanel);
            backGroundPanel.revalidate();
            backGroundPanel.updateUI();
            backGroundPanel.repaint();
        });
    }

    private void initCheckButton(JCheckBox checkBox, Task t, JPanel taskPanel) {
        checkBox.addActionListener(e -> {
            if(checkBox.isSelected()) {
                manager.markAsComplete(t, true);

            }else {
                manager.markAsComplete(t, false);
            }
        });
    }

    public void initAllButton() {
        allButton = new JButton("All");
        allButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        allButton.addActionListener(e -> {
            //Todo: add stuff related to all button.
        });
    }

    public void initOverdueButton() {
        overdueButton = new JButton("Overdue");
        overdueButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        overdueButton.addActionListener(e -> {
            //Todo: add stuff related to overdue button.
        });
    }

    public void initUpcomingButton() {
        upcomingButton = new JButton("Upcoming");
        upcomingButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        upcomingButton.addActionListener(e -> {
            //Todo: add stuff related to upcoming button.
        });
    }

    private void initMidPanel() {
        midPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        taskFrame.add(midPanel, BorderLayout.CENTER);
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

    private void initOnClose() {
        taskFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.save();
                super.windowClosing(e);
            }
        });
    }
}
