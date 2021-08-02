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
    public final static int SCROLL_PREFER_WIDTH = 500;
    public final static int SCROLL_PREFER_HEIGHT = 200;
    private JFrame taskFrame;
    private JPanel topButtonPanel;
    private JPanel cards;
    private JPanel bottomButtonPanel;

    private JScrollPane allTaskScroll;
    private JPanel allTasksPane;
    private JScrollPane overdueScroll;
    private JPanel overduePane;
    private JScrollPane upcomingScroll;
    private JPanel upcomingPane;

    private CardLayout cardLayout;
    private JButton allButton;
    private JButton overdueButton;
    private JButton upcomingButton;
    private JButton addNewButton;
    private TaskManager manager;

    public TaskGUI() {
        taskFrame = new JFrame("My To-Do List");
        taskFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        manager = new TaskManager();

        initAllButton();
        initOverdueButton();
        initUpcomingButton();

        initTopPanel();
        initCardPanel();
        initBottomPanel();

        initAllTaskPanel();
        initOverduePanel();
        initUpcomingPanel();

        taskFrame.setVisible(true);
        taskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initOnClose();
    }

    private void initAllTaskPanel() {
        ArrayList<Task> temp = manager.getAllTasks();
        allTasksPane = new JPanel();
        allTasksPane.setLayout(new BoxLayout(allTasksPane, BoxLayout.Y_AXIS));
        for(int i = 0; i < temp.size(); i++) {
            initTaskPanel(temp.get(i), allTasksPane, i, true);
        }
        allTaskScroll = new JScrollPane();
        allTaskScroll.getViewport().setPreferredSize(new Dimension(SCROLL_PREFER_WIDTH, SCROLL_PREFER_HEIGHT));
        allTaskScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        allTaskScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        allTaskScroll.getViewport().setView(allTasksPane);
        cards.add(allTaskScroll, "All");
    }

    private void initOverduePanel() {
        ArrayList<Task> temp = manager.getOverdue();
        overduePane = new JPanel();
        overduePane.setLayout(new BoxLayout(overduePane, BoxLayout.Y_AXIS));
        for(int i = 0; i < temp.size(); i++) {
            initTaskPanel(temp.get(i), overduePane, i, false);
        }
        overdueScroll = new JScrollPane();
        overdueScroll.getViewport().setPreferredSize(new Dimension(SCROLL_PREFER_WIDTH, SCROLL_PREFER_HEIGHT));
        overdueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        overdueScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        overdueScroll.getViewport().setView(overduePane);
        cards.add(overdueScroll, "Overdue");
    }

    private void initUpcomingPanel() {
        ArrayList<Task> temp = manager.getUpcoming();
        upcomingPane = new JPanel();
        upcomingPane.setLayout(new BoxLayout(upcomingPane, BoxLayout.Y_AXIS));
        for(int i = 0; i < temp.size(); i++) {
            initTaskPanel(temp.get(i), upcomingPane, i, false);
        }
        upcomingScroll = new JScrollPane();
        upcomingScroll.getViewport().setPreferredSize(new Dimension(SCROLL_PREFER_WIDTH, SCROLL_PREFER_HEIGHT));
        upcomingScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        upcomingScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        upcomingScroll.getViewport().setView(upcomingPane);
        cards.add(upcomingScroll, "Upcoming");
    }

    private void initTaskPanel(Task t, JPanel panel, int i, boolean hasButtons) {
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

        if(hasButtons) {
            addButtons(t, panel, taskPanel);
        }
        panel.add(taskPanel);
    }

    private void addButtons(Task t, JPanel panel, JPanel taskPanel) {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JCheckBox completeCheck = new JCheckBox("Completed", t.isComplete());
        initCheckButton(completeCheck, t);
        buttons.add(completeCheck, BorderLayout.AFTER_LAST_LINE);

        JButton deleteButton = new JButton("Remove Task");
        initDeleteButton(t, deleteButton, taskPanel, panel);
        buttons.add(deleteButton);
        taskPanel.add(buttons, BorderLayout.SOUTH);
    }

    private void initDeleteButton(Task t, JButton deleteButton, JPanel taskPanel, JPanel backGroundPanel) {
        deleteButton.addActionListener(e -> {
            manager.delete(t);
            backGroundPanel.remove(taskPanel);
            backGroundPanel.revalidate();
            backGroundPanel.repaint();
        });
    }

    private void initCheckButton(JCheckBox checkBox, Task t) {
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
        allButton.addActionListener(e -> cardLayout.show(cards, "All"));
    }

    public void initOverdueButton() {
        overdueButton = new JButton("Overdue");
        overdueButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        overdueButton.addActionListener(e -> cardLayout.show(cards, "Overdue"));
    }

    public void initUpcomingButton() {
        upcomingButton = new JButton("Upcoming");
        upcomingButton.setPreferredSize(new Dimension(BUTTON_PREFER_WIDTH, BUTTON_PREFER_HEIGHT));
        upcomingButton.addActionListener(e -> cardLayout.show(cards, "Upcoming"));
    }

    private void initCardPanel() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        taskFrame.add(cards, BorderLayout.CENTER);
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
