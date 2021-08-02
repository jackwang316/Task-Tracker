package ca.cmpt213.a4.view;

import ca.cmpt213.a4.model.Task;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPane extends JFrame{
    private static final int DIALOG_WIDTH = 400;
    private static final int DIALOG_HEIGHT = 600;
    private static final int FIELD_WIDTH = 100;
    private static final int FIELD_HEIGHT = 25;
    private JDialog dialog;
    private Task added;
    private JTextField nameInput;
    private JTextField notesInput;
    private JPanel dialogPane;
    private TimePicker timePicker;
    private DatePicker datePicker;

    public AddTaskPane() {
        this.dialog = new JDialog();
        this.dialog.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.dialog.setTitle("Add Task");
        this.dialogPane = new JPanel();
        this.dialogPane.setLayout(new BoxLayout(dialogPane, BoxLayout.Y_AXIS));
        this.dialog.add(dialogPane);
        this.initLayout();
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initLayout() {
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel namePrompt = new JLabel("Name:");
        this.nameInput = new JTextField();
        this.nameInput.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

        namePanel.add(namePrompt);
        namePanel.add(nameInput);

        JPanel notesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel notesPrompt = new JLabel("Notes:");
        this.notesInput = new JTextField();
        this.notesInput.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

        notesPanel.add(notesPrompt);
        notesPanel.add(notesInput);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel datesPrompt = new JLabel("Due Date");
        timePicker = new TimePicker();
        datePicker = new DatePicker();
        datePanel.add(datesPrompt);
        datePanel.add(datePicker);
        datePanel.add(timePicker);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(namePanel);
        inputPanel.add(notesPanel);
        inputPanel.add(datePanel);

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

    private void initGenerateButton(JButton generate) {
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    private void initCreateButton(JButton create) {
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameInput.getText().isEmpty() || notesInput.getText().isEmpty()) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Name and notes can not be empty");
                }else {

                }
            }
        });
    }

    private void initCancelButton(JButton button) {
        button.addActionListener(e -> dispose());
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddTaskPane();
            }
        });
    }
}
