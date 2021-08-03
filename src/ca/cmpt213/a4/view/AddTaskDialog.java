package ca.cmpt213.a4.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.GregorianCalendar;

public class AddTaskDialog extends JDialog {
    private static final int DIALOG_WIDTH = 400;
    private static final int DIALOG_HEIGHT = 300;
    private static final int FIELD_WIDTH = 300;
    private static final int FIELD_HEIGHT = 25;
    private static final int PADDING = 20;
    private JTextField nameInput;
    private JTextField notesInput;
    private JPanel dialogPane;
    private TimePicker timePicker;
    private DatePicker datePicker;

    public AddTaskDialog() {
        setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        setTitle("Add Task");
        this.dialogPane = new JPanel();
        this.dialogPane.setLayout(new BoxLayout(dialogPane, BoxLayout.Y_AXIS));
        add(dialogPane);
        this.initLayout();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initLayout() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        getNamePanel(inputPanel);
        getNotesPanel(inputPanel);
        getDatePanel(inputPanel);
        addButtons(inputPanel);
    }

    private void addButtons(JPanel inputPanel) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton generate = new JButton("Generate");
        JButton create = new JButton("Create");
        JButton cancel = new JButton("Cancel");

        buttonPanel.add(generate);
        buttonPanel.add(create);
        buttonPanel.add(cancel);
        this.dialogPane.add(inputPanel);
        this.dialogPane.add(buttonPanel);

        initGenerateButton(generate);
        initCreateButton(create);
        initCancelButton(cancel);
    }

    private void getNamePanel(JPanel inputPanel) {
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
        JLabel namePrompt = new JLabel("Name:");
        this.nameInput = new JTextField();
        this.nameInput.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

        namePanel.add(namePrompt);
        namePanel.add(nameInput);
        inputPanel.add(namePanel);
    }

    private void getNotesPanel(JPanel inputPanel) {
        JPanel notesPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
        JLabel notesPrompt = new JLabel("Notes:");
        this.notesInput = new JTextField();
        this.notesInput.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

        notesPanel.add(notesPrompt);
        notesPanel.add(notesInput);
        inputPanel.add(notesPanel);
    }

    private void getDatePanel(JPanel inputPanel) {
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, PADDING, 0));
        JLabel datesPrompt = new JLabel("Due Date");
        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FiveMinutes, null, null);
        timePicker = new TimePicker(timeSettings);
        datePicker = new DatePicker();
        datePanel.add(datesPrompt);
        datePanel.add(datePicker);
        datePanel.add(timePicker);
        inputPanel.add(datePanel);
    }

    private void initGenerateButton(JButton generate) {
        generate.addActionListener(e -> {

        });
    }

    private void initCreateButton(JButton create) {
        create.addActionListener(e -> {
            if (isInputEmpty()) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Name, notes, date, and time can not be empty");
            } else {
                setVisible(false);
            }
        });
    }

    private boolean isInputEmpty() {
        return nameInput.getText().isEmpty() || notesInput.getText().isEmpty()
                || datePicker.getText().isEmpty() || timePicker.getText().isEmpty();
    }

    private void initCancelButton(JButton button) {
        button.addActionListener(e -> dispose());
    }

    public String getNameInput() {
        return nameInput.getText();
    }

    public String getNoteInput() {
        return notesInput.getText();
    }

    public GregorianCalendar getDateInput() {
        LocalDate date = datePicker.getDate();
        LocalTime time = timePicker.getTime();
        return new GregorianCalendar(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth(),
                time.getHour(), time.getMinute());
    }
}
