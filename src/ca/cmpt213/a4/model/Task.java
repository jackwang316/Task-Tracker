package ca.cmpt213.a4.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Task models the information about a task that has or needs to be completed.
 * A task includes its name, notes regarding the task, the due date, and
 * if the task has been completed. Features include retrieving the
 * attributes of the project, formatting the date to a proper string format and
 * formatting the entire object into one string.
 */
public class Task implements Comparable<Task> {
    private String name;
    private String notes;
    private GregorianCalendar dueDate;
    private boolean isComplete;

    public Task(String name, String notes, GregorianCalendar dueDate) {
        this.name = name;
        this.notes = notes;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isOverdue() {
        Calendar current = Calendar.getInstance();
        return current.after(this.dueDate);
    }

    public String getNotes() {
        return this.notes;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public GregorianCalendar getDueDate() {
        return this.dueDate;
    }

    public String getDateFormatted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setCalendar(this.dueDate);
        return dateFormat.format(dueDate.getTime());
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "Task: " + this.getName() + "\n" + "Notes: " + getNotes() + "\n"
                + "Due date: " + getDateFormatted();
    }

    @Override
    public int compareTo(Task t) {
        return this.getDueDate().compareTo(t.getDueDate());
    }
}
